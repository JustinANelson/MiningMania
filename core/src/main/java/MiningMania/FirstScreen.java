package MiningMania;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.Json;

import org.apache.commons.lang3.SerializationUtils;
import org.java_websocket.util.ByteBufferUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import MiningMania.Objects.Player;

import static MiningMania.Helpers.Variables.firstRun;
import static MiningMania.Helpers.Variables.name;
import static MiningMania.Helpers.Variables.newPlayerReceived;
import static MiningMania.MiningMania.c;

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
		if(i == 0) {
			if(c.isOpen()) {
				Player p = new Player("Krynger");
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream out = null;
				byte[] yourBytes = new byte[0];
				try {
					out = new ObjectOutputStream(bos);
					out.writeObject(p);
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
}