package net.tru.trutermproject.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TRUTermProject.MOD_ID);

    /*items will be
    public static final DeferredItem<Item> NAME = ITEMS.register("name",
            ()-> new Item(new Item.Properties()));
            the item will be in game and can be accessed with commands
            you can add it to a creative mode tab to make testing easier and you need json files to give it a texture

   */

    public static final DeferredItem<Item> BERYL = ITEMS.register("beryl",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
