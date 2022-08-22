package com.ortodontalio.alphaesletters.handlers;

import com.ortodontalio.alphaesletters.handlers.slots.DMFuelSlot;
import com.ortodontalio.alphaesletters.handlers.slots.DMResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DyeingMachineScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public DyeingMachineScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new SimpleInventory(4));
    }

    public DyeingMachineScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(AlphaesScreenHandlers.DYEING_MACHINE_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.addSlot(new DMFuelSlot(inventory, 0, 42 , 24));
        this.addSlot(new Slot(inventory, 1, 42 , 58));
        this.addSlot(new Slot(inventory, 2, 84 , 41));
        this.addSlot(new DMResultSlot(inventory, 3, 134 , 41));
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
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
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
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
