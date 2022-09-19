package net.thearke.arketweaks.util;

import java.util.Timer;
import java.util.TimerTask;

public class ConnectionState {
    private static boolean hasCancelled = false;
    public TimerTask TASK = new TimerTask() {
        @Override
        public void run() {
            if (ArkeUtil.connectedToServer()) {
                ArkeUtil.setLoginTime(System.currentTimeMillis());
                this.cancel();
                hasCancelled = true;
            }
        }
    };

    public void activate() {
        if (!hasCancelled) {
            new Timer().scheduleAtFixedRate(TASK, 0, 10);
        }
    }
}