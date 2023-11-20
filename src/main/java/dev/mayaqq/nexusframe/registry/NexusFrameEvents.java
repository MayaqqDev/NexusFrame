package dev.mayaqq.nexusframe.registry;

import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class NexusFrameEvents {
    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            Multiblock.MultiblockPreviewElement.safeMatchCheck(world, pos);
        });
    }
}
