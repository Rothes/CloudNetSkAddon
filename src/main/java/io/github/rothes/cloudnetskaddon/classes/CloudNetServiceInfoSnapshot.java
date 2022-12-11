package io.github.rothes.cloudnetskaddon.classes;

public final class CloudNetServiceInfoSnapshot {

    public final String name;
    public final String lifeCycle;
    public final String address;

    public CloudNetServiceInfoSnapshot(de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot ss) {
        name = ss.getName();
        lifeCycle = ss.getLifeCycle().name();
        address = ss.getAddress().toString();
    }

    @Override
    public String toString() {
        return "ServiceInfoSnapshot{" +
                "name='" + name + '\'' +
                ", status='" + lifeCycle + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
