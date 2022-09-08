package com.ortodontalio.alphaesletters.annotations;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.atteo.classindex.ClassIndex;
import java.util.logging.Level;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

/**
 * A class for processing author's annotations. Contains methods that perform certain actions on annotated objects.
 * Cannot be inherited.
 * @author Ortodontalio
 */
public final class AnnotationsProcessor {

    private static final Logger LOGGER = Logger.getLogger(AnnotationsProcessor.class.getName());

    private AnnotationsProcessor(){}

    /**
     * Method for registers {@link Block} objects, defined in classes, annotated by {@link BlockRegistrator}.
     */
    public static void registerBlocks() {
        Iterable<Class<?>> iterable = ClassIndex.getAnnotated(BlockRegistrator.class);
        for(Class<?> className : iterable) {
            List<Field> fields = List.of(className.getDeclaredFields());
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

    /**
     * Method for registers {@link BlockItem} objects, defined in classes, annotated by {@link BlockItemRegistrator}.
     */
    public static void registerBlockItems() {
        Iterable<Class<?>> iterable = ClassIndex.getAnnotated(BlockItemRegistrator.class);
        for(Class<?> className : iterable) {
            List<Field> fields = List.of(className.getDeclaredFields());
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

}
