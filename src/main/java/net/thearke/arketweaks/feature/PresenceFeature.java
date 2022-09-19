package net.thearke.arketweaks.feature;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.world.World;
import net.thearke.arketweaks.ArkeTweaks;
import net.thearke.arketweaks.util.ArkeUtil;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class PresenceFeature implements IPCListener {
    private Timer timer;
    @Getter
    private OffsetDateTime stamp;
    private IPCClient client;

    @Override
    public void onReady(IPCClient client) {
        this.client = client;
        stamp = OffsetDateTime.now();
        richPresence();
        timer = new Timer();
        initTimer();
    }

    private void initTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    client.connect(DiscordBuild.ANY);
                } catch (NoDiscordClientException | IllegalStateException ignored) {}
                richPresence();
            }
        }, 0, 1000);
    }

    private void richPresence() {
        RichPresence.Builder b = new RichPresence.Builder()
                .setStartTimestamp((ArkeUtil.getLoginTime() / 1000L))
                .setLargeImage("arke", "The Arke | 1.18.2");
        MinecraftClient mc = MinecraftClient.getInstance();
        ServerInfo server = mc.getCurrentServerEntry();

        if (server != null) {
            assert mc.player != null;
            server = mc.getCurrentServerEntry();

            assert server != null;
            if (ArkeUtil.connectedToServer() && mc.player != null) {
                ArkeTweaks.LOGGER.info("World " + mc.player.world.getRegistryKey().getValue());
                if (!Objects.equals(mc.player.world.getRegistryKey().getValue().toString(), "minecraft:lobby")) {
                    World gameWorld = mc.player.world;
                    b.setState("In a game with " + gameWorld.getPlayers().size() + " players");
                } else {
                    b.setState("In a Lobby");
                }
                b.setDetails("play.thearke.net");
            }

            try {
                client.sendRichPresence(b.build());
            } catch (IllegalStateException e) {
                ArkeTweaks.LOGGER.warn("Discord Presence skipped because Discord is no longer detected.");
            }
        }
    }

}
