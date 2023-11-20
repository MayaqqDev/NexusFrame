package dev.mayaqq.nexusframe;

import dev.mayaqq.nexusframe.registry.NexusFrameEvents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NexusFrame implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("NexusFrame");

    @Override
    public void onInitialize() {
        NexusFrameEvents.register();
    }

    public static Identifier id(String path) {
        return new Identifier("nexusframe", path);
    }
}
