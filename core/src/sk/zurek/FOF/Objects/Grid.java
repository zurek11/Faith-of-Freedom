package sk.zurek.FOF.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * GRID
 * Created by zurek on 29/12/2016.
 */
public class Grid {
    private static Texture active;
    private static Texture inactive;
    private float x,y;

    public boolean change=false;

    public Grid(float x,float y){
        this.x=x;
        this.y=y;

        if(inactive==null)inactive=new Texture("grid_inactive.png");
        if(active==null)active=new Texture("grid_active.png");
    }

    public void render(SpriteBatch batch){
        if(change)batch.draw(active,x,y);
        else batch.draw(inactive,x,y);
    }
}
