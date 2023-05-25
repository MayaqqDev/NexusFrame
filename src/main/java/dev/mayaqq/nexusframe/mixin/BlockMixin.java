package dev.mayaqq.nexusframe.mixin;

import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import dev.mayaqq.nexusframe.utils.*;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onPlaced", at = @At("HEAD"))
    private void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (world.isClient) return;
        if (Multiblock.attachments.get(pos) != null && Multiblock.elements.get(pos) != null) {
            ElementHolder holder = NexusVirtualEntityUtils.createHolder(pos);
            BlockDisplayElement element = NexusVirtualEntityUtils.createElement(pos);
            HolderAttachment attachment = ChunkAttachment.of(holder, (ServerWorld) world, pos);
            if (!(((Block) (Object) this).getDefaultState() == Multiblock.elements.get(pos).getBlockState())) {
                element.setGlowing(true);
                element.setScale(new Vector3f(0.5F, 0.5F, 0.5F));
            } else {
                element.setScale(new Vector3f(0.0F, 0.0F, 0.0F));
            }
            holder.addElement(element);
            Multiblock.attachments.get(pos).destroy();
            Multiblock.attachments.put(pos, attachment);
            Multiblock.elements.put(pos, element);
        }
    }
}