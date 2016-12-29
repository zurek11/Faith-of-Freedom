package sk.zurek.FOF.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sk.zurek.FOF.Objects.Grid;

public class MapEditor implements Screen {
    private static final int SPEED=100;
    private static final int GRID_WIDTH=2400;
    private static final int GRID_HEIGHT=2400;
    private static final int GRID_SIZE=60;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private float x=GRID_WIDTH/2,y=GRID_HEIGHT/2;
    private Grid[][] grids;

    public MapEditor(){
        batch = new SpriteBatch();
        camera=new OrthographicCamera(800,600);
        grids = new Grid[GRID_WIDTH / GRID_SIZE][GRID_HEIGHT / GRID_SIZE];
        for(int i=0;i<GRID_WIDTH/GRID_SIZE;++i){
            for(int j=0;j<GRID_HEIGHT/GRID_SIZE;++j){
                grids[i][j]=new Grid(i*GRID_SIZE,j*GRID_SIZE);
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
        batch.begin();

        for(int i=0;i<GRID_WIDTH/GRID_SIZE;++i){
            for(int j=0;j<GRID_HEIGHT/GRID_SIZE;++j){
                grids[i][j].render(batch);
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
            if((Gdx.input.getX()<GRID_WIDTH) && (Gdx.input.getX()>0)
                    && (Gdx.input.getY()<GRID_HEIGHT) && (Gdx.input.getY()>0)){
                grids[Gdx.input.getX()/60][Gdx.input.getY()/60].change=true;
            }
        }
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
}
