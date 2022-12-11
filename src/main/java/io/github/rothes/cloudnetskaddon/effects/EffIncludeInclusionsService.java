package io.github.rothes.cloudnetskaddon.effects;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import org.bukkit.event.Event;

public class EffIncludeInclusionsService extends BaseCloudNetServiceEffect {

    @Override
    protected void process(ServiceInfoSnapshot serviceInfoSnapshot, Event e) {
        serviceInfoSnapshot.provider().includeWaitingServiceInclusions();
    }

}
