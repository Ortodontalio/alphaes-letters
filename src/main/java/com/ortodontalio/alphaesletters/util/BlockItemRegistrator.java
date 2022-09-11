package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class for registers {@link BlockItem} objects. Contains methods that perform certain actions on extended classes.
 * @author Ortodontalio
 */
public abstract class BlockItemRegistrator {
    private static final Logger LOGGER = Logger.getLogger(BlockItemRegistrator.class.getName());

    /**
     * Method for registers {@link BlockItem} objects.
     */
    protected static void registerBlocks(Class<? extends BlockItemRegistrator> clazz) {
        List<Field> fields = List.of(clazz.getDeclaredFields());
        for(Field field : fields) {
            try {
                Registry.register(Registry.ITEM, new Identifier(AlphaesLetters.MOD_ID, field.getName().toLowerCase()),
                        (BlockItem)field.get(null));
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.SEVERE,
                        String.format("BlockItem hasn't been registered during the next error: %s", e.getMessage()));
            }
        }
    }
}