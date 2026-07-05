package com.ortodontalio.alphaesletters.tech;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ConcreteWithBars extends PillarBlock {
    public ConcreteWithBars(Settings settings) {
        super(settings);
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool,
                                   boolean dropExperience) {
        RegistryWrapper.WrapperLookup wrapperLookup = world.getRegistryManager();

        RegistryEntry<Enchantment> silkTouch = wrapperLookup
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(Enchantments.SILK_TOUCH);
        boolean hasSilkTouch = EnchantmentHelper.getLevel(silkTouch, tool) > 0;
        if (!hasSilkTouch) {
            BlockState bars = Blocks.IRON_BARS.getDefaultState();
            world.setBlockState(pos, bars);
        }
    }
}
