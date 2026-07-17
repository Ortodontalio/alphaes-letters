package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.addon_system.AddonResourceLoader;
import com.ortodontalio.alphaesletters.client.TintRegistries;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import com.ortodontalio.alphaesletters.entity.LetterBasicEntity;
import com.ortodontalio.alphaesletters.handlers.AlphaesScreenHandlers;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreen;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.common.HasColor.COLOR;
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
                    if (tintIndex == 1 && view != null && pos != null) {
                        BlockEntity be = view.getBlockEntity(pos);
                        if (be instanceof LetterBasicEntity letterBE) {
                            if (state.get(LetterBasic.HAS_COVER)) {
                                return letterBE.getCoverColor().getSignColor();
                            }
                        }
                    }
                    return -1;
                },
                coloredBlocks.toArray(Block[]::new));
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) ->
                state.get(COLOR).getSignColor(), STRIKETHROUGH_BLOCK);
        coloredBlocks.add(STRIKETHROUGH_BLOCK);
        TintRegistries.registerAll();
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), coloredBlocks.toArray(Block[]::new));

        // Register Addon Virtual ResourcePack
        ModContainer modContainer = FabricLoader.getInstance()
                .getModContainer(AlphaesLetters.MOD_ID)
                .orElseThrow(() -> new RuntimeException("Mod container not found"));
        ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of(AlphaesLetters.MOD_ID, "openletters_generated"),
                modContainer,
                ResourcePackActivationType.ALWAYS_ENABLED
        );
    }
}
