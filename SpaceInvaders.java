package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class SpaceInvaders extends ApplicationAdapter {
    
    /*----- Variables -----*/
    private SpriteBatch batch;
    public int height, width;
    public int x = 348;
    private final int velocidad = 250;

    private Texture fondo, nave;
    //private Sprite Enemy;
    private SpaceShip spaceship;
    private Enemy Enemy;
    private boolean disparo;
    
    private ArrayList<Bullet> bullets;
    /*----- End Variables -----*/ 
    
    /*------------------------------------------------------------*/

    @Override
    public void create () {
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

        bullets = new ArrayList<Bullet>();
        fondo = new Texture("background.png");
        nave = new Texture("space-ship-24px.png");

        spaceship = new SpaceShip();
        Enemy = new Enemy();
        //Enemy = new Sprite(nave);
        //spaceship.setPosition(348, 50);
        Enemy.setPosition(348, 300);
        System.out.println(x);
    }

    @Override // Render se llama constantemente
    public void render () {
        batch.begin();
        batch.draw(fondo, 0, 0, width,height);
        batch.draw(spaceship, spaceship.getX(), spaceship.getY());
        batch.draw(Enemy, Enemy.getX(), Enemy.getY());
        
        for(Bullet bullet : bullets){
            bullet.render(batch);
        }
        
        Controles();
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
    
    private void Controles() {
        
        boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        disparo = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        float delta = Gdx.graphics.getDeltaTime();
       
        if(derecha && !izquierda){
            if((spaceship.getX() + spaceship.getWidth()) < 720){
                x += velocidad * delta;
                spaceship.setX(x);
            }
        }

        else if(izquierda && !derecha){
            if(spaceship.getX() > 0){
                x -= velocidad * delta;
                spaceship.setX(x);
            }
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();        
        if(disparo){
            bullets.add(new Bullet(spaceship.getX()));
            bullets.add(new Bullet(spaceship.getX()+ spaceship.getWidth() - 8));
        }
        spaceship.update(delta);
        for(Bullet bullet : bullets){
            bullet.update(delta);
            if(bullet.remove){
                bulletsToRemove.add(bullet);
            }
        }
        for(Bullet bullet : bullets){             
                               
            if(bullet.getCollisionRect().colidesWith(Enemy.getCollisionRect())){
                bulletsToRemove.add(bullet);
                
                //Enemy.setPosition(60, 45);
            }
        }
        bullets.removeAll(bulletsToRemove);


   }
}