package com.ortodontalio.processors;

import com.ortodontalio.annotations.LettersDefinition;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"com.ortodontalio.annotations.*"})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class MainProcessor extends AbstractProcessor {
    private static final String MC_ITEM_PACKAGE = "net.minecraft.item";
    private static final ClassName BLOCK_ITEM_CLASS = ClassName.get(MC_ITEM_PACKAGE, "BlockItem");
    private static final ClassName IDENTIFIER_CLASS = ClassName.get("net.minecraft.util", "Identifier");
    private static final Set<String> EXCLUDED_FIELDS = Set.of("NONE", "block");
    private static final String MC_REGISTRY_PACKAGE = "net.minecraft.registry";
    private static final ClassName REGISTRY_KEYS_CLASS = ClassName.get(MC_REGISTRY_PACKAGE, "RegistryKeys");
    private static final ClassName REGISTRY_KEY_CLASS = ClassName.get(MC_REGISTRY_PACKAGE, "RegistryKey");
    private static final ClassName REGISTRIES_CLASS = ClassName.get(MC_REGISTRY_PACKAGE, "Registries");
    private static final ClassName REGISTRY_CLASS = ClassName.get(MC_REGISTRY_PACKAGE, "Registry");
    private static final String OUTPUT_PACKAGE = "com.ortodontalio.alphaesletters.codegen";
    private static final String GROUP_REGISTRATOR_CLASS = "GroupRegistrator";
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return false;
        }
        try {
            generateGroupRegisterClass();
            for (Element element : roundEnv.getElementsAnnotatedWith(LettersDefinition.class)) {
                generateRegistratorsAndWrite(element);
            }
        } catch (Exception ex) {
            messager.printMessage(Diagnostic.Kind.ERROR, String.format("An error has occurred during generating code: %s",
                    ex.getLocalizedMessage()));
        }
        return true;
    }

    private void write(TypeSpec classToWrite) throws IOException {
        JavaFile.builder(OUTPUT_PACKAGE, classToWrite)
                .build()
                .writeTo(filer);
    }

    private void generateGroupRegisterClass() throws IOException {
        ClassName itemGroupClass = ClassName.get(MC_ITEM_PACKAGE, "ItemGroup");
        ClassName fabricGroupClass = ClassName.get("net.fabricmc.fabric.api.itemgroup.v1", "FabricItemGroup");
        ClassName itemStackClass = ClassName.get(MC_ITEM_PACKAGE, "ItemStack");
        ClassName textClass = ClassName.get("net.minecraft.text", "Text");

        TypeSpec groupClass = TypeSpec
                .classBuilder(GROUP_REGISTRATOR_CLASS)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addMethod(MethodSpec.methodBuilder("registerGroup")
                        .addModifiers(Modifier.PROTECTED, Modifier.FINAL, Modifier.STATIC)
                        .addParameter(String.class, "className")
                        .addParameter(List.class, "blocks")
                        .addStatement("String key = String.format(\"itemGroup.alphaesletters.%s\", className)")
                        .addStatement("$T group = $T.builder()" +
                                ".icon(() -> ($T) blocks.get(0))" +
                                ".displayName($T.translatable(key))" +
                                ".entries((context, entries) -> entries.addAll(blocks))" +
                                ".build()", itemGroupClass, fabricGroupClass, itemStackClass, textClass)
                        .addStatement("$T.register($T.ITEM_GROUP, $T.of($T.ITEM_GROUP, $T.of(\"alphaesletters\", className)), group)",
                                REGISTRY_CLASS, REGISTRIES_CLASS, REGISTRY_KEY_CLASS, REGISTRY_KEYS_CLASS, IDENTIFIER_CLASS)
                        .build())
                .build();
        write(groupClass);
    }

    private void generateRegistratorsAndWrite(Element annotatedClass) throws IOException {
        ClassName letterBasicClass = ClassName.get("com.ortodontalio.alphaesletters.common", "LetterBasic");
        ClassName blockClass = ClassName.get("net.minecraft.block", "Block");
        ClassName itemClass = ClassName.get(MC_ITEM_PACKAGE, "Item");
        ClassName itemStackClass = ClassName.get(MC_ITEM_PACKAGE, "ItemStack");
        ClassName colorProviderClass = ClassName.get("net.fabricmc.fabric.api.client.rendering.v1", "ColorProviderRegistry");

        // Init type registrators
        String registratorClassName = String.format("%sRegistrator", annotatedClass.getSimpleName().toString());
        TypeSpec.Builder registratorClass = TypeSpec
                .classBuilder(registratorClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        TypeSpec.Builder registratorItemClass = TypeSpec
                .classBuilder(String.format("%sItemsRegistrator", annotatedClass.getSimpleName().toString()))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(ClassName.get(OUTPUT_PACKAGE, GROUP_REGISTRATOR_CLASS));

        // Init register methods
        MethodSpec.Builder registerMethodBuilder = MethodSpec.methodBuilder("registerAll")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                .addStatement("$T<$T> allBlocks = new ArrayList<>()", ArrayList.class, blockClass);
        MethodSpec.Builder registerItemsMethodBuilder = MethodSpec.methodBuilder("registerAll")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                .addStatement("$T<$T> allBlocks = new ArrayList<>()", ArrayList.class, itemStackClass);

        annotatedClass.getEnclosedElements().stream()
                .filter(innerElements -> innerElements.getKind().isField()
                        && !EXCLUDED_FIELDS.contains(innerElements.getSimpleName().toString()))
                .toList()
                .forEach(field -> {
                    FieldSpec genBlockField = FieldSpec.builder(blockClass, field.getSimpleName().toString().toUpperCase())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("new $T($S)", letterBasicClass, field.getSimpleName().toString().toLowerCase())
                            .build();
                    FieldSpec genBlockItemField = FieldSpec.builder(BLOCK_ITEM_CLASS, field.getSimpleName().toString().toUpperCase())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("new $T($N.$N, new $T.Settings().useBlockPrefixedTranslationKey().registryKey($T.of($T.ITEM, $T.of(\"alphaesletters\", $S))))",
                                    BLOCK_ITEM_CLASS, registratorClassName, genBlockField, itemClass, REGISTRY_KEY_CLASS, REGISTRY_KEYS_CLASS, IDENTIFIER_CLASS,
                                    field.getSimpleName().toString().toLowerCase())
                            .build();
                    registratorClass.addField(genBlockField);
                    registratorItemClass.addField(genBlockItemField);

                    registerMethodBuilder.addStatement("$T.register($T.BLOCK, $T.of($T.BLOCK, $T.of(\"alphaesletters\", $S)), $N)",
                                    REGISTRY_CLASS, REGISTRIES_CLASS, REGISTRY_KEY_CLASS, REGISTRY_KEYS_CLASS, IDENTIFIER_CLASS,
                                    field.getSimpleName().toString().toLowerCase(), genBlockField)
                            .addStatement("allBlocks.add($N)", genBlockField);
                    registerItemsMethodBuilder.addStatement("$T.register($T.ITEM, $T.of($T.ITEM, $T.of(\"alphaesletters\", $S)), $N)",
                                    REGISTRY_CLASS, REGISTRIES_CLASS, REGISTRY_KEY_CLASS, REGISTRY_KEYS_CLASS, IDENTIFIER_CLASS,
                                    field.getSimpleName().toString().toLowerCase(), genBlockItemField)
                            .addStatement("allBlocks.add(new $T($N))", itemStackClass, genBlockItemField);
                });
        registerMethodBuilder.addStatement("$T.BLOCK.register((state, view, pos, tintIndex) -> state.get($T.COLOR).getMapColor().color," +
                "allBlocks.toArray(LetterBasic[]::new))", colorProviderClass, letterBasicClass);
        registerItemsMethodBuilder.addStatement("registerGroup($S, allBlocks)", annotatedClass.getSimpleName().toString().toLowerCase());
        registratorClass.addMethod(registerMethodBuilder.build());
        registratorItemClass.addMethod(registerItemsMethodBuilder.build());

        write(registratorClass.build());
        write(registratorItemClass.build());
    }
}
