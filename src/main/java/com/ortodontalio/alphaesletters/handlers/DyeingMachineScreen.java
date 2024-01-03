package com.ortodontalio.alphaesletters.handlers;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.AlphaesLetters.MOD_ID;

public class DyeingMachineScreen extends HandledScreen<DyeingMachineScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/gui/dyeing_machine_gui.png");

    public DyeingMachineScreen(DyeingMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        backgroundHeight = 176;
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        playerInventoryTitleX = 88;
        playerInventoryTitleY = 83;
    }

    @Override
    protected void drawBackground(DrawContext matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        matrices.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        if (handler.isCrafting()) {
            matrices.drawTexture(TEXTURE, x + 103, y + 44, 176, 14, handler.getScaledProgress(), 10);
        }

        if (handler.hasFuel()) {
            matrices.drawTexture(TEXTURE, x + 41, y + 42 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 18, handler.getScaledFuelProgress());
        }
    }

    @Override
    public void render(DrawContext matrices, int mouseX, int mouseY, float delta) {
        renderBackgroundTexture(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

}
