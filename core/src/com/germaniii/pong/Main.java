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

import java.util.Random;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	Ball ball;
	Bar bar1, bar2;
	float bar1_y, bar1_speed;
	float ball_x, ball_y;

	boolean is_running;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		is_running = false;

		bar1 = new Bar(1);	// player 1
		bar2 = new Bar(2);	// player 2
		ball = new Ball(bar1.getHeight());
		ball_x = ball.getX();
		ball_y = ball.getY();
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
			bar1_incrementY();
			is_running = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			bar1_decrementY();
			is_running = true;
		}
		//Touch
		if(Gdx.input.isTouched()){
			//Player 1
			if(Gdx.input.getY() > Gdx.graphics.getHeight()/2 && Gdx.input.getX() < Gdx.graphics.getWidth() / 2)
				bar1_decrementY();
			if(Gdx.input.getY() < Gdx.graphics.getHeight()/2 && Gdx.input.getX() < Gdx.graphics.getWidth() / 2)
				bar1_incrementY();
			is_running = true;
		}

		if(is_running) {
			// Bounds Setting
			if (bar1.getY() > Gdx.graphics.getHeight() - bar1.getHeight())
				bar1.setY(Gdx.graphics.getHeight() - bar1.getHeight());
			if (bar1.getY() < 0)
				bar1.setY(0);
			if (bar2.getY() > Gdx.graphics.getHeight() - bar2.getHeight())
				bar2.setY(Gdx.graphics.getHeight() - bar2.getHeight());
			if (bar2.getY() < 0)
				bar2.setY(0);

			// Ball Logic
			ball_x += ball.getSpeedX() * Gdx.graphics.getDeltaTime();
			ball_y += ball.getSpeedY() * Gdx.graphics.getDeltaTime();
			ball.setX(ball_x);
			ball.setY(ball_y);

			if (ball.getY() > Gdx.graphics.getHeight() - ball.getRad())
				ball.setSpeedY(ball.getSpeedY() * -1);
			if (ball.getY() < 0)
				ball.setSpeedY(ball.getSpeedY() * -1);

			if (ball.getX() > Gdx.graphics.getWidth() - ball.getRad()) {
				ball.setX(Gdx.graphics.getWidth()/2);
				ball.setY(Gdx.graphics.getHeight()/2);
				bar1.setY(Gdx.graphics.getHeight() * 2/5);
				bar2.setY(Gdx.graphics.getHeight() * 2/5);
				is_running = false;
			}
			if (ball.getX() < 0) {
				ball.setX(Gdx.graphics.getWidth()/2);
				ball.setY(Gdx.graphics.getHeight()/2);
				bar1.setY(Gdx.graphics.getHeight() * 2/5);
				bar2.setY(Gdx.graphics.getHeight() * 2/5);
				is_running = false;
			}
		}

		batch.begin();
		font.draw(batch, "PONG", Gdx.graphics.getWidth()/2 - 60, Gdx.graphics.getHeight() * 7/8);
		batch.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRad());
		shapeRenderer.rect(bar1.getX(), bar1.getY(), bar1.getWidth(), bar1.getHeight());
		shapeRenderer.rect(bar2.getX(), bar2.getY(), bar2.getWidth(), bar2.getHeight());
		shapeRenderer.end();
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
	}

	private void bar1_incrementY(){
		bar1_y = bar1.getY();
		bar1_speed = bar1.getSpeedY();
		bar1_y += bar1.getSpeedY() * Gdx.graphics.getDeltaTime();
		bar1.setY(bar1_y);
	}

	private void bar1_decrementY(){
		bar1_y = bar1.getY();
		bar1_speed = bar1.getSpeedY();
		bar1_y -= bar1.getSpeedY() * Gdx.graphics.getDeltaTime();
		bar1.setY(bar1_y);
	}


}
