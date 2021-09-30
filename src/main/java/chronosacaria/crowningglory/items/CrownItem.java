package chronosacaria.crowningglory.items;

import chronosacaria.crowningglory.CrowningGlory;
import chronosacaria.crowningglory.configs.CrownStats;
import chronosacaria.crowningglory.configs.CrowningGloryConfig;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class CrownItem extends ArmorItem {

    protected static final int MAX_TOOLTIPS = 5;

    protected static final UUID [] ARMOR_MODIFIER = new UUID[] {
            UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    protected final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    protected final Crowns crown;

    public CrownItem(Crowns crown, EquipmentSlot slot){
        super(crown, slot, new Item.Settings().group(CrowningGlory.CROWNS_GROUP));
        this.crown = crown;

        int protection = crown.getProtectionAmount(slot);
        float toughness = crown.getToughness();

        ImmutableMultimap.Builder<EntityAttribute,EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER[slot.getEntitySlotId()];
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "Armor modifier",
                protection, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "Armor toughness",
                toughness, EntityAttributeModifier.Operation.ADDITION));
        if(this.knockbackResistance > 0) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uuid, "Armor knockback resistance",
                    this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }

        CrownStats crownStats = CrowningGloryConfig.config.crownStats.get(crown);
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot){
        return slot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public Rarity getRarity(ItemStack itemStack){
        return crown.getRarity();
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        super.appendTooltip(itemStack, world, tooltip, tooltipContext);

        for (int i = 1; i <= MAX_TOOLTIPS; i++) {
            String crownId;

            crownId = crown.getCrownName();

            String translationKey = String.format("item.crowningglory.%s_crown.tooltip_%d", crownId, i);

            String moreInfoTranslationKey = String.format("item.crowningglory.%s_crown.moreinfo.tooltip_%d", crownId, i);

            if (I18n.hasTranslation(translationKey)) {
                tooltip.add(new TranslatableText(translationKey));
            }

            if (I18n.hasTranslation(moreInfoTranslationKey) && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)){
                tooltip.remove(new TranslatableText(translationKey));
                tooltip.add(new TranslatableText(moreInfoTranslationKey));
            }
        }
    }

}
