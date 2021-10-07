package chronosacaria.crowningglory.configs;

import chronosacaria.crowningglory.CrowningGlory;
import chronosacaria.crowningglory.effects.CrownEffectID;
import chronosacaria.crowningglory.items.Crowns;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;
import java.util.EnumSet;

import static chronosacaria.crowningglory.items.Crowns.*;

@Config(name = CrowningGlory.MOD_ID)
public class CrowningGloryConfig implements ConfigData {
    public static final CrowningGloryConfig config;

    static {
        AutoConfig.register(CrowningGloryConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(CrowningGloryConfig.class).getConfig();
    }

    public EnumMap<Crowns, Boolean> enableCrownsRegistration = new EnumMap<>(Crowns.class);
    public EnumMap<Crowns, CrownStats> crownStats = new EnumMap<>(Crowns.class);
    public EnumMap<CrownEffectID, Boolean> enableCrownEffects = new EnumMap<>(CrownEffectID.class);
    public EnumMap<Crowns, Boolean> enableCrownSpawning = new EnumMap<>(Crowns.class);
    public EnumMap<Crowns, Float> crownsSpawnRate = new EnumMap<>(Crowns.class);

    // convenience methods:
    protected CrownStats crownProtection(int value, Crowns crowns){
        return crownStats.get(crowns).crownProtection(value);
    }
    protected CrownStats crownDurabilityMultiplier(int value, Crowns crowns) {
        return crownStats.get(crowns).crownDurabilityMultiplier(value);
    }
    protected CrownStats setKnockbackResistance(float value, Crowns crowns) {
        return crownStats.get(crowns).crownKnockbackRes(value);
    }

    // crown config defaults
    public CrowningGloryConfig(){
        for (Crowns crowns : Crowns.values()) {
            crownStats.put(crowns, new CrownStats());
        }

        for (Crowns crowns : Crowns.values()) {
            CrownStats stats = new CrownStats();
            stats.protection = new EnumMap<>(EquipmentSlot.class);
            for (EquipmentSlot slot : EnumSet.of(EquipmentSlot.HEAD)) {
                stats.protection.put(slot, 0);
            }

            this.crownStats.put(crowns, stats);
        }

        crownProtection(3, AZURE).crownDurabilityMultiplier(15);
        crownProtection(2, CATSEYE).crownDurabilityMultiplier(15);
        crownProtection(4, DIAMOND).crownToughness(2).crownDurabilityMultiplier(30);
        crownProtection(3, ENDER).crownDurabilityMultiplier(10);
        crownProtection(2, FLORAL).crownDurabilityMultiplier(5);
        crownProtection(3, FROST).crownDurabilityMultiplier(15);
        crownProtection(3, PRISMARINE).crownDurabilityMultiplier(15);
        crownProtection(3, RUBY).crownDurabilityMultiplier(15);
        crownProtection(3, VALKYRIE).crownDurabilityMultiplier(15);
        crownProtection(2, WREATH).crownDurabilityMultiplier(5);


        for (Crowns crowns : Crowns.values()){
            enableCrownsRegistration.put(crowns, true);
        }

        for (Crowns crowns : Crowns.values()){
            enableCrownSpawning.put(crowns, true);
        }

        for (CrownEffectID crownEffectID : CrownEffectID.values()){
            enableCrownEffects.put(crownEffectID, true);
        }

        for (Crowns crowns : Crowns.values()){
            crownsSpawnRate.put(AZURE, 0.10f);
            crownsSpawnRate.put(CATSEYE, 0.20f);
            crownsSpawnRate.put(DIAMOND, 0.20f);
            crownsSpawnRate.put(ENDER, 0.20f);
            crownsSpawnRate.put(FLORAL, 0.30f);
            crownsSpawnRate.put(FROST, 0.20f);
            crownsSpawnRate.put(PRISMARINE, 0.25f);
            crownsSpawnRate.put(RUBY, 0.20f);
            crownsSpawnRate.put(VALKYRIE, 0.10f);
            crownsSpawnRate.put(WREATH, 0.30f);
        }
    }
}
