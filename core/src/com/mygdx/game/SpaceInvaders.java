package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.mygdx.game.Alien;

public class SpaceInvaders extends ApplicationAdapter {
	private SpriteBatch batch;
        //private BitmapFont font; //Mostrar caracteres por pantalla
	private int height, width;
        
        private Texture fondo;
        private Texture bala;
        private Texture nave;
        
        private Sprite Minave;
        private Sprite Balas;
        private int velocidad = 150;
        private int velocidadBala = 1500;
	
        private boolean disparo;

        
	@Override
	public void create () {
		batch = new SpriteBatch();
                //font = new BitmapFont();
                /* OBTENER ALTURA/ANCHO DE LA VENTANA*/
                height = Gdx.graphics.getHeight();
                width = Gdx.graphics.getWidth();
                /* END ALTURA/ANCHO */
                /* TEXTURAS */
                fondo = new Texture("background.png");
                nave = new Texture("space-ship-24px.png");
                bala = new Texture("bullet.png");
                /* END TEXTURAS */
                Minave = new Sprite(nave);
                Balas = new Sprite(bala);
                Minave.setPosition(width/2, 50);
	}

	@Override
	public void render () {
            RenderizarJuego();
            Controles();   
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}

    private void Controles() {
        boolean derecha = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean izquierda = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        float delta = Gdx.graphics.getDeltaTime();
        disparo = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        
        float y = Minave.getY();
        float x = Minave.getX();
        float yB = Balas.getY();
        
            if(derecha && !izquierda){
                if(x <= width - 29){
                    x = x + velocidad * delta;
                } 
            } 
            else if(izquierda && !derecha){
                if(x != 0){
                    x = x - velocidad * delta;            
                }
            }
                    
            if(disparo){
                Balas.setPosition(x-20, 60);
                yB = y;
                System.out.println("Disparo!!");
            }
        
        yB = yB + velocidadBala * delta;
        Balas.setY(yB);
        Minave.setX(x);
        //System.out.println(x);    
    }
    private void RenderizarJuego() {
        batch.begin();
        batch.draw(fondo, 0, 0, width, height);
        Minave.draw(batch);
        Balas.draw(batch);
        batch.end();
    }
}
