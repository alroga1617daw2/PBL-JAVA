package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Alien extends Sprite{

    private static Texture alienTexture;
    CollisionRect rect;
    private static int height;
    private float x, y;
    public static int n = 5;
    public static ArrayList<Alien> aliens = new ArrayList<Alien>();

    public Alien(){
        this.x = getX();
        this.y = getY();
        alienTexture = new Texture("Space_Invaders_Alien20.png");
        this.rect = new CollisionRect(this.getX(), this.getY(), 20, 20);
    }

    public void update(float deltaTime){
        rect.move(this.getX(), this.getY());
    }

    public CollisionRect getCollisionRect(){
        return rect;
    }
    public void render(SpriteBatch batch){
        batch.draw(alienTexture,this.getX(),this.getY());
    }
    public static void generarAliens(){
        height = Gdx.graphics.getHeight();
        alienTexture = new Texture("Space_Invaders_Alien20.png");
        int numy1=height-30, numy2=height-120, numx1=0, numx2=620;

        for(int i=0; i < n;i++) { //5 aliens en el arrayList
            aliens.add(new Alien());
        }

        for (Sprite a : Alien.aliens) { //indicamos la posicion inicial de cada alien
            int numAleatorioy=(int)Math.floor(Math.random()*(numy1-(numy2+1))+(numy2));
            int numAleatoriox=(int)Math.floor(Math.random()*(numx1-(numx2+1))+(numx2));
            a.setPosition(numAleatoriox, numAleatorioy);
        }
        n += 1;
    }


}