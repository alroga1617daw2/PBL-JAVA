package com.mygdx.game;

public class CollisionRect {
    
    float x,y;
    int width, height;

    public CollisionRect(float x, float y , int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move (float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean colidesWith(CollisionRect rect){
        return this.x < rect.x + rect.width && this.y < rect.y + rect.height && this.x + width > rect.x && this.y + height > rect.y;
    }
}