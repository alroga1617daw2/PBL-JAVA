package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorNave extends Actor {
    private Texture nave;
    private Sprite Minave;
    public int width, height;
    
    //Constructor
    public ActorNave(){
        nave = new Texture("space-ship-24px.png");
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        Minave = new Sprite(nave);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Minave, getX(),getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
  
}
