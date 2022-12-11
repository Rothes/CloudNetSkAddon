package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import org.bukkit.event.Event;

public class EffKickPlayer extends BaseCloudNetPlayersEffect {

    private Expression<String> name;
    private Expression<String> reason;
    private boolean forceDisconnect;

    @Override
    protected void execute(Event e) {
        for (ICloudPlayer player : playerManager.getOnlinePlayers(name.getSingle(e))) {
            playerManager.getPlayerExecutor(player).kick(reason != null ? reason.getSingle(e) : "no reason given");

//            if (forceDisconnect) {
//                playerManager.logoutPlayer(player.getUniqueId(), player.getName(), player.getLoginService());
//            }
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        reason = (Expression<String>) exprs[1];
        if (parseResult.mark == 1) {
            forceDisconnect = true;
        }
        return true;
    }

}
