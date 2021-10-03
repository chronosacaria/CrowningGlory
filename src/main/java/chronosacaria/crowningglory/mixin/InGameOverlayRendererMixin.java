package chronosacaria.crowningglory.mixin;

import chronosacaria.crowningglory.items.Crowns;
import chronosacaria.crowningglory.registry.CrownsRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static chronosacaria.crowningglory.configs.CrowningGloryConfig.config;
import static chronosacaria.crowningglory.effects.CrownEffectID.LAVA_VISION;

@Mixin(InGameOverlayRenderer.class)
@Environment(EnvType.CLIENT)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderFireOverlayOverride(MinecraftClient minecraftClient, MatrixStack matrixStack,
                                                  CallbackInfo ci){
        if (!config.enableCrownEffects.get(LAVA_VISION))
            return;

        if (MinecraftClient.getInstance().player != null && config.enableCrownsRegistration.get(Crowns.RUBY)) {
            if (MinecraftClient.getInstance().player.isOnFire()){
                ItemStack helmetStack = MinecraftClient.getInstance().player.getEquippedStack(EquipmentSlot.HEAD);

                if (helmetStack.getItem() == CrownsRegistry.crownItems.get(Crowns.RUBY).get(EquipmentSlot.HEAD).asItem()){
                    ci.cancel();
                }
            }
        }
    }
}
