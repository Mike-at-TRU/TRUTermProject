package net.tru.trutermproject.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.ModItems;
import net.tru.trutermproject.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagLookup<Block>> blockTags,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, TRUTermProject.MOD_ID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS).add(ModItems.BERYL_SWORD.get());

        tag(ItemTags.AXES).add(ModItems.BERYL_AXE.get());

        tag(ItemTags.PICKAXES).add(ModItems.BERYL_PICKAXE.get());
        tag(ItemTags.HOES).add(ModItems.BERYL_HOE.get());
        tag(ItemTags.SHOVELS).add(ModItems.BERYL_SHOVEL.get());
        tag(ModTags.Items.HAMMERS).add(ModItems.WOODEN_HAMMER.get())
                .add(ModItems.STONE_HAMMER.get())
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.BERYL_HAMMER.get())
                .add(ModItems.GOLD_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get())
        ;

    }
}
