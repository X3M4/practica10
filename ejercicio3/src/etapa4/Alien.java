package etapa4;


public class Alien extends Personaje
{
    protected static final double FRECUENCIA_DISPARO = 0.01;
    protected int vx;
    
    public Alien(Escenario e){
        super(e);
        setSpriteNombre(new String[] {"extra0.gif", "extra1.gif", "jose_maria_fernandez.gif"});
        setFrameVelocidad(35);
    }
    
    public void actua(){
        super.actua();
        x += vx;
        if (x < 0 || x > Escenario.ANCHO - getAncho()) vx = -vx;
        if (Math.random() < FRECUENCIA_DISPARO) dispara();
    }
    
    public int getVx(){return vx;}
    public void setVx(int i){vx = i;}
    
    public void colision(Personaje a){
        if (a instanceof Misil || a instanceof Bomba) {
            escena.getJugador().sumaPuntos(20);
            escena.getSonidoCache().tocaSonido("explosion.wav");
            borra();
            a.borra();
            genera();
        }
    }
    
    public void genera(){
        Alien a = new Alien(escena);
        a.setX((int)(Math.random() * (Escenario.ANCHO - getAncho())));
        a.setY((int)(Math.random() * Escenario.LIMITE/2));
        int v = (int)(Math.random() * 20 - 10);
        a.setVx((v == 0) ? 1 : v);
        escena.insertaPersonaje(a);
    }
    
    public void dispara(){
        Laser m = new Laser(escena);
        m.setX(x + (getAncho() / 2));
        m.setY(y + getAlto());
        escena.insertaPersonaje(m);
        escena.getSonidoCache().tocaSonido("foton.wav");
    }
}
