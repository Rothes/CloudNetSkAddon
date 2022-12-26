package io.github.rothes.cloudnetskaddon;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.github.rothes.cloudnetskaddon.classes.CloudNetServiceInfoSnapshot;
import io.github.rothes.cloudnetskaddon.effects.*;
import io.github.rothes.cloudnetskaddon.expressions.ExprCloudServicesByGroup;
import io.github.rothes.cloudnetskaddon.expressions.ExprCloudServicesByTask;
import io.github.rothes.cloudnetskaddon.expressions.ExprCreateBy;
import io.github.rothes.cloudnetskaddon.expressions.ExprCreateNew;
import io.github.rothes.cloudnetskaddon.expressions.ExprCloudServices;
import io.github.rothes.cloudnetskaddon.expressions.ExprOnlinePlayers;
import io.github.rothes.cloudnetskaddon.expressions.ExprOnlinePlayersCount;
import io.github.rothes.cloudnetskaddon.expressions.ExprRegisteredPlayers;
import io.github.rothes.cloudnetskaddon.expressions.ExprThisService;
import io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot.ExprServiceInfoSnapshotAddress;
import io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot.ExprServiceInfoSnapshotLifeCycle;
import io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot.ExprServiceInfoSnapshotName;
import io.github.rothes.cloudnetskaddon.expressions.cloudnetserviceinfosnapshot.ExprServiceInfoSnapshotOnlineCount;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class CloudNetSkAddon extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!Skript.isAcceptRegistrations()) {
            getLogger().log(Level.SEVERE, "Disabling plugin due to Skript doesn't accept registrations now. Please restart the whole server.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        registerClasses();
        registerEffects();
        registerExpressions();
        Skript.registerAddon(this);
    }

    private void registerClasses() {
        Classes.registerClass(new ClassInfo<>(CloudNetServiceInfoSnapshot.class, "serviceinfosnapshot")
                .name("serviceinfosnapshot")
                .description("Represents a CloudNet ServiceInfoSnapshot.")
                .defaultExpression(new EventValueExpression<>(CloudNetServiceInfoSnapshot.class))
                .parser(new Parser<CloudNetServiceInfoSnapshot>() {
                    @Override
                    @Nonnull
                    public String toString(CloudNetServiceInfoSnapshot serviceInfoSnapshot, int flags) {
                        return toVariableNameString(serviceInfoSnapshot);
                    }

                    @Override
                    public boolean canParse(@Nonnull ParseContext context) {
                        return false;
                    }

                    @Override
                    public CloudNetServiceInfoSnapshot parse(@Nonnull String s, @Nonnull ParseContext context) {
                        return null;
                    }

                    @Override
                    @Nonnull
                    public String toVariableNameString(CloudNetServiceInfoSnapshot serviceInfoSnapshot) {
                        return "ServiceInfoSnapshot:" + serviceInfoSnapshot.getName();
                    }

                    @Override
                    @Nonnull
                    public String getVariableNamePattern() {
                        return "ServiceInfoSnapshot:[\\s\\S]+";
                    }
                }));
    }

    private void registerEffects() {
//        Skript.registerEffect(EffClusterShutdown.class, "[cloud[ ]net] [cluster] shut[ ]down [the] cluster");
//        Skript.registerEffect(EffClusterAdd.class, "[cloud[ ]net] cluster add node %string% to host %string%");
//        Skript.registerEffect(EffClusterRemove.class, "[cloud[ ]net] cluster remove node %string%");
//        Skript.registerEffect(EffClusterPushAll.class, "[cloud[ ]net] cluster push[ ]all");
//        Skript.registerEffect(EffClusterPushLocalTemplate.class, "[cloud[ ]net] cluster push [local][ ]template %string%");
//        Skript.registerEffect(EffClusterPushLocalTemplates.class, "[cloud[ ]net] cluster push [local][ ]templates");
//        Skript.registerEffect(EffClusterPushTasks.class, "[cloud[ ]net] cluster push tasks");
//        Skript.registerEffect(EffClusterPushGroups.class, "[cloud[ ]net] cluster push groups");
//        Skript.registerEffect(EffClusterPushLocalPerms.class, "[cloud[ ]net] cluster push [local][ ](perms|permissions)");
//
//        Skript.registerEffect(EffClear.class, "[cloud[ ]net] clear the console");
        Skript.registerEffect(EffCopy.class, "[cloud[ ]net] copy [the] service %string% to template %string% [excludes %-string%]");
        Skript.registerEffect(EffExit.class, "[cloud[ ]net] exit");

        Skript.registerEffect(EffDeletePlayer.class, "[cloud[ ]net] delete player %string%");
        Skript.registerEffect(EffKickPlayer.class, "[cloud[ ]net] [(1¦(forced|force))] kick player %string% [[with] reason %-string%]", "[cloud[ ]net] kick player %string% [[with] string %-string%] [(1¦(forced|force))]");
        Skript.registerEffect(EffMessagePlayer.class, "[cloud[ ]net] message player %string% %string%", "[cloud[ ]net] send message %string% to player %string%");
        Skript.registerEffect(EffConnectPlayer.class, "[cloud[ ]net] connect player %string% to server %string%");

        Skript.registerEffect(EffStartService.class, "[cloud[ ]net] start service %string%");
        Skript.registerEffect(EffStopService.class, "[cloud[ ]net] [(1¦(forced|force))] stop service %string%", "[cloud[ ]net] stop service %string% [(1¦(forced|force))]");
        Skript.registerEffect(EffDeleteService.class, "[cloud[ ]net] delete service %string%");
        Skript.registerEffect(EffIncludeInclusionsService.class, "[cloud[ ]net] include[ ]inclusion[s] service %string%");
        Skript.registerEffect(EffIncludeTemplatesService.class, "[cloud[ ]net] include[ ]template[s] service %string%");
        Skript.registerEffect(EffDeployResourcesService.class, "[cloud[ ]net] deploy[ ]resource[s] service %string%");
        Skript.registerEffect(EffRestartService.class, "[cloud[ ]net] restart service %string%");
        Skript.registerEffect(EffCommandService.class, "[cloud[ ]net] command service %string% [with] %string%", "[cloud[ ]net] send command %string% to service %string%");
        Skript.registerEffect(EffAddDeploymentService.class, "[cloud[ ]net] add deployment to service %string% with storage %string% [([with] excluded[ ]file[s]|excludes) %-string%]");
        Skript.registerEffect(EffAddTemplateService.class, "[cloud[ ]net] add template to service %string% with storage %string%");
        Skript.registerEffect(EffAddInclusionService.class, "[cloud[ ]net] add inclusion to service %string% with url %string% and targetPath %string%");

    }

    private void registerExpressions() {
        Skript.registerExpression(ExprOnlinePlayersCount.class, Integer.class, ExpressionType.SIMPLE,
                "[the] [cloud[ ]net] online player[s] count");
        Skript.registerExpression(ExprOnlinePlayers.class, String.class, ExpressionType.SIMPLE,
                "[the] [cloud[ ]net] online player[s]");
        Skript.registerExpression(ExprRegisteredPlayers.class, String.class, ExpressionType.SIMPLE,
                "[the] [cloud[ ]net] registered player[s]");

        Skript.registerExpression(ExprCreateBy.class, CloudNetServiceInfoSnapshot.class, ExpressionType.COMBINED, "[cloud[ ]net] create [%-number%] service[s] [named %-string%] " +
                "[[with] runtime %-string%] [[with] auto[ ]delete[ ]on[ ]stop %-boolean%] [[with] static[ ]services %-boolean%] " +
                "[[with] nodes %-string%] [[with] templates %-string%] [[with] deployments %-string%] " +
                "[[with] port %-string%] [[with] groups %-string%] [[with] deleted[ ]files[ ]after[ ]stop %-string%] " +
                "[[with] memory %-string%] [[with] jvmOptions %-string%] [[with] processParameters %-string%] [[with] javaCommand %-string%] by task %string%" +
                "[(1¦and start)]");
        Skript.registerExpression(ExprCreateNew.class, CloudNetServiceInfoSnapshot.class, ExpressionType.COMBINED, "[cloud[ ]net] create new [%-number%] service[s] named %string% " +
                "[[with] runtime %-string%] [[with] auto[ ]delete[ ]on[ ]stop %-boolean%] [[with] static[ ]services %-boolean%] " +
                "[[with] nodes %-string%] [[with] templates %-string%] [[with] deployments %-string%] " +
                "[[with] port %-string%] [[with] groups %-string%] [[with] deleted[ ]files[ ]after[ ]stop %-string%] " +
                "[[with] memory %-string%] [[with] jvmOptions %-string%] [[with] processParameters %-string%] [[with] javaCommand %-string%] [with] type %string%" +
                "[(1¦and start)]");

        Skript.registerExpression(ExprThisService.class, CloudNetServiceInfoSnapshot.class, ExpressionType.SIMPLE,
                "[cloud[ ]net] this [cloud[ ]net] service");
        Skript.registerExpression(ExprCloudServices.class, CloudNetServiceInfoSnapshot.class, ExpressionType.SIMPLE,
                "[the] [cloud[ ]net] cloud service[s]");
        Skript.registerExpression(ExprCloudServicesByTask.class, CloudNetServiceInfoSnapshot.class, ExpressionType.COMBINED,
                "[the] [cloud[ ]net] cloud service[s] by task %string%");
        Skript.registerExpression(ExprCloudServicesByGroup.class, CloudNetServiceInfoSnapshot.class, ExpressionType.COMBINED,
                "[the] [cloud[ ]net] cloud service[s] by group %string%");

        Skript.registerExpression(ExprServiceInfoSnapshotName.class, String.class, ExpressionType.COMBINED, "[cloud[ ]net] [the] name of %serviceinfosnapshot%");
        Skript.registerExpression(ExprServiceInfoSnapshotAddress.class, String.class, ExpressionType.COMBINED, "[cloud[ ]net] [the] address of %serviceinfosnapshot%");
        Skript.registerExpression(ExprServiceInfoSnapshotLifeCycle.class, String.class, ExpressionType.COMBINED, "[cloud[ ]net] [the] life[ ]cycle of %serviceinfosnapshot%");
        Skript.registerExpression(ExprServiceInfoSnapshotOnlineCount.class, Integer.class, ExpressionType.COMBINED, "[cloud[ ]net] [the] online player[s] count of %serviceinfosnapshot%");
    }

}
