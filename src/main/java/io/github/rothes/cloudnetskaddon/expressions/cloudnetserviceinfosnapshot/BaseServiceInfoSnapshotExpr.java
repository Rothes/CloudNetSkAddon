package io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import io.github.rothes.cloudnetskaddon.expressions.BaseCloudNetExpr;

public abstract class BaseServiceInfoSnapshotExpr extends BaseCloudNetExpr<String> {

    protected Expression<CloudNetServiceInfoSnapshot> serviceInfoSnapshot;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        serviceInfoSnapshot = (Expression<CloudNetServiceInfoSnapshot>) exprs[0];
        return true;
    }

}
