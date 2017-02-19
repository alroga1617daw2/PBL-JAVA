package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Timer;


public class Enemy extends ApplicationAdapter {

    //arraylist aliens, foreach(dibuixar aliens)
    private SpriteBatch batch;
    private Texture background;
    private int height, width, x, y;


    @Override
    public void create() {
        batch = new SpriteBatch();
        // TEXTURAS
        background = new Texture("background.png");
        //FIN TEXTURAS
spawn();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        // SPRITES INICIO
        //SPRITES FIN
    }

    @Override
    public void render() {
        renderizarJuego();
        procesarEntrada();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }

    public void renderizarJuego() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, width, height);

        //dibujar todo el ArrayList que contiene los Aliens
        for (Sprite a : Alien.aliens) {
            batch.draw(a, a.getX(), a.getY(), 20, 20);
        }
        batch.end();
    }

    public void procesarEntrada() {

        boolean abajo = Gdx.input.isKeyPressed(Input.Keys.DOWN);


        for (Sprite a : Alien.aliens) {
            float x = a.getX(), y = a.getY();

            if (y > 5) {
                y -= 0.5f;
                a.setPosition(x, y);
                System.out.println(a.getY());
            } else {
                System.out.println("Perder vidas");
            }
        }
    }

    public void spawn(){
        Timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                Alien.generarAliens(); // generamos los aliens cada 12 segundos

            }

        },1,15);
    }

}
