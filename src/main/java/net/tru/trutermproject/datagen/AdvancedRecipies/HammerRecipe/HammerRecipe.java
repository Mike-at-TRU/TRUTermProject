package net.tru.trutermproject.datagen.AdvancedRecipies.HammerRecipe;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.tru.trutermproject.util.ModTags;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HammerRecipe implements CraftingRecipe {
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    static final int MAX_HEIGHT = 3;
    static final int MAX_WIDTH = 3;
     final boolean isSimple;

    public HammerRecipe(ItemStack result, NonNullList<Ingredient> ingredients){

        this.result = result;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
    }
    // I understand most of this (ish) but that didn't stop me from stealing ae2's QuartzCutting.java
    public static final MapCodec<HammerRecipe> CODEC = RecordCodecBuilder.mapCodec((builder) -> builder.group(
            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(HammerRecipe::getResult),
            Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((r) ->{
                Ingredient[] ingredients = r.toArray(Ingredient[]::new);
                if(ingredients.length == 0){
                    return DataResult.error(() -> "I need ingredient you idiot");
                }else {
                    return ingredients.length > MAX_HEIGHT * MAX_WIDTH ? DataResult.error(()->{
                        return "what do you expect me to do with all this? this is way too much compaird to %s"
                                .formatted(MAX_HEIGHT*MAX_WIDTH);
                    }) : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
                }
            }, DataResult::success).forGetter(HammerRecipe::getIngredients)

    ).apply(builder, HammerRecipe::new));

    //ok yes, I did just copy and paste this but can you blame me?
    public static final StreamCodec<RegistryFriendlyByteBuf, HammerRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, HammerRecipe::getResult,
            StreamCodec.of(
                    //don't know why this was saying object at first
                    (buffer, value) -> {
                        buffer.writeVarInt(value.size());
                        for (Ingredient ingredient : value) {
                            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
                        }
                    },
                    buffer -> {
                        int count = buffer.readVarInt();
                        NonNullList<Ingredient> ingredients = NonNullList.withSize(count, Ingredient.EMPTY);
                        ingredients.replaceAll(ignored -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
                        return ingredients;
                    }),
            HammerRecipe::getIngredients,
            HammerRecipe::new);

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remainItems = NonNullList.withSize(input.size(), ItemStack.EMPTY);

        boolean damagedHammer = false;
        for( int i = 0; i < remainItems.size(); i++){
            ItemStack item = input.getItem(i);

            if(!damagedHammer && item.is(ModTags.Items.HAMMERS)){
                damagedHammer = true;
                ItemStack result = item.copy();

                MutableBoolean broken = new MutableBoolean(false);
                if(CommonHooks.getCraftingPlayer() instanceof ServerPlayer serverPlayer){
                    result.hurtAndBreak(1, serverPlayer.serverLevel(), serverPlayer, ignored -> broken.setTrue());
                } else {
                  MinecraftServer currentServer = ServerLifecycleHooks.getCurrentServer();
                  if(currentServer != null){
                      result.hurtAndBreak(1, currentServer.overworld(), null, ignored -> broken.setTrue());
                  }
                }
                remainItems.set(i, broken.getValue() ? ItemStack.EMPTY : result);
            } else if(item.hasCraftingRemainingItem()){
                remainItems.set(i, item.getCraftingRemainingItem());
            }
        }
        return remainItems;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if(input.ingredientCount() != this.ingredients.size())
            return false;
        else if(!this.isSimple){
            ArrayList<ItemStack> nonEmptyItems = new ArrayList<ItemStack>(input.ingredientCount());

            for(ItemStack item : input.items()){
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            }

            return RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        }
        else{ if (input.size() == 1 && this.ingredients.size() == 1){
            return this.ingredients.getFirst().test(input.getItem(0));
        }
        return input.stackedContents().canCraft(this, null);
        }
    }

    @Override
    public ItemStack assemble(CraftingInput pInput, HolderLookup.Provider pRegistries) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return this.result;
    }

    private ItemStack getResult(){
        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return HammerRecipeSerializer.INSTANCE;
    }
}
