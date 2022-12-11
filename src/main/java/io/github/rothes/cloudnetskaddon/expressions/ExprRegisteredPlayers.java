package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import org.bukkit.event.Event;

import java.util.stream.Collectors;

public class ExprRegisteredPlayers extends BaseCloudNetExpr<String> {

    protected static final BridgePlayerManager playerManager = (BridgePlayerManager) CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

    @Override
    protected String[] get(Event e) {
        return playerManager.getRegisteredPlayers().stream().map(ICloudOfflinePlayer::getName).toArray(String[]::new);
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
