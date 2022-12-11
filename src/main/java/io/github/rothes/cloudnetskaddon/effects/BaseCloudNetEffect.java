package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Effect;
import io.github.rothes.cloudnetskaddon.CloudNetBase;
import org.bukkit.event.Event;

public abstract class BaseCloudNetEffect extends Effect implements CloudNetBase {

    @Override
    public String toString(Event event, boolean debug) {
        return "CloudNet " + this.getClass().getSimpleName().substring(2) + " effect";
    }

}
