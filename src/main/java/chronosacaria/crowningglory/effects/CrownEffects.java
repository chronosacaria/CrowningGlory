package chronosacaria.crowningglory.effects;

import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class CrownEffects {

    private static final List<Block> FLOWER_WALKER_LIST =
            List.of(Blocks.DANDELION, Blocks.POPPY, Blocks.BLUE_ORCHID,
                    Blocks.ALLIUM, Blocks.AZURE_BLUET, Blocks.RED_TULIP,
                    Blocks.PINK_TULIP, Blocks.WHITE_TULIP, Blocks.ORANGE_TULIP,
                    Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY, Blocks.SUNFLOWER,
                    Blocks.LILAC, Blocks.ROSE_BUSH, Blocks.PEONY);

    public static void applyFlowerWalkerEffect(PlayerEntity playerEntity){
        World world = playerEntity.getEntityWorld();
        BlockPos blockPos = playerEntity.getBlockPos();

        if (playerEntity.isAlive() && world.getTime() % 20 == 0) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);

            if (helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.FLORAL).get(EquipmentSlot.HEAD).asItem()) {
                if (playerEntity.isOnGround() && playerEntity.isSneaking()) {
                    BlockState blockState =
                            FLOWER_WALKER_LIST.get(playerEntity.getRandom().nextInt(FLOWER_WALKER_LIST.size())).getDefaultState();
                    float size = (float) Math.min(16, 2 + 1);
                    BlockPos.Mutable mutablePosition = new BlockPos.Mutable();
                    Iterator blockPosIterator =
                            BlockPos.iterate(blockPos.add(-size, 0.0D, -size), blockPos.add(size, 0.0D, size)).iterator();

                    while (blockPosIterator.hasNext()) {
                        BlockPos blockPos2 = (BlockPos) blockPosIterator.next();
                        if (blockPos2.isWithinDistance(playerEntity.getPos(), size)) {
                            mutablePosition.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                            BlockState checkState = world.getBlockState(mutablePosition);
                            if (checkState.isAir()) {
                                BlockState blockState3 = world.getBlockState(blockPos2);
                                if (blockState3.getMaterial() == Material.AIR && blockState.canPlaceAt(world,
                                        blockPos2) && world.canPlace(blockState, blockPos2,
                                        ShapeContext.absent())) {
                                    world.setBlockState(blockPos2, blockState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void applyFluidWalkingEffect(PlayerEntity playerEntity){
        World world = playerEntity.getEntityWorld();
        BlockPos blockPos = playerEntity.getBlockPos();

        if (playerEntity.isAlive() && world.getTime() % 10 == 0) {
            ItemStack helmetStack = playerEntity.getEquippedStack(EquipmentSlot.HEAD);

            if (helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.FROST).get(EquipmentSlot.HEAD).asItem()) {
                if (playerEntity.isOnGround()) {
                    BlockState blockState = Blocks.FROSTED_ICE.getDefaultState();
                    float size = (float) Math.min(16, 2 + 1);
                    BlockPos.Mutable mutablePosition = new BlockPos.Mutable();
                    Iterator blockPosIterator =
                            BlockPos.iterate(blockPos.add(-size, -1.0D, -size), blockPos.add(size, -1.0D, size)).iterator();

                    while (blockPosIterator.hasNext()) {
                        BlockPos blockPos2 = (BlockPos) blockPosIterator.next();
                        if (blockPos2.isWithinDistance(playerEntity.getPos(), size)) {
                            mutablePosition.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                            BlockState checkState = world.getBlockState(mutablePosition);
                            if (checkState.isAir()) {
                                BlockState blockState3 = world.getBlockState(blockPos2);
                                if (blockState3.getMaterial() == Material.WATER && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                                    world.setBlockState(blockPos2, blockState);
                                    world.getBlockTickScheduler().schedule(blockPos2, Blocks.FROSTED_ICE,
                                            MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
                if (playerEntity.isOnGround()) {
                    BlockState blockState = Blocks.CRYING_OBSIDIAN.getDefaultState();
                    float size = (float) Math.min(16, 2 + 1);
                    BlockPos.Mutable mutablePosition = new BlockPos.Mutable();
                    Iterator blockPosIterator = BlockPos.iterate(blockPos.add(-size, -1.0D, -size), blockPos.add(size, -1.0D, size)).iterator();

                    while (blockPosIterator.hasNext()) {
                        BlockPos blockPos2 = (BlockPos) blockPosIterator.next();
                        if (blockPos2.isWithinDistance(playerEntity.getPos(), size)) {
                            mutablePosition.set(blockPos2.getX(), blockPos2.getY() + 1, blockPos2.getZ());
                            BlockState checkState = world.getBlockState(mutablePosition);
                            if (checkState.isAir()) {
                                BlockState blockState3 = world.getBlockState(blockPos2);
                                if (blockState3.getMaterial() == Material.LAVA && blockState3.get(FluidBlock.LEVEL) == 0 && blockState.canPlaceAt(world, blockPos2) && world.canPlace(blockState, blockPos2, ShapeContext.absent())) {
                                    world.setBlockState(blockPos2, blockState);
                                    world.getBlockTickScheduler().schedule(blockPos2, Blocks.CRYING_OBSIDIAN,
                                            MathHelper.nextInt(playerEntity.getRandom(), 60, 120));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
