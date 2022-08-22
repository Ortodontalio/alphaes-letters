package com.ortodontalio.alphaesletters.entity;

import com.ortodontalio.alphaesletters.handlers.DyeingMachineScreenHandler;
import com.ortodontalio.alphaesletters.inventory.ImplementedInventory;
import com.ortodontalio.alphaesletters.misc.MiscBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DyeingMachineBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public DyeingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(AlphaesBlockEntities.DYEING_MACHINE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.alphaesletters.dyeing_machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new DyeingMachineScreenHandler(syncId, inv, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }

    public static void tick(World world, BlockPos pos, BlockState state, DyeingMachineBlockEntity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    private static boolean hasRecipe(DyeingMachineBlockEntity entity) {
        boolean itemInThirdSlot = entity.getStack(0).getItem() == Items.WATER_BUCKET;
        boolean itemInSecondSlot = entity.getStack(1).getItem() == Items.WHITE_CONCRETE_POWDER;
        boolean itemInFirstSlot = entity.getStack(2).getItem() == Items.BLUE_DYE;
        return itemInFirstSlot && itemInSecondSlot && itemInThirdSlot;
    }

    private static boolean hasNotReachedStackLimit(DyeingMachineBlockEntity entity) {
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }

    private static void craftItem(DyeingMachineBlockEntity entity) {
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);
        entity.setStack(0, new ItemStack(Items.BUCKET, 1));
        entity.setStack(3, new ItemStack(MiscBlocks.LETTER_CONCRETE, entity.getStack(3).getCount() + 1));
    }
}
