package net.tru.trutermproject.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tru.trutermproject.TRUTermProject;

public class ModTags {

    //if one item in a tag is wrong the game throws out the whole thing

    public static class Blocks {
        public static final TagKey<Block> TRU_TERM_PROJECT_BLOCKS = createTag(
                "tru_blocks");
        public static final TagKey<Block> BERYL_ORE = createTag("beryl_ore");
        public static final TagKey<Block> INCORRECT_FOR_BERYL_TOOL = createTag(
                "incorrect_for_beryl_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(
                    ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                            name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRU_TERM_PROJECT_ITEMS = createTag(
                "tru_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(
                    ResourceLocation.fromNamespaceAndPath(TRUTermProject.MOD_ID,
                            name));
        }
    }
}
