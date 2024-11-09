package net.tru.trutermproject.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.tru.trutermproject.util.ModTags;

public class ModToolTiers {
    public static final Tier BERYL = new SimpleTier(
            ModTags.Blocks.INCORRECT_FOR_BERYL_TOOL, 800, 7, 2.5f, 14,
            () -> Ingredient.of(ModItems.BERYL));
}
