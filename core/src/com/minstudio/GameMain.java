package com.minstudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.minstudio.core.Constants;
import com.minstudio.core.Context;
import com.minstudio.core.GridRenderer;
import com.minstudio.core.Resources;
import com.minstudio.core.audio.AudioManager;
import com.minstudio.core.objects.TextureLoader;
import com.minstudio.core.yoshi.Yoshi;

public class GameMain extends ApplicationAdapter {
    private final GameInput gameInput;
    private SpriteBatch batch;
    private GridRenderer gridRenderer;
    private Yoshi yoshi;
    private Context context;
    private OrthographicCamera camera;

    private boolean gameOver = false;

    public GameMain(GameInput gameInput) {
        this.gameInput = gameInput;
    }


    @Override
    public void resize(int width, int height) {
//		Gdx.app.log("Resize",width + " " + height);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
//		camera.zoom = 0.25f;
        gridRenderer = new GridRenderer(batch, 0, 0, 6, 100, 64, 1f);
        TextureLoader.init();
        AudioManager.init();
        initialize();
    }

    private void initialize(){
        yoshi = new Yoshi(new Rectangle(128, 280, 32, 32));
        context = new Context(camera, yoshi, gameInput);
        camera.position.y = Constants.CAMERA_HEIGHT / 2;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);


//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        if (!gameOver) {
            camera.update();
            batch.setProjectionMatrix(camera.combined);
            batch.begin();

            gridRenderer.render();
            context.update();
            context.render(batch);

            batch.end();
        } else {
            //display game over message
            BitmapFont font = new BitmapFont();

            batch.begin();
            font.draw(batch, "Game Over", camera.position.x - 50, camera.position.y + 30);
            font.draw(batch, "Press \"SPACE\" to Play Again", camera.position.x - 100, camera.position.y - 30);
            batch.end();

            //await user input to restart
            if (gameInput.isSpaceDown()) {
                initialize();
                gameOver = false;
                return;
            }
        }
        gameOver = gameOver || context.isObjectOufOfScope(yoshi);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Resources.dispose();
        AudioManager.dispose();
    }
}
