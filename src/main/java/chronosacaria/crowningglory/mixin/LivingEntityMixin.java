package chronosacaria.crowningglory.mixin;

import chronosacaria.crowningglory.effects.CrownEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void crownTickEffects(CallbackInfo ci){
        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        CrownEffects.applyFlowerWalkerEffect(playerEntity);
        CrownEffects.applyFluidWalkingEffect(playerEntity);
        CrownEffects.applyLavaSwimmerEffect(playerEntity);
        CrownEffects.applyGrowthEffect(playerEntity);
        CrownEffects.applyWaterBreathingEffect(playerEntity);
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void voidTeleportationProtectionDamageProtection(DamageSource source, float amount,
                                                             CallbackInfoReturnable<Boolean> cir){

        if(!((Object)this instanceof PlayerEntity)) return;

        PlayerEntity playerEntity = (PlayerEntity) (Object) this;

        CrownEffects.voidProtectionTeleportEffect(playerEntity);
    }
}
