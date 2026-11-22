package com.ortodontalio.alphaesletters.tech;

import com.ortodontalio.alphaesletters.AlphaesLetters;
import com.ortodontalio.alphaesletters.common.HasColor;
import com.ortodontalio.alphaesletters.letters.MiscLetters;
import com.ortodontalio.alphaesletters.util.AlphaesUtils;
import com.ortodontalio.alphaesletters.util.StringProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@Deprecated
public class CroppedFerroconcrete extends Block implements HasColor {
    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final StringProperty LETTER = StringProperty.of("letter", AlphaesUtils.getAllLettersNames());
    public static final String ID = "cropped_letter_concrete";

    public CroppedFerroconcrete() {
        super(Settings
                .create()
                .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(AlphaesLetters.MOD_ID, ID))));
        setDefaultState(getDefaultState()
                .with(LIT, false)
                .with(LETTER, MiscLetters.NONE.asString())
                .with(COLOR, DyeColor.WHITE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, COLOR, LETTER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}
