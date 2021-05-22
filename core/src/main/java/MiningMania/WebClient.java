package MiningMania;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.nio.ByteBuffer;

import MiningMania.Objects.Player;

public class WebClient extends WebSocketClient {

    public WebClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }
    public WebClient(URI serverURI) {
        super(serverURI);
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("new connection opened");
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
}
