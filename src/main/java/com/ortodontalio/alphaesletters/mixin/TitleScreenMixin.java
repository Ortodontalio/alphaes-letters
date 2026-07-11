package com.ortodontalio.alphaesletters.mixin;

import com.ortodontalio.alphaesletters.util.AddonLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        context.drawTextWithShadow(
                client.textRenderer,
                Text.translatable("title.alphaesletters.addons_counter",
                        AddonLoader.getAddonsBlocks().size()),
                2,
                this.height - 22,
                0xFFFFFF
        );
    }
}
