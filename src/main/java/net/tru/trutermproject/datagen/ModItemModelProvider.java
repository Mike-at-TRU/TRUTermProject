package net.tru.trutermproject.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }


    public ModItemModelProvider(PackOutput output,
            ExistingFileHelper existingFileHelper) {
        super(output, TRUTermProject.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //basicItem()
        basicItem(ModItems.BERYL.get());
        basicItem(ModItems.BERYL_GEODE.get());
        // HAMMER TIME!
        handheldItem(ModItems.WOODEN_HAMMER);

        handheldItem(ModItems.STONE_HAMMER);

        handheldItem(ModItems.IRON_HAMMER);
        handheldItem(ModItems.BERYL_HAMMER);
        handheldItem(ModItems.GOLD_HAMMER);
        handheldItem(ModItems.DIAMOND_HAMMER);
        handheldItem(ModItems.NETHERITE_HAMMER);

        //beryl tools
        handheldItem(ModItems.BERYL_SWORD);
        handheldItem(ModItems.BERYL_PICKAXE);
        handheldItem(ModItems.BERYL_AXE);
        handheldItem(ModItems.BERYL_SHOVEL);
        handheldItem(ModItems.BERYL_HOE);
    }


    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(DeferredItem<ArmorItem> itemDeferredItem) {
        final String MOD_ID = TRUTermProject.MOD_ID;

        if (itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial
                        .location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial
                        .location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(
                        armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(
                        trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(
                        currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc,
                        PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile(
                                "item/generated")).texture("layer0",
                                armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc)
                ;

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated")).override()
                        .model(new ModelFile.UncheckedModelFile(
                                trimNameResLoc.getNamespace() + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId()
                                                .getPath()))
                ;
            });
        }
    }

    public void buttonItem(DeferredBlock<?> block,
            DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(),
                mcLoc("block/button_inventory")).texture("texture",
                ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block,
            DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(),
                mcLoc("block/fence_inventory")).texture("texture",
                ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block,
            DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(),
                mcLoc("block/wall_inventory")).texture("wall",
                ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                        "item/" + item.getId().getPath()));
    }
}
