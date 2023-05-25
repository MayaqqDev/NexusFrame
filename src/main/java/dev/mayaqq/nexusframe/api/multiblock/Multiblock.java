package dev.mayaqq.nexusframe.api.multiblock;

import dev.mayaqq.nexusframe.utils.NexusVirtualEntityUtils;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.ChunkAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.function.Predicate;

import static dev.mayaqq.nexusframe.NexusFrame.LOGGER;

public class Multiblock  {
    private char[][][] pattern;
    private final HashMap<Character, Predicate<BlockState>> predicates;
    private final boolean shouldPreview;
    private final int width;
    private final int height;
    private final int length;
    private boolean previewing;
    private int previewed = 0;

    public static HashMap<BlockPos, HolderAttachment> attachments = new HashMap<>();
    public static HashMap<BlockPos, BlockDisplayElement> elements = new HashMap<>();

    public Multiblock(char[][][] pattern, HashMap<Character, Predicate<BlockState>> predicates, boolean shouldPreview) {
        this.pattern = pattern;
        this.predicates = predicates;
        this.width = pattern[0].length;
        this.height = pattern.length;
        this.length = pattern[0][0].length;
        this.shouldPreview = shouldPreview;
        this.previewing = false;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLength() {
        return this.length;
    }

    public boolean getPreviewed() {
        return this.previewing;
    }

    public boolean check(BlockPos pos, World world) {
        //find the $ in the pattern
        BlockPos corner = findOffset(pos);
        if (corner == null) {
            LOGGER.error("Multiblock pattern does not contain $");
            return false;
        }
        boolean result = true;
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                for (int k = 0; k < pattern[i][j].length; k++) {
                    BlockPos blockPos = corner.add(j, i, k);
                    Predicate<BlockState> predicate = predicates.get(pattern[i][j][k]);
                    boolean isRightBlock = predicate.test(world.getBlockState(blockPos));
                    //if the block is already in the map, remove it
                    if (shouldPreview) {
                        BlockDisplayElement element = NexusVirtualEntityUtils.createElement(blockPos);
                        if (attachments.containsKey(blockPos)) {
                            attachments.get(blockPos).destroy();
                            attachments.remove(blockPos);
                            elements.remove(blockPos);
                            previewed = 0;
                        } else {
                            previewed++;
                            // if the block is not in the map, add it and make cool
                            ElementHolder holder = NexusVirtualEntityUtils.createHolder(blockPos);
                            HolderAttachment attachment = ChunkAttachment.of(holder, (ServerWorld) world, blockPos);
                            // TODO: optimize this
                            for (Block state : Registries.BLOCK) {
                                BlockState blockState = state.getDefaultState();
                                if (predicate.test(blockState)) {
                                    element.setBlockState(blockState);
                                    break;
                                }
                            }
                            // if the block is not air and is not the right block, make it glow
                            if (!world.getBlockState(blockPos).isAir() && !isRightBlock) {
                                element.setGlowing(true);
                            }
                            // make the scale 0 if the block is right, so its invisible, and 0.5 if its wrong so its visible
                            if (!isRightBlock) {
                                element.setScale(new Vector3f(0.5F, 0.5F, 0.5F));
                            } else {
                                element.setScale(new Vector3f(0.0F, 0.0F, 0.0F));
                            }
                            // put the block in the map
                            holder.addElement(element);
                            attachments.put(blockPos, attachment);
                            elements.put(blockPos, element);
                        }
                    }
                    // if the block is not the right block, return false
                    if (!isRightBlock) {
                        result = false;
                    }
                }
            }
        }
        if (previewed > 0) {
            previewing = true;
        } else {
            previewing = false;
        }
        return result;
    }

    public void rotate() {
        // does not work yet
        int height = getHeight();
        int width = getWidth();
        int length = getLength();

        char[][][] rotated = new char[height][width][length];

        for (int c = 0; c < height; c++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    rotated[c][i][j] = pattern[c][width - 1 - j][i];
                }
            }
        }
        pattern = rotated;
    }

    public BlockPos findOffset(BlockPos pos) {
        //find the corner of the pattern
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                for (int k = 0; k < this.length; k++) {
                    if (pattern[i][j][k] == '$') {
                        return pos.add(-j, -i, -k);
                    }
                }
            }
        }
        return null;
    }
}
