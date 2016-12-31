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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sk.zurek.FOF.Objects.Cell;

public class MapEditor implements Screen {
    //--------CONSTANTS-------------------------------------------------------------------------------------------------

    private static final int SPEED=100;
    private static final int CAM_WIDTH=800;
    private static final int CAM_HEIGHT=600;
    private static final int GRID_WIDTH=2400;
    private static final int GRID_HEIGHT=2400;
    private static final int CELL_SIZE=60;
    private static final int TEXTURE_NUMBER=5;
    private static final int PANEL_SIZE=100;


    //---------LIBGDX---------------------------------------------------------------------------------------------------

    private Stage stage;
    private Skin skin;
    private Table scrollTable;
    private TextButton[] buttons;
    private Texture[] textures;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont test_font;
    private Vector3 viewPos;

    //---------JAVA-----------------------------------------------------------------------------------------------------

    private Cell[][] cells;
    private float x=GRID_WIDTH/2,y=GRID_HEIGHT/2;
    private int choice;

    //------------------------------------------------------------------------------------------------------------------

    public MapEditor(){
        initPanel();
        initOthers();
        createMap();
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
        test_font.draw(batch,(int)viewPos.x+"/"+(int)viewPos.y,x-CAM_WIDTH/2, y-(CAM_HEIGHT/2)+10);
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
            if((viewPos.x<GRID_WIDTH) && (viewPos.x>0) && (viewPos.y<GRID_HEIGHT) && (viewPos.y>0)){
                    cells[(int)(viewPos.x/60)][(int)(viewPos.y/60)].update(textures[choice]);
            }
        }

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

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

    private void createMap(){
        cells = new Cell[GRID_WIDTH/CELL_SIZE][GRID_HEIGHT/CELL_SIZE];

        for(int i=0;i<GRID_WIDTH/CELL_SIZE;++i){
            for(int j=0;j<GRID_HEIGHT/CELL_SIZE;++j){
                cells[i][j]=new Cell(textures[0],i*CELL_SIZE,j*CELL_SIZE);
            }
        }
    }

    private void initTextures(){
        textures=new Texture[TEXTURE_NUMBER];
        buttons = new TextButton[TEXTURE_NUMBER];
        scrollTable = new Table();
        scrollTable.setBounds(0,0,PANEL_SIZE,PANEL_SIZE);

        addTexture("textures/grid_inactive.png","Delete",0);
        addTexture("textures/grid_active01.png","Red",1);
        addTexture("textures/grid_active02.png","Blue",2);
        addTexture("textures/grid_active03.png","Green",3);
        addTexture("textures/grid_active04.png","Yellow",4);
    }

    private void initPanel(){
        stage=new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Group background = new Group();
        background.setBounds(0, CAM_HEIGHT-PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);
        initTextures();

        ScrollPane scrollPane = new ScrollPane(scrollTable);
        scrollPane.setBounds(0, 0, PANEL_SIZE, PANEL_SIZE);
        scrollPane.setPosition(0,CAM_HEIGHT-PANEL_SIZE);
        scrollPane.setTransform(true);

        stage.addActor(background);
        stage.addActor(scrollPane);
        background.addActor(new Image(new Texture("textures/panel_background.png")));
        Gdx.input.setInputProcessor(stage);
    }

    private void initOthers(){
        test_font=new BitmapFont(Gdx.files.internal("fonts/test_fnt.fnt"));
        test_font.getData().setScale(0.5f);
        batch = new SpriteBatch();
        camera=new OrthographicCamera(CAM_WIDTH,CAM_HEIGHT);
        viewPos = new Vector3();
    }

    //add button with texture to panel
    private void addTexture(String textureName, String buttonName, final int number){
        textures[number] = new Texture(textureName);
        buttons[number]=new TextButton(buttonName, skin);
        scrollTable.add(buttons[number]).width(PANEL_SIZE);
        scrollTable.row();

        buttons[number].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {choice=number;}
        });
    }
}
