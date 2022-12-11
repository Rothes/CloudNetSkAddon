package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceRemoteInclusion;
import org.bukkit.event.Event;

import java.net.MalformedURLException;
import java.net.URL;

public class EffAddInclusionService extends BaseCloudNetServiceEffect {

    protected Expression<String> url;
    protected Expression<String> targetPath;

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        try {
            new URL(url.getSingle(e));
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("url");
        }

        serviceInfoSnapshot.provider().addServiceRemoteInclusion(new ServiceRemoteInclusion(url.getSingle(e), targetPath.getSingle(e)));
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        url = (Expression<String>) exprs[1];
        targetPath = (Expression<String>) exprs[1];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
