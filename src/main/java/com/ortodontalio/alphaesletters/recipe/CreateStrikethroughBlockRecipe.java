package com.ortodontalio.alphaesletters.recipe;

import com.ortodontalio.alphaesletters.tech.StrikethroughBlock;
import com.ortodontalio.alphaesletters.tech.TechBlockItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

public class CreateStrikethroughBlockRecipe extends SpecialCraftingRecipe {

    public CreateStrikethroughBlockRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != 9) {
            return false;
        }
        DyeItem dye = null;
        for (int slot = 0; slot < input.size(); slot++) {
            ItemStack stack = input.getStackInSlot(slot);
            if (slot == 4) {
                if (!(stack.getItem() instanceof DyeItem dyeItem)) {
                    return false;
                }
                dye = dyeItem;
                continue;
            }
            if (!stack.isOf(Items.STICK)) {
                return false;
            }
        }
        return dye != null;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        DyeColor color = DyeColor.WHITE;
        ItemStack center = input.getStackInSlot(4);
        if (center.getItem() instanceof DyeItem dye) {
            color = dye.getColor();
        }
        ItemStack result = new ItemStack(TechBlockItems.STRIKETHROUGH_BLOCK, 2);
        BlockStateComponent component = BlockStateComponent.DEFAULT
                .with(StrikethroughBlock.COLOR, color);
        result.set(DataComponentTypes.BLOCK_STATE, component);
        return result;
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return AlphaesRecipes.CREATE_FRAME_RECIPE;
    }
}
