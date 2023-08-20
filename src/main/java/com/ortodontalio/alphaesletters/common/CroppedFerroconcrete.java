package com.ortodontalio.alphaesletters.common;

import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CroppedFerroconcrete extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final EnumProperty<Letters> LETTER = EnumProperty.of("letter", Letters.class);

    public CroppedFerroconcrete() {
        super(FabricBlockSettings
                .of(Material.STONE, MapColor.BLUE)
                .strength(5.0f, 10.0f)
                .sounds(BlockSoundGroup.STONE)
                .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                .requiresTool());
        this.setDefaultState(getDefaultState().with(LIT, false).with(LETTER, Letters.NONE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, LETTER);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0, 0, 0, 16, 16, 15);
            case WEST -> Block.createCuboidShape(1, 0, 0, 16, 16, 16);
            case EAST -> Block.createCuboidShape(0, 0, 0, 15, 16, 16);
            default -> Block.createCuboidShape(0, 0, 1, 16, 16, 16);
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack inHand = player.getStackInHand(hand);
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))
                && !state.get(LETTER).equals(Letters.NONE)) {
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
        if (inHand.isIn(AlphaesTags.Items.LETTERS)) {
            Letters currentLetterInHand = Letters.findLetterByBlock(inHand);
            if (!state.get(LETTER).equals(currentLetterInHand)) {
                if (!player.isCreative()) {
                    inHand.decrement(1);
                }
                world.setBlockState(pos, state.with(LETTER, currentLetterInHand), Block.NOTIFY_ALL);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
