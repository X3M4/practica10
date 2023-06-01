package etapa3;

import java.awt.image.ImageObserver;


public interface Escenario extends ImageObserver
{
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    public static final int RETARDO = 20;
    public static final int LIMITE = 400;
    
    public SpriteCache getSpriteCache();
    
    public void insertaPersonaje(Personaje p);
}
