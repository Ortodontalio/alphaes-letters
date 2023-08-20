package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.LetterBasic;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ortodontalio.alphaesletters.common.LetterBasic.COLOR;

/**
 * A class for registers {@link Block} objects. Contains methods that perform certain actions on extended classes.
 *
 * @author Ortodontalio
 */
public abstract class BlockRegistrator {
    private static final Logger LOGGER = Logger.getLogger(BlockRegistrator.class.getName());

    /**
     * Method for registers {@link Block} objects.
     */
    public void registerBlocks() {
        List<Field> fields = List.of(this.getClass().getDeclaredFields());
        List<LetterBasic> basicLetters = new ArrayList<>();
        for (Field field : fields) {
            try {
                Block block = (Block) field.get(null);
                Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, field.getName().toLowerCase()),
                        block);
                if (block instanceof LetterBasic) {
                    basicLetters.add((LetterBasic) block);
                }
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.SEVERE,
                        String.format("Block hasn't been registered during the next error: %s", e.getMessage()));
            }
        }
        if (!basicLetters.isEmpty()) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> state.get(COLOR).getMapColor().color,
                    basicLetters.toArray(LetterBasic[]::new));
        }
    }

}
