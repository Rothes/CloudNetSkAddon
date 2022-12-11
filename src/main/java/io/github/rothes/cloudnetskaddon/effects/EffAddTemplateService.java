package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;
import org.bukkit.event.Event;

public class EffAddTemplateService extends BaseCloudNetServiceEffect {

    protected Expression<String> storage;

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        ServiceTemplate template = ServiceTemplate.parse(storage.getSingle(e));

        serviceInfoSnapshot.provider().addServiceTemplate(template);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        storage = (Expression<String>) exprs[1];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
