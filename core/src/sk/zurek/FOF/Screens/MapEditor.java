package sk.zurek.FOF.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import sk.zurek.FOF.Objects.Button;
import sk.zurek.FOF.Objects.Grid;

public class MapEditor implements Screen {
    private static final int SPEED=100;
    private static final int CAM_WIDTH=800;
    private static final int CAM_HEIGHT=600;
    private static final int GRID_WIDTH=2400;
    private static final int GRID_HEIGHT=2400;
    private static final int CELL_SIZE=60;

    private Texture inactive_txt;
    private Texture active1_txt;
    private Texture active2_txt;
    private Texture active3_txt;
    private Button inactive_but,active1_but,active2_but,active3_but;
    private int choice;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font = new BitmapFont();
    private Vector3 viewPos;
    private Grid[][] cells;
    private float x=GRID_WIDTH/2,y=GRID_HEIGHT/2;

    public MapEditor(){
        initButtons();
        batch = new SpriteBatch();
        camera=new OrthographicCamera(CAM_WIDTH,CAM_HEIGHT);
        cells = new Grid[GRID_WIDTH/CELL_SIZE][GRID_HEIGHT/CELL_SIZE];
        viewPos = new Vector3();

        for(int i=0;i<GRID_WIDTH/CELL_SIZE;++i){
            for(int j=0;j<GRID_HEIGHT/CELL_SIZE;++j){
                cells[i][j]=new Grid(inactive_txt,i*CELL_SIZE,j*CELL_SIZE);
            }
        }
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(viewPos);

        batch.begin();
        font.draw(batch,(int)viewPos.x+"/"+(int)viewPos.y,x-CAM_WIDTH/2, y-(CAM_HEIGHT/2)+12);
        for(int i=0;i<GRID_WIDTH/CELL_SIZE;++i){
            for(int j=0;j<GRID_HEIGHT/CELL_SIZE;++j){
                cells[i][j].render(batch);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            x+=SPEED*Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            x-=SPEED*Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            y+=SPEED*Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            y-=SPEED*Gdx.graphics.getDeltaTime();
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            if(inactive_but.getID(viewPos.x,viewPos.y)>=0)choice=0;
            else if(active1_but.getID(viewPos.x,viewPos.y)>=0)choice=1;
            else if(active2_but.getID(viewPos.x,viewPos.y)>=0)choice=2;
            else if(active3_but.getID(viewPos.x,viewPos.y)>=0)choice=3;

            else if((viewPos.x<GRID_WIDTH) && (viewPos.x>0)
                    && (viewPos.y<GRID_HEIGHT) && (viewPos.y>0)){
                cells[(int)(viewPos.x/60)][(int)(viewPos.y/60)].update(setTexture(choice));
            }
        }
        inactive_but.draw(batch,x-CAM_WIDTH/2,y+(CAM_HEIGHT/2)-60);
        active1_but.draw(batch,x-(CAM_WIDTH/2)+60,y+(CAM_HEIGHT/2)-60);
        active2_but.draw(batch,x-CAM_WIDTH/2,y+(CAM_HEIGHT/2)-120);
        active3_but.draw(batch,x-(CAM_WIDTH/2)+60,y+(CAM_HEIGHT/2)-120);

        camera.position.set(x,y,0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void initButtons(){
        Texture inactive_icon_txt = new Texture("grid_inactive_icon.png");
        inactive_txt=new Texture("grid_inactive.png");
        active1_txt = new Texture("grid_active01.png");
        active2_txt = new Texture("grid_active02.png");
        active3_txt = new Texture("grid_active03.png");
        inactive_but=new Button(inactive_icon_txt,0);
        active1_but=new Button(active1_txt,1);
        active2_but=new Button(active2_txt,2);
        active3_but=new Button(active3_txt,3);
    }

    private Texture setTexture(int id){
        switch (id){
            case 0:return inactive_txt;
            case 1:return active1_txt;
            case 2:return active2_txt;
            case 3:return active3_txt;
        }
        return null;
    }
}
