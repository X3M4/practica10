package etapa4;

import java.awt.event.KeyEvent;


public class Jugador extends Personaje
{
    protected static final int VELOCIDAD_JUGADOR = 4;
    protected static final int MAX_VIDA = 200;
    protected int vx;
    protected int vy;
    private boolean sub, baj, izq, der;
    private int reservaBombas;
    private int puntos;
    private int vida;
    
    public Jugador(Escenario e){
        super(e);
        setSpriteNombre(new String[] {"nave.gif"});
        reservaBombas = 5;
        vida = MAX_VIDA;
        puntos = 0;
    }
    
    public void actua(){
        super.actua();
        x += vx;
        y += vy;
        if (x < 0) x = 0;
        if (x > Escenario.ANCHO - getAncho()) x = Escenario.ANCHO - getAncho();
        if (y < 0) y = 0;
        if (y > Escenario.LIMITE - getAlto()) y = Escenario.LIMITE - getAlto();
    }
    
    protected void actualizaVelocidad(){
        vx = 0; vy = 0;
        if (baj) vy = VELOCIDAD_JUGADOR;
        if (sub) vy = -VELOCIDAD_JUGADOR;
        if (izq) vx = -VELOCIDAD_JUGADOR;
        if (der) vx = VELOCIDAD_JUGADOR;
    }
    
    public void disparar(){
        Misil m = new Misil(escena);
        m.setX(x + (ancho / 2) - (m.getAncho() / 2));
        m.setY(y - m.getAlto());
        escena.insertaPersonaje(m);
        escena.getSonidoCache().tocaSonido("misil.wav");
    }
    
    public void disparaBombas(){
        if (reservaBombas == 0)
            return;
        reservaBombas--;
        escena.insertaPersonaje(new Bomba(escena, Bomba.SUBE_IZQ, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.SUBE, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.SUBE_DER, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.IZQ, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.DER, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.BAJA_IZQ, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.BAJA, x, y));
        escena.insertaPersonaje(new Bomba(escena, Bomba.BAJA_DER, x, y));
    }
    
    public void colision(Personaje p){
        if (p instanceof Alien) {
            p.borra();
            sumaPuntos(40);
            vida -= 40;
        }
        if (p instanceof Laser) {
            p.borra();
            vida -= 10;
        }
        if (vida <= 0)
            escena.setGameOver();
    }
    
    public void sumaPuntos(int cantidad){
        puntos += cantidad;
    }
    
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: sub = true; break;
            case KeyEvent.VK_LEFT: izq = true; break;
            case KeyEvent.VK_RIGHT: der = true; break;
            case KeyEvent.VK_DOWN: baj = true; break;
            case KeyEvent.VK_SPACE: disparar(); break;
            case KeyEvent.VK_B: disparaBombas(); break;
        }
        actualizaVelocidad();
    }
    
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: sub = false; break;
            case KeyEvent.VK_LEFT: izq = false; break;
            case KeyEvent.VK_RIGHT: der = false; break;
            case KeyEvent.VK_DOWN: baj = false; break;
        }
        actualizaVelocidad();
    }
    
    public int getVx(){return vx;}
    public void setVx(int i){vx = i;}
    public int getVy(){return vy;}
    public void setVy(int i){vy = i;}
    public int getReservaBombas(){return reservaBombas;}
    public int getVida(){return vida;}
    public void setVida(int i){vida = i;}
    public int getPuntos(){return puntos;}
    public void setPuntos(int i){puntos = i;}
}
