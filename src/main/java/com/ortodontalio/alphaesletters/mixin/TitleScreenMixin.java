package com.ortodontalio.alphaesletters.mixin;

import com.ortodontalio.alphaesletters.addon_system.AddonResourceLoader;
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
        var text = Text.translatable("title.alphaesletters.addons_counter",
                AddonResourceLoader.getAddonsBlocks().size());
        int textWidth = textRenderer.getWidth(text);
        int xPos = width - textWidth - 2;
        int yPos = height - 22;
        context.drawTextWithShadow(client.textRenderer, text, xPos, yPos, 0xFFFFFF);
    }
}
