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
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.PRISMARINE_SHARD);
    }),
    AZURE("azuresadvantageousadornment", "azures",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.LAPIS_BLOCK);
            }),
    CATSEYE("catseyecirclet", "catseye",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.GUNPOWDER);
            }),
    DIAMOND("diamondcoronet", "diamond",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.DIAMOND);
    }),
    ENDER("enderdiadem", "ender",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.ENDER_PEARL);
            }),
    FLORAL("floralcrown", "floral",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> {return Ingredient.ofItems(Items.DANDELION);
    }),
    FROST("frostcirclet", "frost",
            25,
            ITEM_ARMOR_EQUIP_IRON,
            () -> {return Ingredient.ofItems(Items.BLUE_ICE);
    }),
    RUBY("rubydiadem", "ruby",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.BLAZE_POWDER);
    }),
    VALKYRIE("valkyrielaurel", "valkyrie",
            25,
            ITEM_ARMOR_EQUIP_DIAMOND,
            () -> {return Ingredient.ofItems(Items.FEATHER);
    }),
    WREATH("wreathcrown", "wreath",
            25,
            ITEM_ARMOR_EQUIP_ELYTRA,
            () -> {return Ingredient.ofItems(Items.DANDELION);
    });

    private static final int[] baseDurability = new int[]{12, 14, 15, 10};
    private final String textureName;
    private final String crownName;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final Lazy<Ingredient> repairIngredient;

    Crowns(String textureName, String crownName, int enchantability, SoundEvent soundEvent,
           Supplier<Ingredient> repairIngredient){
        this.textureName = textureName;
        this.crownName = crownName;
        this.enchantability = enchantability;
        this.equipSound = soundEvent;
        this.repairIngredient = new Lazy<>(repairIngredient);
    }

    public String getCrownName(){
        return crownName;
    }

    public Rarity getRarity(){
        switch (this){
            case AZURE:
            case VALKYRIE:
                return Rarity.EPIC;
            case AQUAMARINE:
            case CATSEYE:
            case DIAMOND:
            case ENDER:
            case FROST:
            case RUBY:
                return Rarity.RARE;
            default:
                return Rarity.UNCOMMON;
        }
    }

    @Override
    public int getDurability(EquipmentSlot slot){
        return baseDurability[slot.getEntitySlotId()] * config.crownStats.get(this).durabilityMultiplier;
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
