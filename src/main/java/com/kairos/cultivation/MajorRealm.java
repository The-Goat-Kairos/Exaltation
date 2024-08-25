package com.kairos.cultivation;

import net.minestom.server.entity.Player;

public interface MajorRealm {
    void onEnter(Player player, CultivationData data);
    void onExit(Player player, CultivationData data);
    boolean canAdvance(Player player, CultivationData data);
    void onTick(Player player, CultivationData data);
    MajorRealm next();
    String getName();
}
