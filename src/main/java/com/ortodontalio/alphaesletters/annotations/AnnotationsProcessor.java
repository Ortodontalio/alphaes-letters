package com.ortodontalio.alphaesletters.annotations;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
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
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char DIRECTORY_SEPARATOR = '/';
    private static final String CLASS_SUFFIX = ".class";
    private static final String SCANNED_PACKAGE = "com.ortodontalio.alphaesletters";
    private static final String[] EXCLUSIONS = {"mixin","AlphaesLetters.class"};

    private AnnotationsProcessor(){}

    /**
     * Method for registers {@link Block} objects, defined in classes, annotated by {@link BlockRegistrator}.
     */
    public static void registerBlocks() {
        Iterable<Class<?>> iterable = getAnnotated(BlockRegistrator.class);
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
        Iterable<Class<?>> iterable = getAnnotated(BlockItemRegistrator.class);
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

    /**
     * Secondary util method, uses recursion for founding necessary classes.
     * @param file is folder of class for searching the annotation.
     * @param scannedPackage is a package, in which folder or class is stored.
     * @param annotation is the annotation whose classes need to be found.
     * @return found classes' list.
     */
    private static List<Class<?>> find(File file, String scannedPackage, Class<? extends Annotation> annotation) {
        List<Class<?>> classes = new ArrayList<>();
        String resource = scannedPackage + PACKAGE_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                classes.addAll(find(child, resource, annotation));
            }
        } else {
            if (resource.endsWith(CLASS_SUFFIX)) {
                try {
                    Class<?> clazz = Class.forName(resource.substring(0, resource.length() - CLASS_SUFFIX.length()));
                    if(clazz.isAnnotationPresent(annotation)) {
                        classes.add(clazz);
                    }
                } catch (ClassNotFoundException ignore) {
                    LOGGER.warning("One or more classes haven't been found.");
                }
            }
        }
        return classes;
    }

    /**
     * Util method for searching annotated classes.
     * @param annotation is the annotation whose classes need to be found.
     * @return found classes' list.
     */
    private static List<Class<?>> getAnnotated(Class<? extends Annotation> annotation) {
        String scannedPath = SCANNED_PACKAGE.replace(PACKAGE_SEPARATOR, DIRECTORY_SEPARATOR);
        URL url = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        File directory = new File(Objects.requireNonNull(url).getFile());
        List<Class<?>> classes = new ArrayList<>();
        List<File> files = Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(f -> Arrays.stream(EXCLUSIONS).noneMatch(s -> s.equals(f.getName())))
                .toList();
        for (File file : files) {
            classes.addAll(find(file, SCANNED_PACKAGE, annotation));
        }
        LOGGER.log(Level.INFO, "Registrators found: {0}", classes);
        return classes;
    }
}
