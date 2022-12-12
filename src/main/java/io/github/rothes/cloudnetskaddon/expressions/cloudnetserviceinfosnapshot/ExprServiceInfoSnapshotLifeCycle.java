package io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot;

import org.bukkit.event.Event;

public class ExprServiceInfoSnapshotLifeCycle extends BaseServiceInfoSnapshotExpr {

    @Override
    protected String[] get(Event e) {
        return new String[]{serviceInfoSnapshot.getSingle(e).getLifeCycle().name()};
    }

}
