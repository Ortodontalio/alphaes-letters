package com.ortodontalio.alphaesletters.handlers;

import com.ortodontalio.alphaesletters.entity.DyeingMachineBlockEntity;
import com.ortodontalio.alphaesletters.handlers.slots.DMFarbaSlot;
import com.ortodontalio.alphaesletters.handlers.slots.DMFuelSlot;
import com.ortodontalio.alphaesletters.handlers.slots.DMResourceSlot;
import com.ortodontalio.alphaesletters.handlers.slots.DMResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DyeingMachineScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public DyeingMachineScreenHandler(int syncId, PlayerInventory inventory, BlockPositionPayload payload) {
        this(syncId, inventory, (DyeingMachineBlockEntity) inventory.player.getWorld().getBlockEntity(payload.pos()),
                new ArrayPropertyDelegate(4));
    }

    public DyeingMachineScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory,
                                      PropertyDelegate delegate) {
        super(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = delegate;
        this.addSlot(new DMFuelSlot(inventory, 0, 42, 24));
        this.addSlot(new DMResourceSlot(inventory, 1, 42, 58));
        this.addSlot(new DMFarbaSlot(inventory, 2, 84, 41));
        this.addSlot(new DMResultSlot(inventory, 3, 134, 41));
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(delegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean hasFuel() {
        return propertyDelegate.get(2) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 27;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelProgress = this.propertyDelegate.get(2);
        int maxFuelProgress = this.propertyDelegate.get(3);
        int fuelProgressSize = 14;

        return maxFuelProgress != 0 ? (int) (((float) fuelProgress / (float) maxFuelProgress) * fuelProgressSize) : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = slots.get(invSlot);
        ItemStack originalStack = slot.getStack();
        newStack = originalStack.copy();
        if (invSlot < this.inventory.size()) {
            if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
            return ItemStack.EMPTY;
        }

        if (originalStack.isEmpty()) {
            slot.setStackNoCallbacks(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 94 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 152));
        }
    }
}
