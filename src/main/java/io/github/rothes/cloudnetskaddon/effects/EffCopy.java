package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.common.language.LanguageManager;
import de.dytanic.cloudnet.driver.provider.service.SpecificCloudServiceProvider;
import de.dytanic.cloudnet.driver.service.ServiceDeployment;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class EffCopy extends BaseCloudNetEffect {

    private Expression<String> service;
    private Expression<String> template;
    private Expression<String> excludes;

    @Override
    protected void execute(Event e) {
        SpecificCloudServiceProvider cloudServiceProvider1 = getCloudNetDriver().getCloudServiceProvider(service.getSingle(e));
        if (cloudServiceProvider1 == null || !cloudServiceProvider1.isValid()) {
            cloudServiceProvider1 = getCloudNetDriver().getCloudServiceProvider(UUID.fromString(service.getSingle(e)));
        }
        ServiceInfoSnapshot serviceInfoSnapshot = cloudServiceProvider1.getServiceInfoSnapshot();

        if (serviceInfoSnapshot != null) {
            SpecificCloudServiceProvider cloudServiceProvider = serviceInfoSnapshot.provider();
            ServiceTemplate targetTemplate;

            if (template == null) {
                targetTemplate = Arrays.stream(serviceInfoSnapshot.getConfiguration().getTemplates())
                        .filter(serviceTemplate ->
                                serviceTemplate.getPrefix().equalsIgnoreCase(serviceInfoSnapshot.getServiceId().getTaskName())
                                        && serviceTemplate.getName().equalsIgnoreCase("default"))
                        .findFirst().orElse(null);
            } else {
                targetTemplate = ServiceTemplate.parse(template.getSingle(e));
            }

            if (targetTemplate == null) {
                throw new IllegalArgumentException(LanguageManager.getMessage("command-copy-service-no-default-template")
                        .replace("%name%", serviceInfoSnapshot.getServiceId().getName()));
            }

            List<ServiceDeployment> oldDeployments = new ArrayList<>(
                    Arrays.asList(serviceInfoSnapshot.getConfiguration().getDeployments()));

            List<String> excludes = this.excludes != null ? Arrays.asList(this.excludes.getSingle(e).split(";")) : Collections.emptyList();

            cloudServiceProvider.addServiceDeployment(new ServiceDeployment(targetTemplate, excludes));
            cloudServiceProvider.deployResources(true);

            oldDeployments.forEach(cloudServiceProvider::addServiceDeployment);
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        service = (Expression<String>) exprs[0];
        template = (Expression<String>) exprs[1];
        excludes = (Expression<String>) exprs[2];
        return true;
    }

}
