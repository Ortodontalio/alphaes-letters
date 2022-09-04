package com.ortodontalio.alphaesletters.common;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LetterPowder extends Block {

    public LetterPowder() {
        super(FabricBlockSettings
                .of(Material.SOIL, MapColor.WHITE_GRAY)
                .strength(2.0f, 1.0f)
                .sounds(BlockSoundGroup.SAND));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player,
                           BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity,
                           ItemStack stack) {
        ItemStack simplePowder = Blocks.WHITE_CONCRETE_POWDER.asItem().getDefaultStack();
        BlockState simplePowderState = Blocks.WHITE_CONCRETE_POWDER.getDefaultState();
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        dropStacks(simplePowderState, world, pos, blockEntity, player, simplePowder);
        BlockState bars = Blocks.IRON_BARS.getDefaultState();
        world.setBlockState(pos, bars, Block.NOTIFY_ALL);
    }
}
