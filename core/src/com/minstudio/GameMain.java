package com.minstudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.minstudio.core.Context;
import com.minstudio.core.GridRenderer;
import com.minstudio.core.Resources;
import com.minstudio.core.objects.TextureLoader;
import com.minstudio.core.yoshi.Logger;
import com.minstudio.core.yoshi.Yoshi;

public class GameMain extends ApplicationAdapter {
	private SpriteBatch batch;
	private GridRenderer gridRenderer;
	private final GameInput gameInput;
	private Yoshi yoshi;
	private Context context;

	public static final int CAMERA_WIDTH = 256 + 128;
	public static final int CAMERA_HEIGHT = 512;
	private OrthographicCamera camera;

	private boolean gameOver = false;

	public GameMain(GameInput gameInput) {
		this.gameInput = gameInput;
	}


	@Override
	public void resize (int width, int height) {
//		Gdx.app.log("Resize",width + " " + height);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
//		camera.zoom = 0.25f;
		yoshi = new Yoshi(new Rectangle(128, 280, 32, 32));
		context = new Context(camera, yoshi, gameInput);
		gridRenderer = new GridRenderer(batch, 0, 0, 6, 100, 64, 1f);
		TextureLoader.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(!gameOver){
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
			if(gameInput.isSpaceDown()){
				create();
				gameOver = false;
				return;
			}
		}
		gameOver = gameOver || isGameOver();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Resources.dispose();
	}

	private boolean isGameOver(){
		//if yoshi falls too much
		if(this.yoshi.getPosition().y < this.camera.position.y - CAMERA_HEIGHT / 2 - 40f){
			Logger.info(this, "Game Over! " + yoshi.getPosition() + " Camera at: " + this.camera.position);
			return true;
		}
		return false;
	}
}
