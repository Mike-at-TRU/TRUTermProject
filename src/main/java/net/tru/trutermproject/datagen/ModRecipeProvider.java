package net.tru.trutermproject.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.block.ModBlocks;
import net.tru.trutermproject.item.ModItems;
import net.tru.trutermproject.item.custom.HammerItem;
import net.tru.trutermproject.util.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends RecipeProvider {
    List<ItemLike> BERYL_SMELTABLES = List.of(ModItems.BERYL_GEODE,
            ModBlocks.BERYL_ORE, ModBlocks.BERYL_DEEPSLATE_ORE,
            ModBlocks.BERYL_END_ORE, ModBlocks.BERYL_NETHER_ORE);

    public ModRecipeProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    //recipes default to the name of the recipe result, in the save you can add another parameter
    //if there are multiple ways to make something set it to "trutermproject:[item/block]_from_[thing used to craft]
    //shapeless needs a .requires before .unlockedBy
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE)
                .pattern("BBB").pattern("BAB").pattern("BBB")
                .define('B', Blocks.GOLD_BLOCK).define('A', Items.APPLE)
                .unlockedBy("has_gold_block", has(Blocks.GOLD_BLOCK))
                .save(recipeOutput)
        ;





        //do i need a suffix on this if so how
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BERYL)
                            .requires(ModItems.BERYL_GEODE).requires(ModTags.Items.HAMMERS)
                            .unlockedBy("has_beryl_geode", has(ModItems.BERYL_GEODE.get())).save(recipeOutput);

        hammerRecipe(recipeOutput, ModItems.WOODEN_HAMMER, ItemTags.PLANKS);
        hammerRecipe(recipeOutput, ModItems.STONE_HAMMER, ItemTags.STONE_TOOL_MATERIALS);
        hammerRecipe(recipeOutput,ModItems.IRON_HAMMER, Items.IRON_INGOT);
        hammerRecipe(recipeOutput, ModItems.GOLD_HAMMER, Items.GOLD_INGOT);

        hammerRecipe(recipeOutput, ModItems.BERYL_HAMMER, ModItems.BERYL.get());

        hammerRecipe(recipeOutput, ModItems.DIAMOND_HAMMER, Items.DIAMOND);

        hammerRecipe(recipeOutput, ModItems.NETHERITE_HAMMER, Items.NETHERITE_INGOT);

    }
    protected static <T extends ShapedRecipe> void hammerRecipe(RecipeOutput recipeOutput, DeferredItem<HammerItem> hammerToCraft, Item itemUsedForCrafting){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammerToCraft)
                .pattern("III").pattern("ISI").pattern(" S ")
                .define('I' , itemUsedForCrafting).define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + itemUsedForCrafting.asItem(),has(itemUsedForCrafting))
                .save(recipeOutput);
    }
    protected static <T extends ShapedRecipe> void hammerRecipe(RecipeOutput recipeOutput, DeferredItem<HammerItem> hammerToCraft, TagKey<Item> itemUsedForCrafting){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hammerToCraft)
                .pattern("III").pattern("ISI").pattern(" S ")
                .define('I' , itemUsedForCrafting).define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + itemUsedForCrafting,has(itemUsedForCrafting))
                .save(recipeOutput);
    }

    //private String getUnlockedByName(Item)

    protected static void oreSmelting(RecipeOutput recipeOutput,
            List<ItemLike> pIngredients, RecipeCategory pCategory,
            ItemLike pResult, float pExperience, int pCookingTIme,
            String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput,
            List<ItemLike> pIngredients, RecipeCategory pCategory,
            ItemLike pResult, float pExperience, int pCookingTime,
            String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }



    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer,
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> pIngredients, RecipeCategory pCategory,
            ItemLike pResult, float pExperience, int pCookingTime,
            String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder
                    .generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer,
                            factory).group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput,
                            TRUTermProject.MOD_ID + ":" + getItemName(
                                    pResult) + pRecipeName + "_" + getItemName(
                                    itemlike))
            ;
        }
    }
}
