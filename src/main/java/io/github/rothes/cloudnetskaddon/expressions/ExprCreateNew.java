package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.service.ProcessConfiguration;
import de.dytanic.cloudnet.driver.service.ServiceEnvironmentType;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Collection;

public class ExprCreateNew extends BaseCloudNetCreateExpr {

    private Expression<String> name;
    private Expression<String> type;

    @Override
    protected CloudNetServiceInfoSnapshot[] get(Event e) {
        String name = this.name.getSingle(e);
        ServiceEnvironmentType environment = ServiceEnvironmentType.valueOf(this.type.getSingle(e));

        Collection<ServiceInfoSnapshot> serviceInfoSnapshots = runCloudService(
                e,
                count != null ? count.getSingle(e).intValue() : 1,
                name,
                null,
                false,
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ProcessConfiguration(
                        environment,
                        environment.isMinecraftProxy() ? 256 : 512,
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                environment.getDefaultStartPort(),
                null
        );
        return serviceInfoSnapshots.stream().map(CloudNetServiceInfoSnapshot::new).toArray(CloudNetServiceInfoSnapshot[]::new);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        name = (Expression<String>) exprs[15];
        type = (Expression<String>) exprs[16];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
