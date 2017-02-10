/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author alroga
 */
public class ActorFondo extends Actor{
    private Texture fondo;
    public int width, height;

   
   public ActorFondo(){
       fondo = new Texture("background.png");
   }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        batch.draw(fondo, 0, 0, width, height);
    }
   
   
}
