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
import net.minecraft.item.DyeItem;
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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags.DYES;

public class LetterBasic extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class, DyeColor.values());

    public LetterBasic() {
        super(FabricBlockSettings
                .of(Material.STONE, MapColor.WHITE)
                .strength(5.0f, 10.0f)
                .sounds(BlockSoundGroup.STONE)
                .luminance(state -> Boolean.TRUE.equals(state.get(LIT)) ? 10 : 0)
                .requiresTool());
        this.setDefaultState(getDefaultState().with(LIT, false));
        this.setDefaultState(getDefaultState().with(COLOR, DyeColor.WHITE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, COLOR);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(0, 1, 0, 16, 16, 2);
            case WEST -> Block.createCuboidShape(14, 1, 0, 16, 16, 16);
            case EAST -> Block.createCuboidShape(0, 1, 0, 2, 16, 16);
            default -> Block.createCuboidShape(0, 1, 14, 16, 16, 16);
        };
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack inHand = player.getStackInHand(hand);
        if (inHand.isOf(Items.GLOWSTONE_DUST) && Boolean.FALSE.equals(state.get(LIT))) {
            world.playSound(player, pos, SoundEvents.ENTITY_GLOW_ITEM_FRAME_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
        if (inHand.isIn(DYES) && !state.get(COLOR).equals(((DyeItem) inHand.getItem()).getColor())) {
            world.playSound(player, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, state.with(COLOR, ((DyeItem) inHand.getItem()).getColor()), Block.NOTIFY_ALL);
            if (!player.isCreative()) {
                inHand.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
