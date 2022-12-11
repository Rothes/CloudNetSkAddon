package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import org.bukkit.event.Event;

public class EffCommandService extends BaseCloudNetServiceEffect {

    protected Expression<String> command;

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        serviceInfoSnapshot.provider().runCommand(command.getSingle(e));
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        command = (Expression<String>) exprs[1];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
