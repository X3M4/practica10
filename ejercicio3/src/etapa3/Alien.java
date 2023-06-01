package etapa3;


public class Alien extends Personaje
{
    protected int vx;
    
    public Alien(Escenario e){
        super(e);
        setSpriteNombre(new String[] {"extra0.gif", "extra1.gif", "jose_maria_fernandez.gif"});
        setFrameVelocidad(35);
    }
    
    public void actua(){
        super.actua();
        x += vx;
        if (x < 0 || x > Escenario.ANCHO - 40) vx = -vx;
    }
    
    public int getVx(){return vx;}
    public void setVx(int i){vx = i;}
    
    public void colision(Personaje a){
        if (a instanceof Misil || a instanceof Bomba) {
          borra();
          a.borra();
          genera();
        }
    }
    
    public void genera(){
        Alien a = new Alien(escena);
        a.setX((int)(Math.random() * Escenario.ANCHO));
        a.setY((int)(Math.random() * Escenario.LIMITE/2));
        int v = (int)(Math.random() * 20 - 10);
        a.setVx((v == 0) ? 1 : v);
        escena.insertaPersonaje(a);
    }
}
