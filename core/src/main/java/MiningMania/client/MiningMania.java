package MiningMania.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.net.URI;
import java.net.URISyntaxException;

import MiningMania.helpers.Variables;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MiningMania extends Game {
	public static WebClient c = null;
	public MiningMania() { super(); }
	@Override
	public void dispose() {
		super.dispose();
	}
	@Override
	public void pause() {
		super.pause();
	}
	@Override
	public void resume() {
		super.resume();
	}
	@Override
	public void render() {
		super.render();
	}
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}
	@Override
	public Screen getScreen() {
		return super.getScreen();
	}
	@Override
	public void create() {
		try {
			System.out.println("Try connect webclient");
			c = new WebClient(new URI( "ws://localhost:8887"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		c.connect();
		c.setTcpNoDelay(true);
		switch (Gdx.app.getType()) {
			case Android:
				System.out.println("Version: " + Gdx.app.getVersion());
				Variables.clientType = Variables.ANDROID_CLIENT;
				Variables.androidVersion = Gdx.app.getVersion();
				break;
			case Desktop:
				Variables.clientType = Variables.DESKTOP_CLIENT;
				break;
			case WebGL:
				Variables.clientType = Variables.HTML_CLIENT;
				break;
			default:
				// Other platforms specific code
		}
		setScreen(new FirstScreen());
	}
}