package com.germaniii.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	Ball ball;
	Bar bar1, bar2;
	float bar1_y, bar1_speed;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		ball = new Ball();
		bar1 = new Bar(1);	// player 1
		bar2 = new Bar(2);	// player 2
	}

	@Override
	public void render () {
		// Background Initialization
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Title Card
		font.setColor(1,1,1,1);
		font.getData().setScale(3);

		// Input Handling
		// Keyboard
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			incrementY();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			decrementY();
		}
		//Touch
		if(Gdx.input.isTouched()){
			if(Gdx.input.getY() > Gdx.graphics.getHeight()/2)
				decrementY();
			else
				incrementY();
		}


		// Bounds Setting
		if (bar1.getY() > Gdx.graphics.getHeight() - 100)
			bar1.setY(Gdx.graphics.getHeight() - 100);
		if (bar1.getY() < 0)
			bar1.setY(0);
		if (bar2.getY() > Gdx.graphics.getHeight() - 100)
			bar2.setY(Gdx.graphics.getHeight() - 100);
		if (bar2.getY() < 0)
			bar2.setY(0);






		batch.begin();
		font.draw(batch, "PONG", Gdx.graphics.getWidth()/2 - 60, Gdx.graphics.getHeight() * 3/4);
		batch.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(bar1.getX(), bar1.getY(), bar1.getWidth(), bar1.getHeight());
		shapeRenderer.rect(bar2.getX(), bar2.getY(), bar2.getWidth(), bar2.getHeight());
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
	}

	private void incrementY(){
		bar1_y = bar1.getY();
		bar1_speed = bar1.getSpeedY();
		bar1_y += bar1.getSpeedY() * Gdx.graphics.getDeltaTime();
		bar1.setY(bar1_y);
	}

	private void decrementY(){
		bar1_y = bar1.getY();
		bar1_speed = bar1.getSpeedY();
		bar1_y -= bar1.getSpeedY() * Gdx.graphics.getDeltaTime();
		bar1.setY(bar1_y);
	}


}
