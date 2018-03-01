package com.minstudio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.minstudio.core.yoshi.Yoshi;
import com.minstudio.core.yoshi.YoshiAnimation;
import com.minstudio.core.yoshi.YoshiState;

public class GameMain extends ApplicationAdapter {
	private SpriteBatch batch;
	private final GameInput gameInput;
	private float elapsedTime;
	private Vector2 yoshiPos;
	private Yoshi yoshi;
	private Texture yoshiTexture;
	private YoshiAnimation yoshiAnimationLeft;
	private YoshiAnimation yoshiAnimationRight;

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
		yoshiTexture = new Texture("sprites/yoshi.png");
		yoshiAnimationLeft = new YoshiAnimation(yoshiTexture, true);
		yoshiAnimationRight = new YoshiAnimation(yoshiTexture, false);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
		camera.zoom = 0.25f;
		yoshiPos = new Vector2(400, 300);
		yoshi = new Yoshi(new Rectangle(0, 0, 48, 48));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		if(gameInput.isLeft()){
			updateYoshiState(Yoshi.State.RUN, false);
			elapsedTime += Gdx.graphics.getDeltaTime();
			yoshiPos.x -= Gdx.graphics.getDeltaTime() * 75;
			yoshiAnimationLeft.draw(batch, yoshiPos, YoshiAnimation.AnimationType.RUN, elapsedTime);
		} else if(gameInput.isRight()){
			updateYoshiState(Yoshi.State.RUN, true);
			elapsedTime += Gdx.graphics.getDeltaTime();
			yoshiPos.x += Gdx.graphics.getDeltaTime() * 75;
			yoshiAnimationRight.draw(batch, yoshiPos, YoshiAnimation.AnimationType.RUN, elapsedTime);
		} else {
			YoshiAnimation animation = yoshi.isFacingRight()? yoshiAnimationRight : yoshiAnimationLeft;
			animation.draw(batch, yoshiPos, YoshiAnimation.AnimationType.NORMAL, elapsedTime);
		}


		batch.end();
	}

	private void updateYoshiState(Yoshi.State state, boolean facingRight){
		if(state != yoshi.getCurrentState() || facingRight != yoshi.isFacingRight()){
			elapsedTime = 0;
			yoshi.setCurrentState(state);
			yoshi.setFacingRight(facingRight);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		yoshiTexture.dispose();
	}
}
