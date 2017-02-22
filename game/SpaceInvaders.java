package com.mygdx.game;

import java.util.ArrayList;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Timer;

public class SpaceInvaders extends Game {

    /*----- Variables -----*/
    public SpriteBatch batch;
    public int height, width;
    public int score = 0;
    public int lives = 10;
    public int round = 0;
    public int x = 348;
    public int x2 = 348;
    public String player, type, type2;
    private final int velocidad = 300;

    //private String playerName;
    private Texture fondo;
    private SpaceShip spaceship, spaceship2;
    private boolean disparo, disparo2;
    private boolean seleccion = false;
    private boolean ready = false;
    private boolean coopSelect = false;
    private String coop;

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Alien> aliensToRemove = new ArrayList<Alien>();

    BitmapFont scoreFont, vidas, rounds;
    Save saver;
    /*----- End Variables -----*/

    /*--------------------------------------------------------------------------------------------------------*/

    @Override
    public void create() {
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        vidas = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        rounds = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        saver = new Save();
        spawn();
        type = new String();
        fondo = new Texture("background.png");

    }

    @Override // Render se llama constantemente
    public void render() {
        UserInput();
        batch.begin();
        batch.draw(fondo, 0, 0, width, height);

        if (seleccion) {
            if (lives > 0) {
                spaceship.render(batch);
                if (coop.equalsIgnoreCase("si")) {
                    spaceship2.render(batch);
                }

                for (Bullet bullet : bullets) {
                    bullet.render(batch);
                }
                for (Alien a : Alien.aliens) {
                    a.render(batch);
                }
                UI();
                Colisiones();
                Controles();
                GenerarAliens();
            } else {

                spaceship.render(batch);
                if (coop.equalsIgnoreCase("si")) {
                    spaceship2.render(batch);
                }
                GlyphLayout gameOver = new GlyphLayout(scoreFont, "GAME OVER");

                scoreFont.draw(batch, gameOver, (width / 2) - (gameOver.width / 2), height / 2 - (gameOver.height / 2));
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    /*--------------------------------------------------------------------------------------------------------*/
    private void UI() {
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "score:" + score);
        GlyphLayout roundLayout = new GlyphLayout(rounds, "round:" + round);
        GlyphLayout vidasLayout = new GlyphLayout(vidas, "lives:" + lives);

        scoreFont.getData().setScale(0.45f);
        rounds.getData().setScale(0.45f);
        vidas.getData().setScale(0.45f);

        scoreFont.draw(batch, scoreLayout, (width - 85) - (scoreLayout.width / 2), height - 20);
        scoreFont.draw(batch, roundLayout, (width / 2) - (roundLayout.width / 2), height - 20);
        scoreFont.draw(batch, vidasLayout, (85) - (vidasLayout.width / 2), height - 20);
    }

    private void UserInput() {

        try {
            if (player == null) {
                player = saver.getValue();
            }

            if (player != null) {
                type = saver.getValue();
                if(type.equalsIgnoreCase("1")){
                    type2 = "1";
                } else if(type.equalsIgnoreCase("2")){
                    type2 = "2";
                } else if(type.equalsIgnoreCase("3")){
                    type2 = "3";
                }
            }

            if (type != null) {
                coop = saver.getValue();
            }
            if (coop != null) {
                if (coop.equalsIgnoreCase("si")) {
                    coopSelect = true;
                    ready = true;
                } else if (coop.equalsIgnoreCase("no")) {
                    coopSelect = false;
                    ready = true;
                }
            }

            if (seleccion == false && ready == true) {
                if (coopSelect == true) {
                    if (type2.equalsIgnoreCase("1")) {
                        spaceship = new SpaceShip1();
                        spaceship2 = new SpaceShip2();
                        seleccion = true;
                    } else if (type2.equalsIgnoreCase("2")) {
                        spaceship = new SpaceShip2();
                        spaceship2 = new SpaceShip3();
                        seleccion = true;
                    } else if (type2.equalsIgnoreCase("3")) {
                        spaceship = new SpaceShip3();
                        spaceship2 = new SpaceShip1();
                        seleccion = true;
                    }
                } else {
                    if (type2.equalsIgnoreCase("1")) {
                        spaceship = new SpaceShip1();
                        seleccion = true;
                    } else if (type2.equalsIgnoreCase("2")) {
                        spaceship = new SpaceShip2();
                        seleccion = true;
                    } else if (type2.equalsIgnoreCase("3")) {
                        spaceship = new SpaceShip3();
                        seleccion = true;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }

    }

    private void Controles() {

        boolean derecha = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean derecha2 = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean izquierda2 = Gdx.input.isKeyPressed(Input.Keys.LEFT);

        disparo = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        disparo2 = Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0);
        float delta = Gdx.graphics.getDeltaTime();

        if (derecha && !izquierda) {
            if ((spaceship.getX() + spaceship.getWidth()) < 720) {
                x += velocidad * delta;
                spaceship.setX(x);
            }
        }

        else if (izquierda && !derecha) {
            if (spaceship.getX() > 0) {
                x -= velocidad * delta;
                spaceship.setX(x);
            }
        }

        if (coop.equalsIgnoreCase("si")) {
            if (derecha2 && !izquierda2) {
                if ((spaceship2.getX() + spaceship2.getWidth()) < 720) {
                    x2 += velocidad * delta;
                    spaceship2.setX(x2);
                }
            }

            else if (izquierda2 && !derecha2) {
                if (spaceship2.getX() > 0) {
                    x2 -= velocidad * delta;
                    spaceship2.setX(x2);
                }
            }
        }

        /* DISPAROS */
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();

        if (disparo) {
            bullets.add(new Bullet(spaceship.getX() + 6));
            //bullets.add(new Bullet(spaceship.getX()+ spaceship.getWidth() - 8));
        }
        if (coop.equalsIgnoreCase("si")) {
            if (disparo2) {
                bullets.add(new Bullet(spaceship2.getX() + 6));
                //bullets.add(new Bullet(spaceship.getX()+ spaceship.getWidth() - 8));
            }
            spaceship2.update(delta);
        }
        spaceship.update(delta);

        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }

    }

    /* COLISIONES */
    public void Colisiones() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();

        for (Bullet bullet : bullets) {
            for (Alien a : Alien.aliens) {
                if (bullet.getCollisionRect().colidesWith(a.getCollisionRect())) {
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
                if(coopSelect == true){
                    y -= 1.5f;
                }else {
                    y -= 1f;
                }
                a.setPosition(x, y);
                a.rect.move(x, y);
            } else {
                System.out.println("Vida -1");
                aliensToRemove.add(a);
                lives -= 1;
            }
        }
    }

    public void spawn() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Alien.generarAliens(); // generamos los aliens cada 12 segundos
                round += 1;
            }

        }, 1, 8);
    }

}