package etapa4;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class Personaje
{
    protected int x, y;
    protected int ancho, alto;
    protected String[] spriteNombres;
    protected int actualFrame;
    protected Escenario escena;
    protected SpriteCache sc;
    protected int frameVelocidad;
    protected int t;
    protected boolean marcadoParaBorrar;
    
    public Personaje(Escenario e){
        this.escena = e;
        sc = e.getSpriteCache();
        actualFrame = 0;
    }
    
    public void paint(Graphics2D g){
        g.drawImage(sc.getSprite(spriteNombres[actualFrame]), x, y, escena);
    }
    
    public int getX(){ return x;}
    public void setX(int i){x = i;}
    
    public int getY(){ return y;}
    public void setY(int i){y = i;}
    
    public void borra(){marcadoParaBorrar = true;}
    public boolean estaMarcadoParaBorrar(){return marcadoParaBorrar;}
    
    public String[] getSpriteNombres() {return spriteNombres;}
    public void setSpriteNombre(String[] s){
        spriteNombres = s;
        alto = 0;
        ancho = 0;
        for (int i = 0; i < s.length; i++){
            BufferedImage imagen = sc.getSprite(spriteNombres[i]);
            alto = Math.max(alto, imagen.getHeight());
            ancho = Math.max(ancho, imagen.getWidth());
        }
    }    
    
    public Rectangle getFronteras(){ return new Rectangle(x, y, ancho, alto);}
    public void colision(Personaje a){ }
    
    public int getAlto(){ return alto;}
    public int getAncho(){ return ancho;}
    public void setAlto(int i){alto = i;}
    public void setAncho(int i){ancho = i;}
    public void setFrameVelocidad(int i){frameVelocidad = i;}
    public void actua(){
        if (frameVelocidad == 0)
            return;
        t++;
        if (t % frameVelocidad == 0){
            t = 0;
            actualFrame = (actualFrame + 1) % spriteNombres.length;
        }
    }
}
