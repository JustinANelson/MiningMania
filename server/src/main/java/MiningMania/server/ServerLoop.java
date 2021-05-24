package MiningMania.server;

import java.util.Timer;
import java.util.TimerTask;

import MiningMania.Shared;
import MiningMania.SharedVars;

public class ServerLoop {
    static int x;
    static long lastUpdateTime_FPS;
    static long updateTimer_FPS = 1000;
    static int currentFPS;
    public static void serverLoop() throws InterruptedException {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (lastUpdateTime_FPS < System.currentTimeMillis()) {
                    currentFPS = x;
                    x=0;
                    lastUpdateTime_FPS = System.currentTimeMillis() + updateTimer_FPS;
                }
                x++;
            }
        }, 0, 17);
    }
}
