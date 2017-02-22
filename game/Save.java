package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class Save implements TextInputListener{
    String text;
    public Save(){
        Gdx.input.getTextInput(this, "Mode?", "","Modo cooperativo? si / no");
        Gdx.input.getTextInput(this, "Type?", "","Elige el tipo de nave: 1, 2 o 3");
        Gdx.input.getTextInput(this, "player name?", "","Escribe aqui tu nombre");
    }
    public void input(String text){
        this.text = text;
    }

    public String getValue(){
            return text;
    }
    
    public void canceled(){

    }
}