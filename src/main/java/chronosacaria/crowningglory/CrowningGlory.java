package chronosacaria.crowningglory;

import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.loottables.CrowningGloryLootTables;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CrowningGlory implements ModInitializer {

    public static final String MOD_ID = "crowningglory";

    public static Identifier ID (String path){
        return new Identifier(MOD_ID, path);
    }

    public static final ItemGroup CROWNS_GROUP = FabricItemGroupBuilder.build(ID("crowns"),
            () -> new ItemStack(CrownsRegistry.crownItems.get(Crowns.DIAMOND).get(EquipmentSlot.HEAD)));

    @Override
    public void onInitialize() {
        CrownsRegistry.init();
        CrowningGloryLootTables.init();
    }
}
