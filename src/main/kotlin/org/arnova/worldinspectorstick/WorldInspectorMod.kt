package org.arnova.worldinspectorstick

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import org.arnova.worldinspectorstick.item.WorldInspectorController

object WorldInspectorMod : ModInitializer {

    const val MOD_ID = "worldinspectorstick"

    val WORLD_INSPECTOR_STICK_ID: Identifier? =
        Identifier.of(MOD_ID, "world_inspector_stick")

    override fun onInitialize() {
        val INSPECTOR_STICK = Registry.register(
            Registries.ITEM,
            WORLD_INSPECTOR_STICK_ID,
            WorldInspectorController(
                Item.Settings()
                    .maxCount(1)
                    .registryKey(
                        RegistryKey.of(
                            Registries.ITEM.key,
                            WORLD_INSPECTOR_STICK_ID
                        )
                    )
            )
        )

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL)
            .register { entries ->
                entries.add(INSPECTOR_STICK)
            }
    }
}

