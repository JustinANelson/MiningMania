package MiningMania.server;

import org.apache.commons.lang3.SerializationUtils;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import MiningMania.server.Objects.Player;
import jdk.internal.foreign.PlatformLayouts;

import static java.util.Objects.isNull;

/** Launches the server application. */
public class ServerLauncher extends WebSocketServer {
	public ServerLauncher(InetSocketAddress address) {
		super(address);
	}
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conn.send("Welcome to the server!"); //This method sends a message to the new client
		broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
		System.out.println("new connection to " + conn.getRemoteSocketAddress());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
		System.out.println(message);
	}

	@Override
	public void onMessage( WebSocket conn, ByteBuffer message ) {
		//System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
		byte[] yourBytes = new byte[0];
		Player p = new Player();
		yourBytes = message.array();
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);

			p = (Player) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		System.out.println(p.name);
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
	}
	public static void main(String[] args) throws InterruptedException {
		String host = "127.0.0.1";
		int port = 8887;
		boolean running = true;

		WebSocketServer server = new ServerLauncher(new InetSocketAddress(host, port));
		server.run();
		//while (running){
			//System.out.println("server is running");
		//}

		// TODO Implement server application.


	}
}