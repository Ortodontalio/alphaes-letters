package com.ortodontalio.alphaesletters.common;

import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LetterFerroconcrete extends Block {

    public static final BooleanProperty LIT = Properties.LIT;

    public LetterFerroconcrete() {
        super(FabricBlockSettings
                .of(Material.STONE, MapColor.BLUE)
                .strength(5.0f, 10.0f)
                .sounds(BlockSoundGroup.STONE)
                .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                .requiresTool());
        this.setDefaultState(getDefaultState().with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack inHand = player.getStackInHand(hand);
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))) {
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            world.setBlockState(pos, state.with(LIT, true), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        if (inHand.isIn(AlphaesTags.Items.AXES) && Boolean.TRUE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!player.isCreative()) {
                inHand.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

}
