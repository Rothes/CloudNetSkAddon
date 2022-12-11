package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import org.bukkit.event.Event;

public class EffMessagePlayer extends BaseCloudNetPlayersEffect {

    private Expression<String> name;
    private Expression<String> message;

    @Override
    protected void execute(Event e) {
        for (ICloudPlayer player : playerManager.getOnlinePlayers(name.getSingle(e))) {
            playerManager.getPlayerExecutor(player).sendChatMessage(message.getSingle(e));
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        message = (Expression<String>) exprs[1];
        return true;
    }

}
