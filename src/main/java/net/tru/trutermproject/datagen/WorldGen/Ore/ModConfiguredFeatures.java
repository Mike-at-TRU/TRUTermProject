package net.tru.trutermproject.datagen.WorldGen.Ore;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.block.ModBlocks;
import net.tru.trutermproject.util.ModTags;

import java.util.List;

public class ModConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BERYL_ORE = createKey("overworld_beryl_ore");
    protected static ResourceKey<ConfiguredFeature<?,?>> END_BERYL_ORE = createKey("end_beryl_ore");

    protected static ResourceKey<ConfiguredFeature<?,?>> NETHER_BERYL_ORE = createKey("nether_beryl_ore");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacable = new TagMatchTest(Tags.Blocks.NETHERRACKS);
        RuleTest endstoneReplacable = new TagMatchTest(Tags.Blocks.END_STONES);


        List<OreConfiguration.TargetBlockState> berylOre =
                List.of(OreConfiguration.target(stoneReplacable, ModBlocks.BERYL_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplacable, ModBlocks.BERYL_DEEPSLATE_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> netherBerylOre =
                List.of(OreConfiguration.target(netherrackReplacable, ModBlocks.BERYL_NETHER_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> endBerylOre =
                List.of(OreConfiguration.target(endstoneReplacable, ModBlocks.BERYL_END_ORE.get().defaultBlockState()));
        register(context, OVERWORLD_BERYL_ORE, Feature.ORE, new OreConfiguration(berylOre, 7));
        register(context, END_BERYL_ORE, Feature.ORE, new OreConfiguration(endBerylOre, 7));
        register(context, NETHER_BERYL_ORE, Feature.ORE, new OreConfiguration(netherBerylOre, 7));

    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                  ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {

        context.register(key, new ConfiguredFeature<>(feature, config));
    }
}
