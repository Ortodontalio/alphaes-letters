package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ConcreteWithBars extends PillarBlock {
    public ConcreteWithBars() {
        super(Settings
                .create()
                .mapColor(MapColor.WHITE_GRAY)
                .strength(7.0f, 8.0f)
                .sounds(BlockSoundGroup.STONE)
                .requiresTool()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, "concrete_with_bars"))));
    }
}
