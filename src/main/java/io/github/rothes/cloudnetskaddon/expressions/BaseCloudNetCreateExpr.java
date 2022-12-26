package io.github.rothes.cloudnetskaddon.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.common.primitives.Ints;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ProcessConfiguration;
import de.dytanic.cloudnet.driver.service.ServiceDeployment;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceRemoteInclusion;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTask;
import de.dytanic.cloudnet.driver.service.ServiceTemplate;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseCloudNetCreateExpr extends BaseCloudNetExpr<CloudNetServiceInfoSnapshot> {

    protected Expression<Number> count;
    protected Expression<String> name;
    protected Expression<String> runtime;
    protected Expression<Boolean> autoDeleteOnStop;
    protected Expression<Boolean> staticServices;
    protected Expression<String> nodes;
    protected Expression<String> templates;
    protected Expression<String> deployments;
    protected Expression<Number> port;
    protected Expression<String> groups;
    protected Expression<String> deletedFilesAfterStop;
    protected Expression<String> memory;
    protected Expression<String> jvmOptions;
    protected Expression<String> processParameters;
    protected Expression<String> javaCommand;
    protected boolean start;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends CloudNetServiceInfoSnapshot> getReturnType() {
        return CloudNetServiceInfoSnapshot.class;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        count = (Expression<Number>) exprs[0];
        name = (Expression<String>) exprs[1];
        runtime = (Expression<String>) exprs[2];
        autoDeleteOnStop = (Expression<Boolean>) exprs[3];
        staticServices = (Expression<Boolean>) exprs[4];
        nodes = (Expression<String>) exprs[5];
        templates = (Expression<String>) exprs[6];
        deployments = (Expression<String>) exprs[7];
        port = (Expression<Number>) exprs[8];
        groups = (Expression<String>) exprs[9];
        deletedFilesAfterStop = (Expression<String>) exprs[10];
        memory = (Expression<String>) exprs[11];
        jvmOptions = (Expression<String>) exprs[12];
        processParameters = (Expression<String>) exprs[13];
        javaCommand = (Expression<String>) exprs[14];
        if (parseResult.mark == 1) {
            start = true;
        }
        return true;
    }


    protected Collection<ServiceInfoSnapshot> runCloudService(
            Event e,
            int count,
            String name,
            String runtime,
            boolean autoDeleteOnStop,
            boolean staticServices,
            Collection<String> nodes,
            Collection<ServiceRemoteInclusion> includes,
            Collection<ServiceTemplate> templates,
            Collection<ServiceDeployment> deployments,
            Collection<String> groups,
            Collection<String> deletedFilesAfterStop,
            ProcessConfiguration processConfiguration,
            int startPort,
            String javaCommand
    ) {
        Collection<ServiceInfoSnapshot> serviceInfoSnapshots = new ArrayList<>(count);

        Collection<ServiceTemplate> temps =
                this.templates != null ? Arrays.asList(ServiceTemplate.parseArray(this.templates.getSingle(e)))
                        : templates;
        Collection<ServiceDeployment> deploy =
                this.deployments != null ? Arrays.stream(ServiceTemplate.parseArray(this.deployments.getSingle(e)))
                        .map(serviceTemplate -> new ServiceDeployment(serviceTemplate, new ArrayList<>()))
                        .collect(Collectors.toList()) : deployments;

        Integer finalStartPort = this.port != null ? this.port.getSingle(e).intValue() : startPort;
        if (finalStartPort == null) {
            finalStartPort = processConfiguration.getEnvironment().getDefaultStartPort();
        }

        for (int i = 0; i < count; i++) {
            ServiceInfoSnapshot serviceInfoSnapshot = CloudNetDriver.getInstance().getCloudServiceFactory()
                    .createCloudService(new ServiceTask(
                            includes,
                            temps,
                            deploy,
                            getOrDefault(this.name, name, e),
                            getOrDefault(this.runtime, runtime, e),
                            getOrDefault(this.autoDeleteOnStop, autoDeleteOnStop, e),
                            getOrDefault(this.staticServices, staticServices, e),
                            this.nodes != null ? Arrays.asList(this.nodes.getSingle(e).split(";")) : nodes,
                            this.groups != null ? Arrays.asList(this.groups.getSingle(e).split(";")) : groups,
                            this.deletedFilesAfterStop != null ? Arrays.asList(this.deletedFilesAfterStop.getSingle(e).split(";")) : deletedFilesAfterStop,
                            new ProcessConfiguration(
                                    processConfiguration.getEnvironment(),
                                    this.memory != null && Ints.tryParse(this.memory.getSingle(e)) != null ?
                                            Integer.parseInt(this.memory.getSingle(e)) : processConfiguration.getMaxHeapMemorySize(),
                                    new ArrayList<>(this.jvmOptions != null ?
                                            Arrays.asList(this.jvmOptions.getSingle(e).split(";")) :
                                            processConfiguration.getJvmOptions()),
                                    new ArrayList<>(this.processParameters != null ?
                                            Arrays.asList(this.processParameters.getSingle(e).split(";")) :
                                            processConfiguration.getProcessParameters())
                            ),
                            finalStartPort,
                            0,
                            getOrDefault(this.javaCommand, javaCommand, e)
                    ));

            if (serviceInfoSnapshot != null) {
                serviceInfoSnapshots.add(serviceInfoSnapshot);
            }
        }

        if (start) {
            for (ServiceInfoSnapshot serviceInfoSnapshot : serviceInfoSnapshots) {
                serviceInfoSnapshot.provider().start();
            }
        }

        return serviceInfoSnapshots;
    }

    private <T> T getOrDefault(Expression<T> expr, T def, Event e) {
        return expr != null ? expr.getSingle(e) : def;
    }

}
