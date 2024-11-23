package net.tru.trutermproject.datagen.AdvancedRecipies.HammerRecipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class HammerRecipe implements CraftingRecipe {
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;

    public HammerRecipe(ItemStack result, NonNullList<Ingredient> ingredients){

        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput pInput) {
        return CraftingRecipe.super.getRemainingItems(pInput);
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public boolean matches(CraftingInput pInput, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
        //todo make own serializer
    }
}
