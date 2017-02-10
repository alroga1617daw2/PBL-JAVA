package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorBala extends Actor {
    private final Texture bala;
    private final Sprite Balas;
    public int width, height;
    
    public ActorBala(){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        bala = new Texture("bullet.png");
        Balas = new Sprite(bala);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Balas, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
  
}
