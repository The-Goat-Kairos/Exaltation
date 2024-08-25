package com.kairos;

import com.kairos.cultivation.CultivationManager;
import io.github.togar2.pvp.MinestomPvP;
import io.github.togar2.pvp.feature.CombatFeatureSet;
import io.github.togar2.pvp.feature.CombatFeatures;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.timer.TaskSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        MinestomPvP.init();
        CombatFeatureSet modernVanilla = CombatFeatures.modernVanilla();
        MinecraftServer.getGlobalEventHandler().addChild(modernVanilla.createNode());

        instanceContainer.setGenerator(World::generate);
        instanceContainer.setChunkSupplier(LightingChunk::new);

        CultivationManager cultivationManager = new CultivationManager();

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(8, 42, 8));
            player.setGameMode(GameMode.CREATIVE);
            cultivationManager.initializePlayer(player);
        });

        MinecraftServer.getSchedulerManager().scheduleTask(() -> {
           for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                cultivationManager.onPlayerTick(player);
           }
        }, TaskSchedule.tick(100), TaskSchedule.tick(100));

        minecraftServer.start(new InetSocketAddress(25565));
        LOGGER.info("Hello, World!");
    }
}