package net.tru.trutermproject.datagen.AdvancedRecipies.HammerRecipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.ModItems;
import net.tru.trutermproject.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class HammerRecipeProvider extends RecipeProvider {
    public HammerRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

//        recipeOutput.accept(ResourceLocation.fromNamespaceAndPath(
//            TRUTermProject.MOD_ID, "hammer_recipe/beryl"),
//                new HammerRecipe( ModItems.BERYL.toStack(),
//                        NonNullList.of(Ingredient.EMPTY,
//                                Ingredient.of(ModTags.Items.HAMMERS),
//                            Ingredient.of(ModItems.BERYL_GEODE))), null);
    }
}
