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
        Sprite alienSprite = new Sprite(alienTexture, 20, 20);
        // Enemy.add(new Sprite(Alien,20,0));
        //SPRITES FIN
    }

    public static void generarAliens(){
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        alienTexture = new Texture("Space_Invaders_Alien20.png");
        int x=-30 ,numy1=height-30, numy2=height-120, numx1=0, numx2=620;

        for(int i=0; i<5;i++) { //20 aliens en el arrayList
            aliens.add(new Sprite(alienTexture));
        }


        for (Sprite a : Alien.aliens) { //indicamos la posicion inicial de cada alien

            int numAleatorioy=(int)Math.floor(Math.random()*(numy1-(numy2+1))+(numy2));
            int numAleatoriox=(int)Math.floor(Math.random()*(numx1-(numx2+1))+(numx2));
            a.setPosition(numAleatoriox, numAleatorioy);

        }

    }
}