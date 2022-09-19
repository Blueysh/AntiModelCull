package net.thearke.arketweaks.util;

import net.minecraft.client.MinecraftClient;

public class ArkeUtil {
    public static boolean connectedToServer() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getCurrentServerEntry() == null) {
            return false;
        } else return client.getCurrentServerEntry().address.toLowerCase().contains("thearke.net");
    }
}
