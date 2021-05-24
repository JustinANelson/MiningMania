package MiningMania.server;

import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import MiningMania.networking.packetData.HandleServerData;
import MiningMania.networking.packets.Packet;

import static MiningMania.server.ServerLoop.serverLoop;

/** Launches the server application. */
public class ServerLauncher extends WebSocketServer {
	public static WebSocketServer server;
	public static boolean running = true;

	public ServerLauncher(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conn.send("Welcome to the server!"); //This method sends a message to the new
		//Variables.addresses.add(conn.getLocalSocketAddress());
		broadcast("new connection: " + handshake.getResourceDescriptor()); //This method sends a message to all clients connected
		System.out.println("new connection to " + conn.getRemoteSocketAddress());
	}
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
		//Variables.addresses.remove(conn.getLocalSocketAddress());
	}
	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);
		System.out.println(message);
	}
	@Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		byte[] yourBytes;
		yourBytes = message.array();
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		try (ObjectInput in = new ObjectInputStream(bis)) {
			Object object = in.readObject();
			if (object instanceof Packet) {
				HandleServerData.checkPackets(object);
			}
			else {
				conn.closeConnection(1, "Invalid packet.");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ignore close exception
	}
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}
	@Override
	public void onWebsocketPing(WebSocket conn, Framedata f) {
		super.onWebsocketPong(conn, f);
		System.out.println("ping");
	}
	@Override
	public void onStart() {
		System.out.println("server started successfully");
		setConnectionLostTimeout(0);
		setConnectionLostTimeout(100);
		//Variables.addresses.clear();
		try {
			serverLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		initServer();
	}
	public static void initServer() {
		String host = "127.0.0.1";
		int port = 8887;
		server = new ServerLauncher(new InetSocketAddress(host, port));
		server.run();
	}
}