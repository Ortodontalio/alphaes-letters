package com.ortodontalio.alphaesletters.util;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface GroupRegistrable {

    Logger LOGGER = Logger.getLogger(GroupRegistrable.class.getName());
    String ADD_TO_GROUP_ERR = "BlockItem hasn't been added to group %s during the next error: %s";
    String GROUP_REGISTER_PATTERN = "itemGroup.alphaesletters.%s";

    default void registerGroup(BlockItem iconBlock) {
        String groupName = getClass().getSimpleName().toLowerCase(Locale.ROOT);
        String key = String.format(GROUP_REGISTER_PATTERN, groupName);
        List<Field> fields = List.of(this.getClass().getDeclaredFields());
        ItemGroup groupToRegister = FabricItemGroup.builder()
                .icon(() -> new ItemStack(iconBlock))
                .displayName(Text.translatable(key))
                .entries((context, entries) -> {
                    for (Field field : fields) {
                        try {
                            BlockItem block = (BlockItem) field.get(null);
                            entries.add(block);
                        } catch (Exception e) {
                            LOGGER.log(Level.SEVERE, String.format(ADD_TO_GROUP_ERR, groupName, e.getMessage()));
                        }
                    }
                })
                .build();
        Registry.register(Registries.ITEM_GROUP, RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(AlphaesLetters.MOD_ID, groupName)),
                groupToRegister);
    }
}
