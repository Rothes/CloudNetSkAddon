package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ServiceDeployment;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class EffAddDeploymentService extends BaseCloudNetServiceEffect {

    protected Expression<String> storage;
    protected Expression<String> excludedFiles;

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        ServiceTemplate template = ServiceTemplate.parse(storage.getSingle(e));
        Collection<String> excludes = excludedFiles != null ? Arrays.asList(excludedFiles.getSingle(e).split(";")) : new ArrayList<>();

        serviceInfoSnapshot.provider().addServiceDeployment(new ServiceDeployment(template, excludes));
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        storage = (Expression<String>) exprs[1];
        excludedFiles = (Expression<String>) exprs[2];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
