package org.arnova.worldinspectorstick.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class WorldInspectorController(settings: Item.Settings) : Item(settings) {

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world: World = context.world

        if(world.isClient) {
            return ActionResult.SUCCESS
        }

        val player: PlayerEntity = context.player ?: return ActionResult.PASS
        val pos: BlockPos = context.blockPos

        inspectBlock(world, pos, player)

        return ActionResult.SUCCESS
    }
}