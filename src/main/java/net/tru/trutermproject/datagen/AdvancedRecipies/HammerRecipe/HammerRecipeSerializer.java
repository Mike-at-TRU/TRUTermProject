package net.tru.trutermproject.datagen.AdvancedRecipies.HammerRecipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class HammerRecipeSerializer implements RecipeSerializer<HammerRecipe> {

	public static final HammerRecipeSerializer INSTANCE = new HammerRecipeSerializer();

	public HammerRecipeSerializer(){}

	@Override
	public MapCodec<HammerRecipe> codec() {
		return HammerRecipe.CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> streamCodec() {
		return HammerRecipe.STREAM_CODEC;
	}
}
