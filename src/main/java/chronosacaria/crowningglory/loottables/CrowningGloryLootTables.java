package chronosacaria.crowningglory.loottables;

import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static chronosacaria.crowningglory.configs.CrowningGloryConfig.*;
import static net.minecraft.loot.LootTables.*;

public class CrowningGloryLootTables {

    public static final Collection<Identifier> DIAMOND_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST
            )));

    public static final Collection<Identifier> FLORAL_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST
            )));

    public static final Collection<Identifier> FROST_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST, IGLOO_CHEST_CHEST
            )));

    public static final Collection<Identifier> RUBY_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    NETHER_BRIDGE_CHEST, RUINED_PORTAL_CHEST
            )));

    public static final Collection<Identifier> WREATH_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST
            )));


    public static void init(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (!id.getNamespace().equals("minecraft"))
                return;

            FabricLootPoolBuilder poolBuilder;

            if (DIAMOND_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.DIAMOND)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.DIAMOND, config.crownsSpawnRate.get(Crowns.DIAMOND));
                supplier.pool(poolBuilder);
            } else if (FLORAL_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.FLORAL)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.FLORAL, config.crownsSpawnRate.get(Crowns.FLORAL));
                supplier.pool(poolBuilder);
            } else if (FROST_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.FROST)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.FROST, config.crownsSpawnRate.get(Crowns.FROST));
            } else if (RUBY_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.RUBY)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.RUBY, config.crownsSpawnRate.get(Crowns.RUBY));
            } else if (WREATH_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.WREATH)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.WREATH, config.crownsSpawnRate.get(Crowns.WREATH));
            }
        });
    }

    public static void addCrown(FabricLootPoolBuilder poolBuilder, Crowns crowns, float p){
        CrownsRegistry.crownItems.get(crowns).values()
                .forEach((item -> {
                    poolBuilder.rolls(BinomialLootNumberProvider.create(1, p));
                    poolBuilder.with(ItemEntry.builder(item));
                }));
    }
}
