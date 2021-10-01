package chronosacaria.crowningglory.mixin;

import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.crowningglory.configs.CrowningGloryConfig.config;
import static chronosacaria.crowningglory.effects.CrownEffectID.LAVA_VISION;
import static chronosacaria.crowningglory.effects.CrownEffectID.WATER_VISION;

@Mixin(BackgroundRenderer.class)
@Environment(EnvType.CLIENT)
public class BackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;" +
            "setShaderFogEnd(F)V"), cancellable = true)
    private static void lavaFogDensity(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance,
                                   boolean thickFog, CallbackInfo ci) {
        if (!config.enableCrownEffects.get(LAVA_VISION))
            return;

        if (MinecraftClient.getInstance().player != null) {
            ItemStack helmetStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD);
            if (helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.RUBY).get(EquipmentSlot.HEAD).asItem()) {
                if (camera.getSubmersionType() == CameraSubmersionType.LAVA) {
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "applyFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;" +
            "setShaderFogEnd(F)V"), cancellable = true)
    private static void waterFogDensity(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance,
                                    boolean thickFog, CallbackInfo ci) {
        if (!config.enableCrownEffects.get(WATER_VISION))
            return;

        if (MinecraftClient.getInstance().player != null) {
            ItemStack helmetStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD);
            if (helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.AQUAMARINE).get(EquipmentSlot.HEAD).asItem()) {
                if (camera.getSubmersionType() == CameraSubmersionType.WATER) {
                    ci.cancel();
                }
            }
        }
    }
}
