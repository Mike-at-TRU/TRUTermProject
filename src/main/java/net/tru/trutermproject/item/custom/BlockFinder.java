package net.tru.trutermproject.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockFinder extends Item {
    TagKey<Block> blocksToFind;

    public BlockFinder(Properties properties, TagKey<Block> blocksToFind) {
        super(properties);
        this.blocksToFind = blocksToFind;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel()
                        .getBlockState(positionClicked.below(i));

                if (isValuableBlock(state)) {

                    outputFirstBlockCoordinates(positionClicked.below(i),
                            player, state.getBlock());
                    foundBlock = true;

                    break;

                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No Blocks Found!"));
            }

            pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                    EquipmentSlot.MAINHAND);
        }
        return InteractionResult.SUCCESS;
    }

    private static void outputFirstBlockCoordinates(BlockPos blockPos,
            Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(
                block.getDescriptionId()) + " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }


    private boolean isValuableBlock(BlockState state) {

        return state.is(blocksToFind);
    }
}
