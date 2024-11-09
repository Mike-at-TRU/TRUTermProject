package net.tru.trutermproject.datagen;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.block.ModBlocks;
import net.tru.trutermproject.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TRUTermProject.MOD_ID,
                existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //todo add incorrect for beryl so it can't mine everything
        tag(ModTags.Blocks.BERYL_ORE).add(ModBlocks.BERYL_DEEPSLATE_ORE.get())
                .add(ModBlocks.BERYL_END_ORE.get())
                .add(ModBlocks.BERYL_ORE.get())
                .add(ModBlocks.BERYL_NETHER_ORE.get())
        ;
        tag(ModTags.Blocks.TRU_TERM_PROJECT_BLOCKS).addTag(
                ModTags.Blocks.BERYL_ORE);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.BERYL_ORE);
        tag(BlockTags.MINEABLE_WITH_HOE);
        tag(BlockTags.MINEABLE_WITH_SHOVEL);

        tag(BlockTags.MINEABLE_WITH_AXE);

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        tag(Tags.Blocks.NEEDS_GOLD_TOOL);
        tag(BlockTags.NEEDS_IRON_TOOL).addTag(ModTags.Blocks.BERYL_ORE);

        tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(Tags.Blocks.NEEDS_GOLD_TOOL)
                .addTag(BlockTags.NEEDS_STONE_TOOL)
        ;

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .remove(BlockTags.NEEDS_STONE_TOOL)
        ;

        tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .remove(Tags.Blocks.NEEDS_GOLD_TOOL)
        ;

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .remove(BlockTags.NEEDS_IRON_TOOL)
        ;
        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        tag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(Tags.Blocks.NEEDS_NETHERITE_TOOL)
        ;

    }
}
