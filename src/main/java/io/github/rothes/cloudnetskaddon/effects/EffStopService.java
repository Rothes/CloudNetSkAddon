package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import org.bukkit.event.Event;

public class EffStopService extends BaseCloudNetServiceEffect {

    private boolean force;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        force = parseResult.mark == 1;
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        if (force)
            serviceInfoSnapshot.provider().kill();
        else
            serviceInfoSnapshot.provider().stop();
    }

}
