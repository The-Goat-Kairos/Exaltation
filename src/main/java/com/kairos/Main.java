package com.kairos;

import io.github.togar2.pvp.MinestomPvP;
import io.github.togar2.pvp.feature.CombatFeatureSet;
import io.github.togar2.pvp.feature.CombatFeatures;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        MinestomPvP.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        CombatFeatureSet modernVanilla = CombatFeatures.modernVanilla();
        MinecraftServer.getGlobalEventHandler().addChild(modernVanilla.createNode());

        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.STONE);
            Point start = unit.absoluteStart();
            unit.fork(setter -> {
               for (int x = 0; x <= 16; x++){
                   for (int z = 0; z <= 16; z++) {
                       Block randomBlock = ListUtils.random(List.of(
                               Block.GRASS_BLOCK,
                               Block.MOSS_BLOCK,
                               Block.GREEN_CONCRETE_POWDER)
                       );
                       setter.setBlock(start.add(x, 0, z).withY(40), randomBlock);
                   }
               }
            });
        });

        instanceContainer.setChunkSupplier(LightingChunk::new);
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(8, 42, 8));
            player.setGameMode(GameMode.CREATIVE);
        });

        minecraftServer.start(new InetSocketAddress(25565));
        LOGGER.info("Hello, World!");
    }
}