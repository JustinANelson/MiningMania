package MiningMania.networking;

import MiningMania.objects.Enemy;
import MiningMania.objects.Player;

public class ServerHandleData {
    public static void HandlePlayer(Player obj) {
        Player p = new Player();
        p.setName(obj.getName());
        System.out.println(p.getName());
    }
    public static void HandleEnemy(Enemy obj) {
        Enemy e = new Enemy();
        e.setName(obj.getName());
        System.out.println(e.getName());
    }
}
