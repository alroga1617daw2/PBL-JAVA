package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

    public class SpaceShip extends Sprite {

        CollisionRect rect;
    public SpaceShip(){
        super(new Texture("space-ship-24px.png"));
        setPosition(348, 50);
        this.rect = new CollisionRect(this.getX(), this.getY(), 24, 24);
    }

    public void update(float deltaTime){
        rect.move(this.getX(), this.getY());
    }
    public void remove(){}
    public CollisionRect getCollisionRect(){
        return rect;
    }
}