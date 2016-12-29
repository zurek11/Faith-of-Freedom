package sk.zurek.FOF.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Button
 * Created by zurek on 29/12/2016.
 */
public class Button {

    private Sprite skin;
    private int id;

    public Button(Texture texture, float x, float y,int id) {
        this.id=id;
        skin=new Sprite(texture);
        skin.setPosition(x, y);
    }

    public Button(Texture texture,int id) {
        this.id=id;
        skin=new Sprite(texture);
    }

    public void draw(SpriteBatch batch,float x, float y) {
        skin.draw(batch);
        skin.setPosition(x, y);
    }

    public int getID(float x, float y) {
        if (x > skin.getX() && x < skin.getX() + skin.getWidth()) {
            if (y > skin.getY() && y < skin.getY() + skin.getHeight()) {
                return id;
            }
        }
        return -1;
    }

}

