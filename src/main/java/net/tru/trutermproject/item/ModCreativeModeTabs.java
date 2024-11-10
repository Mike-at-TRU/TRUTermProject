package net.tru.trutermproject.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, TRUTermProject.MOD_ID);

    public static Supplier<CreativeModeTab> ALL_MOD_ITEMS = CREATIVE_MODE_TAB.register(
            "term_project_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.BERYL_HAMMER.get()))
                    .title(Component.translatable(
                            "creativetab.trutermproject.term_project_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //output.accept(ModItems.ITEM); this is how to add things
                        output.accept((ModItems.RAW_BERYL));
                        output.accept((ModItems.BERYL));
                        output.accept(ModItems.WOODEN_HAMMER);
                        output.accept(ModItems.STONE_HAMMER);
                        output.accept(ModItems.IRON_HAMMER);
                        output.accept(ModItems.BERYL_HAMMER);
                        output.accept(ModItems.GOLD_HAMMER);
                        output.accept(ModItems.DIAMOND_HAMMER);
                        output.accept(ModItems.NETHERITE_HAMMER);
                    }).build());

    public static Supplier<CreativeModeTab> ALL_MOD_BLOCKS = CREATIVE_MODE_TAB.register(
            "term_project_blocks", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.BERYL_ORE))
                    //inorder to define order
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(
                            TRUTermProject.MOD_ID, "term_project_tab"))
                    .title(Component.translatable(
                            "creativetab.trutermproject.term_project_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //output.accept(ModBlocks.BLOCK); this is how to add things
                        output.accept(ModBlocks.BERYL_DEEPSLATE_ORE);
                        output.accept(ModBlocks.BERYL_NETHER_ORE);
                        output.accept(ModBlocks.BERYL_ORE);
                        output.accept(ModBlocks.BERYL_END_ORE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
