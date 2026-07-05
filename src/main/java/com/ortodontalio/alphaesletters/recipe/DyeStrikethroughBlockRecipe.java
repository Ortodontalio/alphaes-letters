package com.ortodontalio.alphaesletters.recipe;

import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

public class DyeStrikethroughBlockRecipe extends SpecialCraftingRecipe {

    public DyeStrikethroughBlockRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() < 2) {
            return false;
        }
        ItemStack frame = ItemStack.EMPTY;
        boolean foundFrame = false;
        boolean foundDye = false;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty())
                continue;
            if (stack.isOf(TechBlockItems.STRIKETHROUGH_BLOCK)) {
                if (foundFrame) return false;
                foundFrame = true;
                continue;
            }
            if (stack.getItem() instanceof DyeItem) {
                if (foundDye) return false;
                foundDye = true;
                continue;
            }
            return false;
        }
        return !frame.isEmpty();
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack frame = ItemStack.EMPTY;
        DyeColor color = DyeColor.WHITE;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.getItem() instanceof DyeItem dye) {
                color = dye.getColor();
            } else if (stack.isOf(TechBlockItems.STRIKETHROUGH_BLOCK)) {
                frame = stack.copyWithCount(1);
            }
        }
        BlockStateComponent component = frame.getOrDefault(DataComponentTypes.BLOCK_STATE, BlockStateComponent.DEFAULT);
        if (component.getValue(StrikethroughBlock.COLOR) == color) {
            return ItemStack.EMPTY;
        }
        component = component.with(StrikethroughBlock.COLOR, color);
        frame.set(DataComponentTypes.BLOCK_STATE, component);
        return frame;
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return AlphaesRecipes.FRAME_DYE_RECIPE;
    }
}
