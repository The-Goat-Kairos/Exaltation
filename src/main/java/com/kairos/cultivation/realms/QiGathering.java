package com.kairos.cultivation.realms;

import com.kairos.cultivation.CultivationData;
import com.kairos.cultivation.MajorRealm;
import net.minestom.server.entity.Player;

public class QiGathering implements MajorRealm {
    private static final String name = "Qi Gathering";
    @Override
    public void onEnter(Player player, CultivationData data) {

    }

    @Override
    public void onExit(Player player, CultivationData data) {

    }

    @Override
    public boolean canAdvance(Player player, CultivationData data) {
        return false;
    }

    @Override
    public void onTick(Player player, CultivationData data) {
        player.sendMessage(data.getCultivationRealm().getName());
    }

    @Override
    public MajorRealm next() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
