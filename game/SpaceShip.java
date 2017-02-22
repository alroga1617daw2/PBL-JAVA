package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    public abstract class SpaceShip extends Sprite {
    
    CollisionRect rect;
    Texture skin;
    Sprite sprite;

    public SpaceShip(){
        //skin = new Texture("night-raider.png");
        //sprite = new Sprite(skin);
        setPosition(348, 50);
        this.rect = new CollisionRect(this.getX(), this.getY(), 24, 24);
    }

    public void update(float deltaTime){
        rect.move(this.getX(), this.getY());
    }

    public void render(SpriteBatch batch){
        batch.draw(sprite,this.getX(),this.getY());
    }
    public CollisionRect getCollisionRect(){
        return rect;
    }
}