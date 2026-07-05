package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.handlers.AlphaesScreenHandlers;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreen;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.util.DyeColor;

import java.util.Objects;

import static com.ortodontalio.alphaesletters.common.HasColor.COLOR;
import static com.ortodontalio.alphaesletters.common.LetterBasic.COVER_COLOR;
import static com.ortodontalio.alphaesletters.tech.TechBlocks.STRIKETHROUGH_BLOCK;

@Environment(EnvType.CLIENT)
public class AlphaesLettersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, DyeingMachineScreen::new);

        var coloredBlocks = AlphaesUtils.getAllLetterBlocks();
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                    if (tintIndex == 0) {
                        return state.get(COLOR).getSignColor();
                    }
                    if (tintIndex == 1) {
                        return state.get(COVER_COLOR).getSignColor();
                    }
                    return -1;
                },
                coloredBlocks.toArray(Block[]::new));
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) ->
                state.get(COLOR).getSignColor(), STRIKETHROUGH_BLOCK);

        coloredBlocks.add(STRIKETHROUGH_BLOCK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    BlockStateComponent component = stack.get(DataComponentTypes.BLOCK_STATE);
                    if (component == null) {
                        return DyeColor.WHITE.getSignColor();
                    }
                    return Objects.requireNonNull(component.getValue(COLOR)).getSignColor();
                },
                coloredBlocks.toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), coloredBlocks.toArray(Block[]::new));
    }
}
