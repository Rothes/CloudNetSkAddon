package io.github.rothes.cloudnetskaddon.effects;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.BridgePlayerManager;
import de.dytanic.cloudnet.ext.bridge.node.CloudNetBridgeModule;
import de.dytanic.cloudnet.ext.bridge.node.player.NodePlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;

import java.lang.reflect.Field;

public abstract class BaseCloudNetPlayersEffect extends BaseCloudNetEffect {

    protected static final BridgePlayerManager playerManager = (BridgePlayerManager) CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

}
