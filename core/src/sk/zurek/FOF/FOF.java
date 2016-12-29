package sk.zurek.FOF;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.zurek.FOF.Screens.MapEditor;

public class FOF extends com.badlogic.gdx.Game {
    public static final int GAME_FPS=60;
    public static final int SCREEN_WIDTH=800;
    public static final int SCREEN_HEIGHT=600;

    private SpriteBatch batch;

    @Override
    public void create(){
        batch = new SpriteBatch();
        this.setScreen(new MapEditor());
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
