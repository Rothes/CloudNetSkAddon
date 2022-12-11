package io.github.rothes.cloudnetskaddon.effects;

import com.google.common.primitives.Ints;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTask;
import org.bukkit.event.Event;

public class EffStartService extends BaseCloudNetServiceEffect {

    @Override
    protected void execute(Event e) {
        setServices(e);
        if (serviceInfoSnapshots.isEmpty()) {
            String[] splitName = name.getSingle(e).split("-");
            String taskName = splitName[0];
            Integer id = Ints.tryParse(splitName[1]);

            if (id != null) {
                ServiceTask task = CloudNetDriver.getInstance().getServiceTaskProvider().getServiceTask(taskName);
                if (task != null) {
                    ServiceInfoSnapshot serviceInfoSnapshot = CloudNetDriver.getInstance().getCloudServiceFactory()
                            .createCloudService(task, id);
                    if (serviceInfoSnapshot != null) {
                        serviceInfoSnapshots.add(serviceInfoSnapshot);
                    }
                }
            }
        }

        for (ServiceInfoSnapshot serviceInfoSnapshot : serviceInfoSnapshots) {
            process(serviceInfoSnapshot, e);
        }
    }

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        serviceInfoSnapshot.provider().start();
    }

}
