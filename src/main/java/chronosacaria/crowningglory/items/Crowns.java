package chronosacaria.crowningglory.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;
import net.minecraft.util.Rarity;

import java.util.function.Supplier;

import static chronosacaria.crowningglory.configs.CrowningGloryConfig.config;
import static net.minecraft.sound.SoundEvents.*;

public enum Crowns implements ArmorMaterial {

    AQUAMARINE("aquamarinecirclet", "aquamarine",
            40,
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.PRISMARINE_SHARD);
    }),
    DIAMOND("diamondcoronet", "diamond",
            40,
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.DIAMOND);
    }),
    FLORAL("floralcrown", "floral",
            40,
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> {return Ingredient.ofItems(Items.DANDELION);
    }),
    FROST("frostcirclet", "frost",
            40,
            25,
            ITEM_ARMOR_EQUIP_IRON,
            () -> {return Ingredient.ofItems(Items.BLUE_ICE);
    }),
    RUBY("rubydiadem", "ruby",
            40,
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.BLAZE_POWDER);
    }),
    WREATH("wreathcrown", "wreath",
            40,
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> {return Ingredient.ofItems(Items.DANDELION);
    });

    private static final int[] baseDurability = new int[]{12, 14, 15, 10};
    private final String textureName;
    private final String crownName;
    private final int durabilityMultiplier;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Lazy<Ingredient> repairIngredient;

    Crowns(String textureName, String crownName, int durabilityMultiplier, int enchantability, SoundEvent soundEvent,
           Supplier<Ingredient> repairIngredient){
        this.textureName = textureName;
        this.crownName = crownName;
        this.durabilityMultiplier = durabilityMultiplier;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    public String getCrownName(){
        return crownName;
    }

    public Rarity getRarity(){
        switch (this){
            case DIAMOND:
            case FROST:
            case RUBY:
                return Rarity.RARE;
            default:
                return Rarity.UNCOMMON;
        }
    }

    @Override
    public int getDurability(EquipmentSlot slot){
        return baseDurability[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return config.crownStats.get(this).protection.get(slot);
    }

    @Override
    public int getEnchantability(){
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound(){
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient(){
        return this.repairIngredient.get();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public String getName(){
        return this.textureName;
    }

    @Override
    public float getToughness(){
        return config.crownStats.get(this).toughness;
    }

    @Override
    public float getKnockbackResistance(){
        return config.crownStats.get(this).knockbackRes;
    }
}
