package chronosacaria.crowningglory.mixin;

import chronosacaria.crowningglory.configs.CrowningGloryConfig;
import chronosacaria.crowningglory.effects.CrownEffectID;
import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

import static chronosacaria.crowningglory.configs.CrowningGloryConfig.config;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin extends HostileEntity {

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void injectCatsEyeCreeperScareEffectGoal(CallbackInfo ci) {
        if (!CrowningGloryConfig.config.enableCrownEffects.get(CrownEffectID.CREEPER_SCARE))
            return;

        Predicate<ServerPlayerEntity> cats_eye_creeper_effect_predicate = (entity) -> {
            ItemStack helmetStack = entity.getEquippedStack(EquipmentSlot.HEAD);
            if (entity.isAlive() && config.enableCrownsRegistration.get(Crowns.CATSEYE) && helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.CATSEYE).get(EquipmentSlot.HEAD).asItem()) {
                return config.enableCrownEffects.get(CrownEffectID.CREEPER_SCARE);
            }
            return false;
        };


        goalSelector.add(0, new FleeEntityGoal(this, ServerPlayerEntity.class, cats_eye_creeper_effect_predicate,
                10.0F, 1.0D, 1.2D,
                EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR));
    }
}

