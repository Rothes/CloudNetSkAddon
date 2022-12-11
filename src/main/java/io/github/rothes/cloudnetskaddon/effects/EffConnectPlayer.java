package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.common.language.LanguageManager;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer;
import org.bukkit.event.Event;

public class EffConnectPlayer extends BaseCloudNetPlayersEffect {

    private Expression<String> name;
    private Expression<String> server;

    @Override
    protected void execute(Event e) {
        String server = this.server.getSingle(e);
        ServiceInfoSnapshot serviceInfoSnapshot = getCloudNetDriver().getCloudServiceProvider()
                .getCloudServiceByName(server);
        if (!serviceInfoSnapshot.getConfiguration().getProcessConfig().getEnvironment().isMinecraftServer()) {
            throw new IllegalArgumentException(LanguageManager.getMessage("module-bridge-command-players-send-player-server-no-server"));
        }
        for (ICloudPlayer player : playerManager.getOnlinePlayers(this.name.getSingle(e))) {
            playerManager.getPlayerExecutor(player).connect(server);
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        server = (Expression<String>) exprs[1];
        return true;
    }

}
