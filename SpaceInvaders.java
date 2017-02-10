package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class SpaceInvaders extends ApplicationAdapter {
    
    /*----- Variables -----*/
    private SpriteBatch batch;
    public int height, width;
    private final int velocidad = 150;
    private final int velocidadBala = 1500;

    private boolean disparo;
    
    private Stage escenario;
    private Actor Minave, fondo, Balas;
    /*----- End Variables -----*/ 
    
    /*------------------------------------------------------------*/
    //Se crea el escenario y se añaden los actores
    private void Escenario() {
        escenario = new Stage();
        
        fondo = new ActorFondo();
        escenario.addActor(fondo);
        
        Minave = new ActorNave();
        Minave.setPosition((width/2) - 12,50);
        escenario.addActor(Minave);
        
        Balas = new ActorBala();
        escenario.addActor(Balas);
       
    }
    
    private void Controles() {
        
        boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        disparo = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        float delta = Gdx.graphics.getDeltaTime();
        
        float y = Minave.getY();
        float x = Minave.getX();
        float yB = Balas.getY();
       
        if(derecha && !izquierda){
            Minave.addAction(Actions.moveBy(velocidad * delta, 0));
        }
        else if(izquierda && !derecha){
            Minave.addAction(Actions.moveBy(-(velocidad * delta), 0));
        }
        
        if(disparo){
            Balas.setPosition((x-20),y);
            Balas.addAction(Actions.moveBy(0, 500,1));
        }
        //yB = yB + velocidadBala * delta;
        //Balas.setY(yB);
        
    }
    
    @Override
    public void create () {
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        Escenario();          
    }

    @Override // Render se llama constantemente
    public void render () {
        escenario.act();
        escenario.draw();
        Controles();
    }

    @Override
    public void dispose () {
        batch.dispose();
        //img.dispose();
    }
    
}