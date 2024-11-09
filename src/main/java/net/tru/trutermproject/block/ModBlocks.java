package net.tru.trutermproject.block;

import net.minecraft.client.resources.model.Material;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.obj.ObjMaterialLibrary;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(
            TRUTermProject.MOD_ID);
/*
how to create a block
    public static final DeferredBlock<Block> BLOCK_NAME = registerBlock("block_name",
            ()-> new Block(BlockBehaviour.Properties.of().strength(2f)));
            .Properties.of() has a lot of things but I would say add strength, sound, and requiresCorrectToolForDrop
            also what are anonymous classes? use that to append hover text
*/

    public static final DeferredBlock<Block> BERYL_ORE = registerBlock(
            "beryl_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BERYL_DEEPSLATE_ORE = registerBlock(
            "beryl_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(4.5f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BERYL_END_ORE = registerBlock(
            "beryl_end_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(5f)
                            .requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BERYL_NETHER_ORE = registerBlock(
            "beryl_nether_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f)
                            .requiresCorrectToolForDrops()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
            Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //when you register a block you need to register an associated item so this is a method to do that
    private static <T extends Block> void registerBlockItem(String name,
            DeferredBlock<T> block) {
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
