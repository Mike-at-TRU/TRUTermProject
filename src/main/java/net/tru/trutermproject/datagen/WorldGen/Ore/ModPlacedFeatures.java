package net.tru.trutermproject.datagen.WorldGen.Ore;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.tru.trutermproject.TRUTermProject;
import org.joml.Vector2dc;

import java.util.List;

//stolen from https://www.youtube.com/watch?v=_7sc7INW0IY
public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BERYL_ORE = createKey("beryl_ore");
    public static final ResourceKey<PlacedFeature> NETHER_BERYL_ORE = createKey("nether_beryl_ore");
    public static final ResourceKey<PlacedFeature> END_BERYL_ORE = createKey("end_beryl_ore");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> overworldBerylOre =
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BERYL_ORE);
        Holder<ConfiguredFeature<?, ?>> netherBerylOre =
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BERYL_ORE);
        Holder<ConfiguredFeature<?, ?>> endBerylOre =
                configuredFeatures.getOrThrow(ModConfiguredFeatures.END_BERYL_ORE);

        register(context, BERYL_ORE, overworldBerylOre, ModOrePlacement.commonOrePlacements(5,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));

        register(context, NETHER_BERYL_ORE, netherBerylOre, ModOrePlacement.commonOrePlacements(5,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));

        register(context, END_BERYL_ORE, endBerylOre, ModOrePlacement.commonOrePlacements(5,
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20))));
    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key
            , Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));

    }
}
