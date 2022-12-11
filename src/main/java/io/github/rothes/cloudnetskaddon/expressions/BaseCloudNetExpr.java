package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.util.SimpleExpression;
import io.github.rothes.cloudnetskaddon.CloudNetBase;
import org.bukkit.event.Event;

public abstract class BaseCloudNetExpr<T> extends SimpleExpression<T> implements CloudNetBase {

    @Override
    public String toString(Event event, boolean debug) {
        return "CloudNet " + this.getClass().getSimpleName().substring(3) + " expression";
    }

}
