package net.thearke.arketweaks;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import net.fabricmc.api.ModInitializer;
import net.thearke.arketweaks.feature.PresenceFeature;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ArkeTweaks implements ModInitializer {
    public static Logger LOGGER = LoggerFactory.getLogger("ArkeTweaks");

    // For PresenceFeature
    public static final IPCClient client = new IPCClient(1020745007667363860L);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from ArkeTweaks!");

        initPresenceFeature();
    }

    private void initPresenceFeature() {
        client.setListener(new PresenceFeature());
        try {
            client.connect(DiscordBuild.ANY);
            LOGGER.info("Connected to Discord successfully!");
        } catch (NoDiscordClientException e) {
            LOGGER.info("Discord was not detected.");
        } catch (IllegalStateException ignored) {
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
