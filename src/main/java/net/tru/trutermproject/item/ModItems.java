package net.tru.trutermproject.item;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.tru.trutermproject.TRUTermProject;
import net.tru.trutermproject.item.custom.BlockFinder;
import net.tru.trutermproject.item.custom.HammerItem;
import net.tru.trutermproject.util.ModTags;

public class ModItems {
    private static final float SWORD_ATTACK = 3f;
    private static float SWORD_SPEED = -2.4f;
    private static float PICKAXE_ATTACK = 1f;
    private static final float PICKAXE_SPEED = -2.8f;
    private static float SHOVEL_ATTACK = 1.5f;
    private static float SHOVEL_SPEED = -3f;
    private static float HAMMER_ATTACK = 7;
    private static float HAMMER_SPEED = -3.5f;
    //axe and hoe not consistent todo learn more about axe
    //hoe seems to just be negate the attack in the tier don't know about speed
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

    public static final DeferredItem<Item> BERYL_GEODE = ITEMS.register(
            "beryl_geode", () -> new Item(new Item.Properties()));


    //todo figure out how to keep hammer and just damage it

    public static final DeferredItem<BlockFinder> BERYL_ORE_FINDER = ITEMS.register(
            "beryl_ore_finder", () -> new BlockFinder(new Item.Properties(),
                    ModTags.Blocks.BERYL_ORE));

    //HAMMER TIME
    public static final DeferredItem<HammerItem> WOODEN_HAMMER = ITEMS.register(
            "wooden_hammer", () -> new HammerItem(Tiers.WOOD,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(Tiers.WOOD,
                                    HAMMER_ATTACK, -1.5f)), 1));
    public static final DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register(
            "stone_hammer", () -> new HammerItem(Tiers.STONE,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(Tiers.STONE,
                                    HAMMER_ATTACK, HAMMER_SPEED)), 1));


    public static final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register(
            "iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties().attributes(

                    PickaxeItem.createAttributes(Tiers.IRON, 7F, -3.5f)), 1));


    public static final DeferredItem<HammerItem> GOLD_HAMMER = ITEMS.register(
            "gold_hammer", () -> new HammerItem(Tiers.GOLD,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(Tiers.GOLD,
                                    HAMMER_ATTACK, HAMMER_SPEED)), 2));
    public static final DeferredItem<HammerItem> BERYL_HAMMER = ITEMS.register(
            "beryl_hammer", () -> new HammerItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(ModToolTiers.BERYL,
                                    HAMMER_ATTACK, HAMMER_SPEED)), 1));
    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register(
            "diamond_hammer", () -> new HammerItem(Tiers.DIAMOND,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(Tiers.DIAMOND,
                                    HAMMER_ATTACK, HAMMER_SPEED)), 2));
    public static final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register(
            "netherite_hammer", () -> new HammerItem(Tiers.NETHERITE,
                    new Item.Properties().attributes(
                            HammerItem.createAttributes(Tiers.IRON,
                                    HAMMER_ATTACK, HAMMER_SPEED)), 2));

    // The Rest Of BERYL TOOLS
    public static final DeferredItem<SwordItem> BERYL_SWORD = ITEMS.register(
            "beryl_sword", () -> new SwordItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(ModToolTiers.BERYL,
                                    SWORD_ATTACK, SWORD_SPEED))));
    public static final DeferredItem<PickaxeItem> BERYL_PICKAXE = ITEMS.register(
            "beryl_pickaxe", () -> new PickaxeItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(ModToolTiers.BERYL,
                                    PICKAXE_ATTACK, PICKAXE_SPEED))));
    public static final DeferredItem<AxeItem> BERYL_AXE = ITEMS.register(
            "beryl_axe", () -> new AxeItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            AxeItem.createAttributes(ModToolTiers.BERYL, 6,
                                    -3))));
    public static final DeferredItem<ShovelItem> BERYL_SHOVEL = ITEMS.register(
            "beryl_shovel", () -> new ShovelItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            ShovelItem.createAttributes(ModToolTiers.BERYL,
                                    SHOVEL_ATTACK, SHOVEL_SPEED))));
    public static final DeferredItem<HoeItem> BERYL_HOE = ITEMS.register(
            "beryl_hoe", () -> new HoeItem(ModToolTiers.BERYL,
                    new Item.Properties().attributes(
                            HoeItem.createAttributes(ModToolTiers.BERYL,
                                    -ModToolTiers.BERYL.getAttackDamageBonus(),
                                    -2))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
