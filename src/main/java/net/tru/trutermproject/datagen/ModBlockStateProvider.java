package net.tru.trutermproject.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TRUTermProject.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.BERYL_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.BERYL_NETHER_ORE);
        blockWithItem(ModBlocks.BERYL_ORE);
        blockWithItem(ModBlocks.BERYL_END_ORE);
        simpleBlock(ModBlocks.JAX_FLOWER.get(), models().cross("jax_flower", blockTexture(ModBlocks.JAX_FLOWER.get())).renderType("minecraft:cutout"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("trutermproject:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("tru.termproject:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
