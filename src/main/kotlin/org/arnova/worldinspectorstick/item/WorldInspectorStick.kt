package org.arnova.worldinspectorstick.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.biome.Biome

class WorldInspectorStick(settings: Item.Settings) : Item(settings) {

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

    private fun inspectBlock(world: World, pos: BlockPos, player: PlayerEntity) {
        val state: BlockState = world.getBlockState(pos)
        val block: Block = state.block

        val blockId: Identifier = Registries.BLOCK.getId(block)

        val properties: String = state.entries.entries.joinToString { (property, value) ->
            "${property.name}=$value"
        }

        val light: Int = world.getLightLevel(pos)

        val biomeKey: RegistryKey<Biome> = world.getBiome(pos).key.orElse(null)

        val chunkLoaded: Boolean = world.isChunkLoaded(pos)

        player.sendMessage(
            Text.literal("Block: $blockId\n$properties\nLight: $light\nbiome: $biomeKey\nChunk: $chunkLoaded"),
            false
        )
    }
}