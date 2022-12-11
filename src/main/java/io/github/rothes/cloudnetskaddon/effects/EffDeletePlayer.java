package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import org.bukkit.event.Event;

public class EffDeletePlayer extends BaseCloudNetPlayersEffect {

    private Expression<String> name;

    @Override
    protected void execute(Event e) {
        for (ICloudOfflinePlayer offlinePlayer : playerManager.getOfflinePlayers(name.getSingle(e))) {
            playerManager.deleteCloudOfflinePlayerAsync(offlinePlayer);
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }

}
