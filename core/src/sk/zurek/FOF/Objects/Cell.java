package sk.zurek.FOF.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * GRID
 * Created by zurek on 29/12/2016.
 */
public class Cell {
    private Sprite skin;
    private float x,y;

    public Cell(Texture texture, float x, float y){
        this.x=x;
        this.y=y;

        skin=new Sprite(texture);
        skin.setPosition(x, y);
    }

    public void render(SpriteBatch batch){
        skin.draw(batch);
        skin.setPosition(x,y);
    }

    public void update(Texture texture){
        skin.setTexture(texture);
    }
}
