package com.ortodontalio.alphaesletters.handlers.slots;

import com.ortodontalio.alphaesletters.tags.AlphaesTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class DMFarbaSlot extends Slot {

    public DMFarbaSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.isIn(AlphaesTags.Items.FARBA_ITEMS);
    }
}
