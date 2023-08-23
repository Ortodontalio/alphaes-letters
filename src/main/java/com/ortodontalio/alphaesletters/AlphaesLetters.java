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
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static com.ortodontalio.alphaesletters.common.LetterBasic.COLOR;
import static com.ortodontalio.alphaesletters.tech.TechBlocks.CROPPED_LETTER_CONCRETE;
import static com.ortodontalio.alphaesletters.tech.TechBlocks.STRIKETHROUGH_BLOCK;
import static net.minecraft.server.command.CommandManager.literal;


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
    private final NumbersBlocks numbersBlocks = new NumbersBlocks();
    private final CyrillicBlocks cyrillicBlocks = new CyrillicBlocks();
    private final LatinBlocks latinBlocks = new LatinBlocks();
    private final MiscBlocks miscBlocks = new MiscBlocks();
    private final TechBlocks techBlocks = new TechBlocks();
    private final NumbersBlockItems numbersBlockItems = new NumbersBlockItems();
    private final CyrillicBlockItems cyrillicBlockItems = new CyrillicBlockItems();
    private final LatinBlockItems latinBlockItems = new LatinBlockItems();
    private final MiscBlockItems miscBlockItems = new MiscBlockItems();
    private final TechBlockItems techBlockItems = new TechBlockItems();


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
        numbersBlocks.registerBlocks();
        cyrillicBlocks.registerBlocks();
        latinBlocks.registerBlocks();
        miscBlocks.registerBlocks();
        techBlocks.registerBlocks();
        numbersBlockItems.registerBlockItems();
        cyrillicBlockItems.registerBlockItems();
        latinBlockItems.registerBlockItems();
        miscBlockItems.registerBlockItems();
        techBlockItems.registerBlockItems();
        AlphaesBlockEntities.registerEntities();
        ScreenRegistry.register(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, DyeingMachineScreen::new);
        AlphaesRecipes.registerRecipes();
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> state.get(COLOR).getMapColor().color,
                CROPPED_LETTER_CONCRETE, STRIKETHROUGH_BLOCK);
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
