package com.minstudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.minstudio.core.Context;
import com.minstudio.core.GridRenderer;
import com.minstudio.core.Resources;
import com.minstudio.core.objects.TextureLoader;
import com.minstudio.core.yoshi.Yoshi;

public class GameMain extends ApplicationAdapter {
	private SpriteBatch batch;
	private GridRenderer gridRenderer;
	private final GameInput gameInput;
	private Yoshi yoshi;
	private Context context;

	private OrthographicCamera camera;

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
		camera.setToOrtho(false, 256 + 128, 512);
//		camera.zoom = 0.25f;
		yoshi = new Yoshi(new Rectangle(128, 280, 32, 32));
		context = new Context(yoshi, gameInput);
		gridRenderer = new GridRenderer(batch, 0, 0, 6, 100, 64, 1f);
		TextureLoader.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		gridRenderer.render();
		context.update();
		context.render(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Resources.dispose();
	}
}
