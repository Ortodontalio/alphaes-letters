package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.codegen.CyrillicLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.CyrillicLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.LatinLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.LatinLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.MinecraftLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.MinecraftLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.MiscLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.MiscLettersRegistrator;
import com.ortodontalio.alphaesletters.entity.AlphaesBlockEntities;
import com.ortodontalio.alphaesletters.recipe.AlphaesRecipes;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import static com.ortodontalio.alphaesletters.common.LetterBasic.COLOR;
import static com.ortodontalio.alphaesletters.tech.TechBlocks.CROPPED_LETTER_CONCRETE;
import static com.ortodontalio.alphaesletters.tech.TechBlocks.STRIKETHROUGH_BLOCK;


public class AlphaesLetters implements ModInitializer {

    public static final String MOD_ID = "alphaesletters";
    public static final String COMMAND_TEXT = """
            Dear friend! When I created this mod, in addition to using it to create road signs,
            beautiful steles, marking streets, buildings, and so on, which was dedicated to my
            desire to create maps with railway travel, I hope that someday this mod will be
            noticed by my old friends with whom I once played on the server back in 2013.
            Unfortunately, due to technical problems, the server stopped working without a
            trace, which is why I lost contact with my old friends. Let me know if you are
            familiar with Ryaman, kalash470, Dumb (or Damb, I don't remember exactly).""";
    private final TechBlocks techBlocks = new TechBlocks();
    private final TechBlockItems techBlockItems = new TechBlockItems();

    @Override
    public void onInitialize() {
        CyrillicLettersRegistrator.registerAll();
        LatinLettersRegistrator.registerAll();
        MiscLettersRegistrator.registerAll();
        MinecraftLettersRegistrator.registerAll();
        techBlocks.registerBlocks();

        CyrillicLettersItemsRegistrator.registerAll();
        LatinLettersItemsRegistrator.registerAll();
        MiscLettersItemsRegistrator.registerAll();
        MinecraftLettersItemsRegistrator.registerAll();
        techBlockItems.registerBlockItems();
        techBlockItems.registerGroup(TechBlockItems.LETTER_POWDER);

        AlphaesBlockEntities.registerEntities();
        AlphaesRecipes.registerRecipes();

//        CommandRegistrationCallback.EVENT.register(
//                (dispatcher, registryAccess, environment) -> dispatcher.register(literal("alread")
//                .executes(context -> {
//                    ClientPlayerEntity player = MinecraftClient.getInstance().player;
//                    if (player != null) {
//                        player.sendMessage(Text.literal(COMMAND_TEXT));
//                    }
//                    return 1;
//                })));
    }
}
