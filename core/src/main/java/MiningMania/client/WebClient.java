package MiningMania.client;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class WebClient extends WebSocketClient {

    public WebClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }
    public WebClient(URI serverURI) {
        super(serverURI);
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }
    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }
    @Override
    public void onMessage(ByteBuffer message) {

    }
    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    @Override
    public void onWebsocketPong(WebSocket conn, Framedata f) {
        super.onWebsocketPong(conn, f);
        System.out.println("Pong");
    }
}
