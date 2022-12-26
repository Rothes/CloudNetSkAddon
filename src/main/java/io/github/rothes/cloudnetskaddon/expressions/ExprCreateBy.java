package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTask;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import org.bukkit.event.Event;

import java.util.Collection;

public class ExprCreateBy extends BaseCloudNetCreateExpr {

    private Expression<String> task;

    @Override
    protected CloudNetServiceInfoSnapshot[] get(Event e) {
        return create(e).stream().map(CloudNetServiceInfoSnapshot::new).toArray(CloudNetServiceInfoSnapshot[]::new);
    }

    protected Collection<ServiceInfoSnapshot> create(Event e) {
        ServiceTask serviceTask = CloudNetDriver.getInstance().getServiceTaskProvider()
                .getServiceTask(task.getSingle(e));
        Collection<ServiceInfoSnapshot> serviceInfoSnapshots = runCloudService(
                e,
                count != null ? count.getSingle(e).intValue() : 1,
                serviceTask.getName(),
                serviceTask.getRuntime(),
                serviceTask.isAutoDeleteOnStop(),
                serviceTask.isStaticServices(),
                serviceTask.getAssociatedNodes(),
                serviceTask.getIncludes(),
                serviceTask.getTemplates(),
                serviceTask.getDeployments(),
                serviceTask.getGroups(),
                serviceTask.getDeletedFilesAfterStop(),
                serviceTask.getProcessConfiguration(),
                serviceTask.getStartPort(),
                serviceTask.getJavaCommand()
        );
        return serviceInfoSnapshots;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        task = (Expression<String>) exprs[15];
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }

}
