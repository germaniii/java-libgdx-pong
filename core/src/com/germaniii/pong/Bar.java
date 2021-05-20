package com.germaniii.pong;

import com.badlogic.gdx.Gdx;

public class Bar {
    private float x, y;
    private float height, width;
    private float speedY;

    Bar(int i){
        if(i == 1){  // if 1 = player 1, left side
            x = Gdx.graphics.getWidth() * 1/8;
            y = Gdx.graphics.getHeight() * 2/5;
        }else{  // else  = player 2, right side
            x = Gdx.graphics.getWidth() * 7/8;
            y = Gdx.graphics.getHeight()* 2/5;
        }

        height = Gdx.graphics.getHeight()/6;
        width = 20;
        speedY = 200;
    }

    public void reset(){
        y = Gdx.graphics.getHeight() * 2/5;
    }

    public void increment(){
        y += speedY * Gdx.graphics.getDeltaTime();
    }

    public void decrement(){
        y -= speedY * Gdx.graphics.getDeltaTime();
    }

    public void setY(float getY){
        y = getY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getSpeedY() {
        return speedY;
    }
}
