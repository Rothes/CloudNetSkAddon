package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.wrapper.Wrapper;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import org.bukkit.event.Event;

public class ExprThisService extends BaseCloudNetServicesExpr {

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    protected CloudNetServiceInfoSnapshot[] get(Event e) {
        return new CloudNetServiceInfoSnapshot[]{new CloudNetServiceInfoSnapshot(Wrapper.getInstance().getCurrentServiceInfoSnapshot())};
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

}
