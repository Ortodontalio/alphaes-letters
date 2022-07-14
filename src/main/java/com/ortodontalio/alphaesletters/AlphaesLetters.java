package com.ortodontalio.alphaesletters;

import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlockItems;
import com.ortodontalio.alphaesletters.cyrillic.CyrillicBlocks;
import com.ortodontalio.alphaesletters.latin.LatinBlockItems;
import com.ortodontalio.alphaesletters.latin.LatinBlocks;
import com.ortodontalio.alphaesletters.misc.MiscBlockItems;
import com.ortodontalio.alphaesletters.misc.MiscBlocks;
import com.ortodontalio.alphaesletters.numbers.NumbersBlockItems;
import com.ortodontalio.alphaesletters.numbers.NumbersBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
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

    @Override
    public void onInitialize() {
        LatinBlockItems.registerItems();
        LatinBlocks.registerBlocks();
        CyrillicBlockItems.registerItems();
        CyrillicBlocks.registerBlocks();
        NumbersBlockItems.registerItems();
        NumbersBlocks.registerBlocks();
        MiscBlockItems.registerItems();
        MiscBlocks.registerBlocks();
    }
}
