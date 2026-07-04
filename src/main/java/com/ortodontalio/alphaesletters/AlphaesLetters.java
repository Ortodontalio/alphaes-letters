package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.codegen.CyrillicLettersItemsRegistrator;
import com.ortodontalio.alphaesletters.codegen.CyrillicLettersRegistrator;
import com.ortodontalio.alphaesletters.codegen.GroupRegistrator;
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
import com.ortodontalio.alphaesletters.util.AddonLoader;
import com.ortodontalio.alphaesletters.util.ExfoliatableRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;


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

    @SuppressWarnings("unchecked")
    @Override
    public void onInitialize() {
        LatinLettersRegistrator.registerAll();
        CyrillicLettersRegistrator.registerAll();
        MiscLettersRegistrator.registerAll();
        MinecraftLettersRegistrator.registerAll();
        TechBlocks.registerAll();
        ExfoliatableRegistry.init();

        ArrayList<ItemStack> itemsToRegister = new ArrayList<>();
        itemsToRegister.addAll(LatinLettersItemsRegistrator.registerAll());
        itemsToRegister.addAll(CyrillicLettersItemsRegistrator.registerAll());
        itemsToRegister.addAll(MiscLettersItemsRegistrator.registerAll());
        itemsToRegister.addAll(MinecraftLettersItemsRegistrator.registerAll());
        itemsToRegister.addAll(TechBlockItems.registerAll());
        GroupRegistrator.registerGroup("letters", itemsToRegister);

        AlphaesBlockEntities.registerEntities();
        AlphaesRecipes.registerRecipes();

        AddonLoader.checkAddonFolder();

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
