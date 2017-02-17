package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Bullet {
    public static final int SPEED = 500;
    public static final int DEFAULT_Y = 60;
    public static Texture texture;

    float x, y;
    CollisionRect rect;
    public boolean remove = false;

    public Bullet(float x){
        this.x = x;
        this.y = DEFAULT_Y;

        this.rect = new CollisionRect(x, y, 9, 13);
        if(texture == null){
            texture = new Texture("bullet.png");
        }
    }

    public void update(float deltaTime) {
        y+= SPEED * deltaTime;
        if(y> Gdx.graphics.getHeight()){
            remove = true;
        }
        rect.move(x, y);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }
}