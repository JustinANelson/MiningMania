package MiningMania.console;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ConsoleClient extends WebSocketClient {
    public ConsoleClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Console connection established.\n");
        ServerConsole.c.send("Console Connected.");
    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
