package com.germaniii.pong;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class Ball {
    Random rand = new Random();
    private float x, y;
    private float rad;
    private float speedX, speedY;

    Ball(float height){
        x = Gdx.graphics.getWidth()/2;
        y = Gdx.graphics.getHeight()/2;
        rad = height / 8;

        speedX = (float) (rand.nextInt(50) + 150);
        speedY = (float) (rand.nextInt(50) + 150) * (rand.nextInt(3) - 1);
    }

    public void reset(){
        x = Gdx.graphics.getWidth()/2;
        y = Gdx.graphics.getHeight()/2;
    }

    public void setSpeedX(float getSpeedX){
        speedX = getSpeedX;
    }

    public void setSpeedY(float getSpeedY){
        speedY = getSpeedY;
    }

    public void setX(float setX){
        x = setX;
    }

    public void setY(float setY){
        y = setY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRad() {
        return rad;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }
}
