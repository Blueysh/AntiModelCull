package me.blueysh.antimodelcull;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class AntiModelCull implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("AntiModelCull");

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from AntiModelCull!");
    }

}
