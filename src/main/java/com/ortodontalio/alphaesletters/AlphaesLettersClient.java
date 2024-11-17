package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.common.HasColor;
import com.ortodontalio.alphaesletters.handlers.AlphaesScreenHandlers;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreen;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

import static com.ortodontalio.alphaesletters.tech.TechBlocks.*;

@Environment(EnvType.CLIENT)
public class AlphaesLettersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, DyeingMachineScreen::new);

        var coloredBlocks = AlphaesUtils.getAllLetterBlocks();
        coloredBlocks.add(CROPPED_LETTER_CONCRETE);
        coloredBlocks.add(STRIKETHROUGH_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> ((HasColor) state.getBlock()).getColor(state),
                coloredBlocks.toArray(Block[]::new));

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), coloredBlocks.toArray(Block[]::new));
        //BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), SYMBOL_WALL);
    }
}
