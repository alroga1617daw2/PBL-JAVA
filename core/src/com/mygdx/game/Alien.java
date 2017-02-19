package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Alien {

    private static Texture alienTexture;
    private static int height, width, x, y;

    public static ArrayList<Sprite> aliens = new ArrayList<Sprite>();

    public Alien(int x , int y){
        this.x = x;
        this.y = y;
    }

    public static void create() {
        SpriteBatch batch = new SpriteBatch();
        // TEXTURAS
        alienTexture = new Texture("Space_Invaders_Alien20.png");

        //FIN TEXTURAS

        // SPRITES INICIO
        Sprite alienSprite = new Sprite(alienTexture, width, height - 10);
        // Enemy.add(new Sprite(Alien,20,0));
        //SPRITES FIN
    }

    public static void generarAliens(){
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        alienTexture = new Texture("Space_Invaders_Alien20.png");
        int x=0;

        for(int i=0; i<5;i++) { //20 aliens en el arrayList
            aliens.add(new Sprite(alienTexture));
        }


        for (Sprite a : Alien.aliens) { //indicamos la posicion inicial de cada alien
            a.setPosition(x,height-30);
            x += 30;
        }

    }
}
                                                                       //////