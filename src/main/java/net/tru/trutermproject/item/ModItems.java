package net.tru.trutermproject.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.custom.HammerItem;

public class ModItems {

    //TODO why hammer not break iron ore?
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(
            TRUTermProject.MOD_ID);

    /*items will be
    public static final DeferredItem<Item> NAME = ITEMS.register("name",
            ()-> new Item(new Item.Properties()));
            the item will be in game and can be accessed with commands
            you can add it to a creative mode tab to make testing easier, and you need json files to give it a texture

   */
    public static final DeferredItem<Item> BERYL = ITEMS.register("beryl",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BERYL = ITEMS.register(
            "raw_beryl", () -> new Item(new Item.Properties()));

    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register(
            "iron_hammer", () -> new HammerItem(Tiers.IRON,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(Tiers.IRON, 7F,
                                    -3.5f)), 1));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
