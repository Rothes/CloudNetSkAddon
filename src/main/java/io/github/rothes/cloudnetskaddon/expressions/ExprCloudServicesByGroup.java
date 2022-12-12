package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import org.bukkit.event.Event;

public class ExprCloudServicesByGroup extends BaseCloudNetServicesExpr {

    private Expression<String> group;

    @Override
    protected CloudNetServiceInfoSnapshot[] get(Event e) {
        return CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServicesByGroup(group.getSingle(e))
                .stream().map(CloudNetServiceInfoSnapshot::new).toArray(CloudNetServiceInfoSnapshot[]::new);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        group = (Expression<String>) exprs[0];
        return true;
    }

}
