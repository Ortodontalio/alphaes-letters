package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlockItems;
import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlocks;
import com.ortodontalio.alphaesletters.entity.AlphaesBlockEntities;
import com.ortodontalio.alphaesletters.handlers.AlphaesScreenHandlers;
import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreen;
import com.ortodontalio.alphaesletters.latin.LatinBlockItems;
import com.ortodontalio.alphaesletters.latin.LatinBlocks;
import com.ortodontalio.alphaesletters.misc.MiscBlockItems;
import com.ortodontalio.alphaesletters.misc.MiscBlocks;
import com.ortodontalio.alphaesletters.numbers.NumbersBlockItems;
import com.ortodontalio.alphaesletters.numbers.NumbersBlocks;
import com.ortodontalio.alphaesletters.recipe.AlphaesRecipes;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import com.ortodontalio.alphaesletters.tech.TechBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AlphaesLetters implements ModInitializer {

    public static final String MOD_ID = "alphaesletters";

    public static final ItemGroup ALPHAES_LATIN = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "latin"),
            () -> new ItemStack(LatinBlockItems.LETTER_A)
    );
    public static final ItemGroup ALPHAES_CYRILLIC = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "cyrillic"),
            () -> new ItemStack(CyrillicBlockItems.LETTER_CYR_YA)
    );
    public static final ItemGroup ALPHAES_MISC = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "misc"),
            () -> new ItemStack(NumbersBlockItems.LETTER_9)
    );
    public static final ItemGroup ALPHAES_TECH = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "tech"),
            () -> new ItemStack(TechBlockItems.LETTER_POWDER)
    );

    @SuppressWarnings("deprecation")
    @Override
    public void onInitialize() {
        NumbersBlocks.registerBlocks();
        CyrillicBlocks.registerBlocks();
        LatinBlocks.registerBlocks();
        MiscBlocks.registerBlocks();
        TechBlocks.registerBlocks();
        NumbersBlockItems.registerBlockItems();
        CyrillicBlockItems.registerBlockItems();
        LatinBlockItems.registerBlockItems();
        MiscBlockItems.registerBlockItems();
        TechBlockItems.registerBlockItems();
        AlphaesBlockEntities.registerEntities();
        ScreenRegistry.register(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, DyeingMachineScreen::new);
        AlphaesRecipes.registerRecipes();
    }
}
