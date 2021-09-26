package chronosacaria.crowningglory.configs;

import net.minecraft.entity.EquipmentSlot;

import java.util.EnumMap;

public class CrownStats {
    public EnumMap<EquipmentSlot, Integer> protection = new EnumMap<>(EquipmentSlot.class);
    public float toughness;
    public float knockbackRes;

    public CrownStats crownProtection(int crownProtection){
        this.protection.put(EquipmentSlot.HEAD, crownProtection);
        return this;
    }

    public CrownStats crownToughness(float toughness){
        this.toughness = toughness;
        return this;
    }

    public CrownStats crownKnockbackRes(float knockbackRes){
        this.knockbackRes = knockbackRes;
        return this;
    }
}
