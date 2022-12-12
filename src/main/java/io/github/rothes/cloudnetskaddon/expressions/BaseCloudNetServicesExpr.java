package io.github.rothes.cloudnetskaddon.expressions;

import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;

public abstract class BaseCloudNetServicesExpr extends BaseCloudNetExpr<CloudNetServiceInfoSnapshot> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends CloudNetServiceInfoSnapshot> getReturnType() {
        return CloudNetServiceInfoSnapshot.class;
    }

}
