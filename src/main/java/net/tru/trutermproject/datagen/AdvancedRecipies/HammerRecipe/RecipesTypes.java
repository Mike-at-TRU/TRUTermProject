package net.tru.trutermproject.datagen.AdvancedRecipies.HammerRecipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;

public class RecipesTypes {
		private RecipesTypes(){

		}
	public static final DeferredRegister<RecipeType<?>> DR = DeferredRegister
			.create(Registries.RECIPE_TYPE, TRUTermProject.MOD_ID);

	public static final RecipeType<HammerRecipe> TRANSFORM = register("hammer_recipe");

	private static <T extends Recipe<?>> RecipeType<T> register(String id) {
		RecipeType<T> type = RecipeType.simple(ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,id));
		DR.register(id, () -> type);
		return type;
	}
}
