package com.mygdx.game;


import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Timer;

public class SpaceInvaders extends ApplicationAdapter {
    
    /*----- Variables -----*/
    private SpriteBatch batch;
    public int height, width;
    public int score, lives;
    public int x = 348;
    private final int velocidad = 300;

    private Texture fondo;
    private SpaceShip spaceship;
    private boolean disparo;
    
    private ArrayList<Bullet> bullets;
    private ArrayList<Alien> aliensToRemove;

    BitmapFont scoreFont, vidas;
    /*----- End Variables -----*/ 
    
    /*--------------------------------------------------------------------------------------------------------*/

    @Override
    public void create () {
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        score = 0;
        lives = 10;
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        vidas = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        spawn();
        bullets = new ArrayList<Bullet>();
        aliensToRemove = new ArrayList<Alien>();
        fondo = new Texture("background.png");
        spaceship = new SpaceShip();
    }

    @Override // Render se llama constantemente
    public void render () {
        batch.begin();
        batch.draw(fondo, 0, 0, width,height);
        if(lives > 0){
            batch.draw(spaceship, spaceship.getX(), spaceship.getY());
        
            GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "score:" + score);
            GlyphLayout vidasLayout = new GlyphLayout(vidas, "vidas:" + lives);

            scoreFont.getData().setScale(0.5f);
            vidas.getData().setScale(0.5f);

            scoreFont.draw(batch, scoreLayout, (width - 85) - (scoreLayout.width/2), height - 20);
            scoreFont.draw(batch, vidasLayout, (85) - (vidasLayout.width/2), height - 20);
        
            for(Bullet bullet : bullets){
                bullet.render(batch);
            }
            for (Alien a : Alien.aliens) {
                a.render(batch);
            }
    
            Colisiones();
            Controles();
            GenerarAliens();
        } else {

            batch.draw(spaceship, spaceship.getX(), spaceship.getY());
            GlyphLayout gameOver = new GlyphLayout(scoreFont, "GAME OVER");

            scoreFont.draw(batch, gameOver, (width / 2) - (gameOver.width/2), height/2 - (gameOver.height /2));
        }
        
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
    
    /*--------------------------------------------------------------------------------------------------------*/

    private void Controles() {
        
        boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        disparo = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Buttons.LEFT);
        float delta = Gdx.graphics.getDeltaTime();
       
        if(derecha && !izquierda){
            if( (spaceship.getX() + spaceship.getWidth()) < 720){
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

        /* DISPAROS */
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        
        if(disparo){
            bullets.add(new Bullet(spaceship.getX() + 6));
            //bullets.add(new Bullet(spaceship.getX()+ spaceship.getWidth() - 8));
        }

        spaceship.update(delta);
        for(Bullet bullet : bullets){
            bullet.update(delta);
            if(bullet.remove){
                bulletsToRemove.add(bullet);
            }
        }

        
        
   }
   /* COLISIONES */
   public void Colisiones(){
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();

        for(Bullet bullet : bullets){             
            for(Alien a : Alien.aliens){
                if(bullet.getCollisionRect().colidesWith(a.getCollisionRect())){
                    bulletsToRemove.add(bullet);
                    aliensToRemove.add(a);
                    System.out.println("Impacto");
                    score += 100;
                }
            }
        }

        bullets.removeAll(bulletsToRemove);
        Alien.aliens.removeAll(aliensToRemove);
   }
   public void GenerarAliens() {
        float delta = Gdx.graphics.getDeltaTime();

        for (Alien a : Alien.aliens) {

            a.setPosition(a.getX(), a.getY());
            float x = a.getX(), y = a.getY();
            a.update(delta);

            if (y > 0) {
                y -= 1f;
                a.setPosition(x, y);
                a.rect.move(x, y);
            } else {
                System.out.println("Perder vidas");
                aliensToRemove.add(a);
                lives -= 1;
            }
        }
    }
    
    public void spawn(){
        Timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                Alien.generarAliens(); // generamos los aliens cada 12 segundos
            }
        },1,8);
    }

}