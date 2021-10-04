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

    public static final Collection<Identifier> AQUAMARINE_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, SHIPWRECK_TREASURE_CHEST
            )));

    public static final Collection<Identifier> AZURE_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, WOODLAND_MANSION_CHEST
            )));

    public static final Collection<Identifier> CATSEYE_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, JUNGLE_TEMPLE_CHEST
            )));

    public static final Collection<Identifier> DIAMOND_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST
            )));

    public static final Collection<Identifier> FLORAL_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST
            )));

    public static final Collection<Identifier> ENDER_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    STRONGHOLD_CORRIDOR_CHEST, STRONGHOLD_CROSSING_CHEST, STRONGHOLD_LIBRARY_CHEST, END_CITY_TREASURE_CHEST
            )));

    public static final Collection<Identifier> FROST_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, ABANDONED_MINESHAFT_CHEST, VILLAGE_ARMORER_CHEST, IGLOO_CHEST_CHEST
            )));

    public static final Collection<Identifier> RUBY_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    NETHER_BRIDGE_CHEST, RUINED_PORTAL_CHEST
            )));

    public static final Collection<Identifier> VALKYRIE_CROWN_LOOT_TABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SIMPLE_DUNGEON_CHEST, STRONGHOLD_LIBRARY_CHEST
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

            if (AQUAMARINE_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.AQUAMARINE) && config.enableCrownsRegistration.get(Crowns.AQUAMARINE)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.AQUAMARINE, config.crownsSpawnRate.get(Crowns.AQUAMARINE));
                supplier.pool(poolBuilder);
            } else if (AZURE_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.AZURE) && config.enableCrownsRegistration.get(Crowns.AZURE)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.AZURE, config.crownsSpawnRate.get(Crowns.AZURE));
                supplier.pool(poolBuilder);
            } else if (CATSEYE_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.CATSEYE) && config.enableCrownsRegistration.get(Crowns.CATSEYE)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.CATSEYE, config.crownsSpawnRate.get(Crowns.CATSEYE));
                supplier.pool(poolBuilder);
            } else if (DIAMOND_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.DIAMOND) && config.enableCrownsRegistration.get(Crowns.DIAMOND)){
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.DIAMOND, config.crownsSpawnRate.get(Crowns.DIAMOND));
                supplier.pool(poolBuilder);
            } else if (ENDER_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.ENDER) && config.enableCrownsRegistration.get(Crowns.ENDER)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.ENDER, config.crownsSpawnRate.get(Crowns.ENDER));
                supplier.pool(poolBuilder);
            } else if (FLORAL_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.FLORAL) && config.enableCrownsRegistration.get(Crowns.FLORAL)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.FLORAL, config.crownsSpawnRate.get(Crowns.FLORAL));
                supplier.pool(poolBuilder);
            } else if (FROST_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.FROST) && config.enableCrownsRegistration.get(Crowns.FROST)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.FROST, config.crownsSpawnRate.get(Crowns.FROST));
                supplier.pool(poolBuilder);
            } else if (RUBY_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.RUBY) && config.enableCrownsRegistration.get(Crowns.RUBY)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.RUBY, config.crownsSpawnRate.get(Crowns.RUBY));
                supplier.pool(poolBuilder);
            } else if (VALKYRIE_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.VALKYRIE) && config.enableCrownsRegistration.get(Crowns.VALKYRIE)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.VALKYRIE, config.crownsSpawnRate.get(Crowns.VALKYRIE));
                supplier.pool(poolBuilder);
            } else if (WREATH_CROWN_LOOT_TABLE.contains(id) && config.enableCrownSpawning.get(Crowns.WREATH) && config.enableCrownsRegistration.get(Crowns.WREATH)) {
                poolBuilder = FabricLootPoolBuilder.builder();
                addCrown(poolBuilder, Crowns.WREATH, config.crownsSpawnRate.get(Crowns.WREATH));
                supplier.pool(poolBuilder);
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
