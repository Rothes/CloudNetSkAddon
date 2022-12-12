package io.github.rothes.cloudnetskaddon.classes;

import de.dytanic.cloudnet.driver.network.HostAndPort;
import de.dytanic.cloudnet.driver.service.ServiceLifeCycle;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;

public final class CloudNetServiceInfoSnapshot {

    public final de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot ss;

    public CloudNetServiceInfoSnapshot(de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot ss) {
        this.ss = ss;
    }

    public String getName() {
        return ss.getName();
    }

    public HostAndPort getAddress() {
        return ss.getAddress();
    }

    public ServiceLifeCycle getLifeCycle() {
        return ss.getLifeCycle();
    }

    public int getOnlineCount() {
        return ss.getProperty(BridgeServiceProperty.ONLINE_COUNT).orElse(0);
    }

    @Override
    public String toString() {
        return "CloudNetServiceInfoSnapshot{" +
                "ss=" + ss +
                '}';
    }

}
