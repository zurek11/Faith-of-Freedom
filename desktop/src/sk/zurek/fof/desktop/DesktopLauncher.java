package sk.zurek.fof.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import sk.zurek.FOF.FOF;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Faith of Freedom";
        config.foregroundFPS= FOF.GAME_FPS;
        config.width= FOF.SCREEN_WIDTH;
        config.height= FOF.SCREEN_HEIGHT;
        config.resizable=false;
		new LwjglApplication(new FOF(), config);
	}
}
