package io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import io.github.rothes.cloudnetskaddon.expressions.BaseCloudNetExpr;
import org.bukkit.event.Event;

public class ExprServiceInfoSnapshotOnlineCount extends BaseCloudNetExpr<Integer> {

    protected Expression<CloudNetServiceInfoSnapshot> serviceInfoSnapshot;

    @Override
    protected Integer[] get(Event e) {
        return new Integer[]{serviceInfoSnapshot.getSingle(e).getOnlineCount()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        serviceInfoSnapshot = (Expression<CloudNetServiceInfoSnapshot>) exprs[0];
        return true;
    }

}
