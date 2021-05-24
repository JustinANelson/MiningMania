package MiningMania.client;

import com.badlogic.gdx.Screen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import MiningMania.helpers.Variables;
import MiningMania.networking.packets.Connect;
import MiningMania.objects.Enemy;
import MiningMania.objects.Player;

import static MiningMania.client.MiningMania.c;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
	public static int i = 0;

	@Override
	public void show() {
		// Prepare your screen here.
	}
	@Override
	public void render(float delta) {
		// Draw your screen here. "delta" is the time since last render in seconds.
		if(c.isOpen()) {
			if (i == 0) {
				System.out.println("Client running");
				Connect c = new Connect();
				c.client_mode = Variables.clientMode;
				c.build_version = Variables.buildVersion;
				sendPacket(c);
				i = 1;
			}
		}
	}
	@Override
	public void resize(int width, int height) {
		// Resize your screen here. The parameters represent the new window size.
	}
	@Override
	public void pause() {
		// Invoked when your application is paused.
	}
	@Override
	public void resume() {
		// Invoked when your application is resumed after pause.
	}
	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}
	@Override
	public void dispose() {
		// Destroy screen's assets here.
	}
	public void sendPacket(Object obj) {
		if (c.isOpen()) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = null;
			byte[] yourBytes = new byte[0];
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(obj);
				out.flush();
				yourBytes = bos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bos.close();
				} catch (IOException ex) {
					// ignore close exception
				}
			}
			c.send(yourBytes);
		}
	}
}