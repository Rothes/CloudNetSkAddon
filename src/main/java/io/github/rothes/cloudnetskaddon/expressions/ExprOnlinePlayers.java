package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import org.bukkit.event.Event;

public class ExprOnlinePlayers extends BaseCloudNetExpr<String> {

    protected static final BridgePlayerManager playerManager = (BridgePlayerManager) CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    @Override
    protected String[] get(Event e) {
        return playerManager.onlinePlayers().asNames().toArray(new String[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

}
