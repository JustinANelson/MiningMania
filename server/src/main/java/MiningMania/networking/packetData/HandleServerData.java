package MiningMania.networking.packetData;

import MiningMania.networking.packets.Connect;
import MiningMania.objects.Enemy;
import MiningMania.objects.Player;

public class HandleServerData {
    public static void checkPackets(Object object) {
        if (object instanceof Connect) {
            HandleConnect((Connect) object);
        }
    }
    public static void HandleConnect(Connect obj) {
        Connect c = new Connect();
        c.build_version = obj.build_version;
        System.out.println(c.build_version);
    }
}
