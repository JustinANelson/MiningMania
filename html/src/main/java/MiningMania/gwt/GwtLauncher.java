package MiningMania.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.github.czyzby.websocket.GwtWebSockets;

import MiningMania.MiningMania;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig () {
			// Initiate web sockets module
			GwtWebSockets.initiate();
			// Resizable application, uses available space in
			return new GwtApplicationConfiguration(true);
			// Fixed size application:
			//return new GwtApplicationConfiguration(480, 320);
		}

		@Override
		public ApplicationListener createApplicationListener () { 
			return new MiningMania();
		}
}
