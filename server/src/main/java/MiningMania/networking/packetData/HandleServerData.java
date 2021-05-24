package MiningMania.networking.packetData;

import MiningMania.networking.packets.Connect;

public class HandleServerData {
    public static void checkPackets(Object object) {
        if (object instanceof Connect) {
            HandleConnect((Connect) object);
        }
    }
    public static void HandleConnect(Connect obj) {
        Connect c = new Connect();
        c.build_version = obj.build_version;
        c.client_type = obj.client_type;
        c.androidVersion = obj.androidVersion;

    }
}
