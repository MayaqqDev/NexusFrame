package dev.mayaqq.nexusframe.api.multiblock;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;

import java.io.File;
import java.nio.file.Path;

import static dev.mayaqq.nexusframe.NexusFrame.LOGGER;

// TODO: Implement this
public class MultiNbt {
    public static char[][][] getMultiblockFromNbt(String name) {
        try {
            Path path = Path.of(FabricLoader.getInstance().getConfigDir() + "/nexusframe");
            File file = new File(path.toFile(), name + ".nbt");
            NbtCompound nbt = NbtIo.read(file);
            LOGGER.info("NBT: " + nbt);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error while reading NBT file: " + e);
            return null;
        }
    }
}
