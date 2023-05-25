package dev.mayaqq.nexusframe;

import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import dev.mayaqq.nexusframe.utils.NexusVirtualEntityUtils;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NexusFrame implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("NexusFrame");

    @Override
    public void onInitialize() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            if (Multiblock.attachments.get(pos) != null && Multiblock.elements.get(pos) != null) {
                ElementHolder holder = NexusVirtualEntityUtils.createHolder(pos);
                BlockDisplayElement element = NexusVirtualEntityUtils.createElement(pos);
                HolderAttachment attachment = ChunkAttachment.of(holder, (ServerWorld) world, pos);
                holder.addElement(element);
                Multiblock.attachments.get(pos).destroy();
                Multiblock.attachments.put(pos, attachment);
                Multiblock.elements.put(pos, element);
            }
        });
    }
}
