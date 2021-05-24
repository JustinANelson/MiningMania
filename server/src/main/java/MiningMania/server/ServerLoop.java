package MiningMania.server;

import java.util.Timer;
import java.util.TimerTask;

public class ServerLoop {
    static int x;
    static long lastUpdateTime_FPS;
    static long updateTimer_FPS = 1000;
    public static void serverLoop() throws InterruptedException {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //System.out.println("Running: " + new java.util.Date());
                if (lastUpdateTime_FPS < System.currentTimeMillis()) {
                    System.out.println(x);
                    x=0;
                    lastUpdateTime_FPS = System.currentTimeMillis() + updateTimer_FPS;
                }
                x++;
            }
        }, 0, 17);
    }
}
