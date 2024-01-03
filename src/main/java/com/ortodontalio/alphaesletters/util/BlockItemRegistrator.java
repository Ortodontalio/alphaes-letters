package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
    public void registerBlockItems() {
        List<Field> fields = List.of(this.getClass().getDeclaredFields());
        for(Field field : fields) {
            try {
                Registry.register(Registries.ITEM, new Identifier(AlphaesLetters.MOD_ID, field.getName().toLowerCase()),
                        (BlockItem)field.get(null));
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.SEVERE,
                        String.format("BlockItem hasn't been registered during the next error: %s", e.getMessage()));
            }
        }
    }
}
