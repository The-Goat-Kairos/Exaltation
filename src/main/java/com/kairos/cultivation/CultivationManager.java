package com.kairos.cultivation;

import com.kairos.cultivation.realms.QiGathering;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CultivationManager {
    private final Map<UUID, CultivationData> playerCultivationData;

    public CultivationManager() {
        playerCultivationData = new HashMap<>();
    }

    public void initializePlayer(Player player) {
        CultivationData newCultivationData = new CultivationData();

        newCultivationData.setCultivationRealm(new QiGathering());

        playerCultivationData.put(player.getUuid(), newCultivationData);
    }

    public CultivationData getCultivationData(Player player) {
        return playerCultivationData.get(player.getUuid());
    }

    public void onPlayerTick(Player player) {
        CultivationData data = getCultivationData(player);
        data.getCultivationRealm().onTick(player, data);
    }
}
