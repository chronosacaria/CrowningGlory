package chronosacaria.crowningglory.configs;

import chronosacaria.crowningglory.CrowningGlory;
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

    public EnumMap<Crowns, CrownStats> crownStats = new EnumMap<>(Crowns.class);

    // convenience methods:
    protected CrownStats crownProtection(int crownProtection, Crowns crowns){
        return crownStats.get(crowns).crownProtection(crownProtection);
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

        crownProtection(4, DIAMOND);
        crownProtection(2, FLORAL);
    }
}
