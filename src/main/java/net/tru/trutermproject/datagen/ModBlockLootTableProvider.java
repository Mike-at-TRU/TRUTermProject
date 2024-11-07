package net.tru.trutermproject.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.tru.trutermproject.block.ModBlocks;
import net.tru.trutermproject.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        add(ModBlocks.BERYL_ORE.get(), block ->
                createMultipleOreDrops(ModBlocks.BERYL_ORE.get(), ModItems.RAW_BERYL.get(),
                        1f, 1f));

        add(ModBlocks.BERYL_DEEPSLATE_ORE.get(), block ->
                createMultipleOreDrops(ModBlocks.BERYL_DEEPSLATE_ORE.get(), ModItems.RAW_BERYL.get(),
                        1f, 1f));

        add(ModBlocks.BERYL_NETHER_ORE.get(), block ->
                createMultipleOreDrops(ModBlocks.BERYL_NETHER_ORE.get(), ModItems.RAW_BERYL.get(),
                        1f, 1f));

        add(ModBlocks.BERYL_END_ORE.get(), block ->
                createMultipleOreDrops(ModBlocks.BERYL_END_ORE.get(), ModItems.RAW_BERYL.get(),
                        1f, 1f));

    }

    @Override
    //because this is all the blocks all will need a loot table
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }


    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item itemToBeDroppedWithoutSilkTouch, float minimumNumberOfItemsToBeDropped, float maximumNumberOfItemsToBeDropped) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(itemToBeDroppedWithoutSilkTouch)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minimumNumberOfItemsToBeDropped, maximumNumberOfItemsToBeDropped)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}