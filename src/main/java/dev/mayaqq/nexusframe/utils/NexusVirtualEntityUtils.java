package dev.mayaqq.nexusframe.utils;

import dev.mayaqq.nexusframe.api.multiblock.Multiblock;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class NexusVirtualEntityUtils {
    public static ElementHolder createHolder(BlockPos pos) {
        return new ElementHolder() {
            @Override
            public Vec3d getPos() {
                return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
            }
        };
    }
    public static BlockDisplayElement createElement(BlockPos pos) {
        BlockDisplayElement element = new BlockDisplayElement();
        element.setBlockState(Multiblock.elements.get(pos).getBlockState());
        element.setGlowing(false);
        element.setScale(new Vector3f(0.5F, 0.5F, 0.5F));
        element.setOffset(new Vec3d(0.25F, 0.25F, 0.25F));
        return element;
    }
}
