package io.github.rothes.cloudnetskaddon.effects;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.common.WildcardUtil;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import org.bukkit.event.Event;

import java.util.Collection;

public abstract class BaseCloudNetServiceEffect extends BaseCloudNetEffect {

    protected Collection<ServiceInfoSnapshot> serviceInfoSnapshots;

    protected Expression<String> name;

    @Override
    protected void execute(Event e) {
        setServices(e);

        for (ServiceInfoSnapshot serviceInfoSnapshot : serviceInfoSnapshots) {
            process(serviceInfoSnapshot, e);
        }
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }

    protected void setServices(Event e) {
        String name = this.name.getSingle(e);

        serviceInfoSnapshots = WildcardUtil.filterWildcard(
                CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices(),
                name,
                false
        );
    }

    abstract protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e);

}
