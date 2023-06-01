package etapa4;



public class Laser extends Personaje
{
    protected static final int VELOCIDAD_LASER = 3;
    
    public Laser(Escenario e){
        super(e);
        setSpriteNombre(new String[] {"disparo0.gif", "disparo1.gif", "disparo2.gif"});
        setFrameVelocidad(10);
    }
    
    public void actua(){
        super.actua();
        y += VELOCIDAD_LASER;
        if (y > Escenario.LIMITE - getAlto()) borra();
    }
}
