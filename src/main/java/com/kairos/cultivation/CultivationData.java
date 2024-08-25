package com.kairos.cultivation;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CultivationData {
    private MajorRealm majorRealm;
    private int qi;
    private Set<Dao> affinities;
    private Map<Dao, Integer> insights;

    public void increaseQi(int amount) {
        this.qi += amount;
    }

    public void addInsight(Dao dao, int amount) {
        insights.put(dao, insights.getOrDefault(dao, 0) + amount);
    }

    public void setInsight(Dao dao, int amount) {
        insights.put(dao, amount);
    }

    public MajorRealm getCultivationRealm() {
        return this.majorRealm;
    }

    public void setCultivationRealm(MajorRealm realm) {
        this.majorRealm = realm;
    }

    public void advanceRealm() {
        //Stuff
    }
}
