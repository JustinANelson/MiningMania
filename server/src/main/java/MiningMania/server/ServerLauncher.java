package MiningMania.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import MiningMania.networking.ServerHandleData;
import MiningMania.objects.Enemy;
import MiningMania.objects.Player;

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
		broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
		System.out.println("new connection to " + conn.getRemoteSocketAddress());
	}
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
		//Variables.addresses.remove(conn.getLocalSocketAddress());
	}
	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
		System.out.println(message);
	}
	@Override
	public void onMessage( WebSocket conn, ByteBuffer message ) {
		//System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
		byte[] yourBytes;
		yourBytes = message.array();
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);

		try (ObjectInput in = new ObjectInputStream(bis)) {
			Object obj = in.readObject();
			if (obj instanceof Player) {
				ServerHandleData.HandlePlayer((Player) obj);
				conn.send("Player Object Received");
			}
			if (obj instanceof Enemy) {
				ServerHandleData.HandleEnemy((Enemy) obj);
				conn.send("Enemy Object Received");
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
	public void onStart() {
		System.out.println("server started successfully");
		setConnectionLostTimeout(0);
		setConnectionLostTimeout(100);
		//Variables.addresses.clear();
		serverLoop();
	}
	public static void serverLoop() {
		while(running) {
			System.out.println("Server is Running");
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