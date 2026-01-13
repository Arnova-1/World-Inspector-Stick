package org.arnova.worldinspectorstick.item

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.biome.Biome

fun inspectBlock(world: World, pos: BlockPos, player: PlayerEntity) {
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