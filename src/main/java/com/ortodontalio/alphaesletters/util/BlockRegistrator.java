package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for registers {@link Block} objects. Contains methods that perform certain actions on extended classes.
 * @author Ortodontalio
 */
public abstract class BlockRegistrator {
    private static final Logger LOGGER = Logger.getLogger(BlockRegistrator.class.getName());

    /**
     * Method for registers {@link Block} objects.
     */
    protected static void registerBlocks(Class<? extends BlockRegistrator> clazz) {
        List<Field> fields = List.of(clazz.getDeclaredFields());
        for(Field field : fields) {
            try {
                Registry.register(Registry.BLOCK, new Identifier(AlphaesLetters.MOD_ID, field.getName().toLowerCase()),
                        (Block) field.get(null));
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.SEVERE,
                        String.format("Block hasn't been registered during the next error: %s", e.getMessage()));
            }
        }
    }

}
