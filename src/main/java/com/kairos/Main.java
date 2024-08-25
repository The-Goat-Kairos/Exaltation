package com.kairos;

import io.github.togar2.pvp.MinestomPvP;
import io.github.togar2.pvp.feature.CombatFeatureSet;
import io.github.togar2.pvp.feature.CombatFeatures;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;

import java.net.InetSocketAddress;

public class Main {
    public static CombatFeatureSet COMBAT_FEATURE_SET;
    public static void main(String[] args) {
        MinestomPvP.init();

        COMBAT_FEATURE_SET = CombatFeatures.empty()
                .add(CombatFeatures.VANILLA_EXPLOSION)
                .add(CombatFeatures.VANILLA_FALL)
                .add(CombatFeatures.VANILLA_PLAYER_STATE)
                .add(CombatFeatures.VANILLA_ARMOR)
                .add(CombatFeatures.VANILLA_ATTACK)
                .add(CombatFeatures.VANILLA_ATTACK_COOLDOWN)
                .add(CombatFeatures.VANILLA_CRITICAL)
                .add(CombatFeatures.VANILLA_DAMAGE)
                .add(CombatFeatures.VANILLA_DEATH_MESSAGE)
                .add(CombatFeatures.VANILLA_EFFECT)
                .add(CombatFeatures.VANILLA_EQUIPMENT)
                .add(CombatFeatures.VANILLA_KNOCKBACK)
                .add(CombatFeatures.VANILLA_SPECTATE)
                .add(CombatFeatures.VANILLA_SWEEPING)
                .add(CombatFeatures.VANILLA_TOTEM)
                .add(CombatFeatures.VANILLA_FOOD)
                .build();

        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit -> unit.modifier().fillHeight(0, 40, getRandomBlock()));

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(8, 42, 8));
        });

        minecraftServer.start(new InetSocketAddress(25565));
        System.out.println("Hello world!");
    }

    private static Block getRandomBlock() {
        return Block.GRASS_BLOCK;
    }
}