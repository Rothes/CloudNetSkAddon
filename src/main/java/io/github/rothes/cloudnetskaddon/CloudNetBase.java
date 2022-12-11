package io.github.rothes.cloudnetskaddon;

import de.dytanic.cloudnet.driver.CloudNetDriver;

public interface CloudNetBase {

    default CloudNetDriver getCloudNetDriver() {
        return CloudNetDriver.getInstance();
    }

}
