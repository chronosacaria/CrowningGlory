package chronosacaria.crowningglory.registry;

import chronosacaria.crowningglory.CrowningGlory;
import chronosacaria.crowningglory.items.CrownItem;
import chronosacaria.crowningglory.items.Crowns;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.EnumMap;
import java.util.EnumSet;

public class CrownsRegistry {

    // (crown, slot) -> item
    public static final EnumMap<Crowns, EnumMap<EquipmentSlot, Item>> crownItems = new EnumMap<>(Crowns.class);

    protected static String crownID(Crowns crown, EquipmentSlot slot){
        String slotID;
        if (slot == EquipmentSlot.HEAD) {
            slotID = "crown";
        } else {
            throw new IllegalArgumentException("crown with non-head equipment slot");
        }
        return crown.getCrownName() + "_" + slotID;
    }

    protected static void registerCrowns(Crowns crown, EnumSet<EquipmentSlot> slots){
        EnumMap<EquipmentSlot, Item> slotMap = new EnumMap<>(EquipmentSlot.class);

        for (EquipmentSlot slot : slots){
            CrownItem item = new CrownItem(crown, slot);
            slotMap.put(slot, item);
            Registry.register(Registry.ITEM, CrowningGlory.ID(crownID(crown, slot)), item);
        }
        crownItems.put(crown, slotMap);
    }

    public static void init(){
        for (Crowns crown : Crowns.values()){
            EnumSet<EquipmentSlot> slot;

            slot = EnumSet.of(EquipmentSlot.HEAD);
            registerCrowns(crown, slot);
        }
    }
}
