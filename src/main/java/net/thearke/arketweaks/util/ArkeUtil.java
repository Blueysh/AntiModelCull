package net.thearke.arketweaks.util;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;

public class ArkeUtil {
    @Getter
    @Setter
    private static Long loginTime = -1L;

    public static boolean connectedToServer() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.getCurrentServerEntry() == null) {
            return false;
        } else return client.getCurrentServerEntry().address.contains("thearke.net");
    }
}
