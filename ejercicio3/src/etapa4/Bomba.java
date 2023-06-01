package etapa4;


public class Bomba extends Personaje
{
    protected static final int SUBE_IZQ = 0;
    protected static final int SUBE = 1;
    protected static final int SUBE_DER = 2;
    protected static final int IZQ = 3;
    protected static final int DER = 4;
    protected static final int BAJA_IZQ = 5;
    protected static final int BAJA = 6;
    protected static final int BAJA_DER = 7;
    
    protected static final int VELOCIDAD_BOMBA = 5;
    
    protected int vx;
    protected int vy;
    
    public Bomba(Escenario e, int direccion, int x, int y){
        super(e);
        this.x = x;
        this.y = y;
        String sprite = "";
        switch(direccion){
            case SUBE_IZQ:
                vx = -VELOCIDAD_BOMBA;
                vy = -VELOCIDAD_BOMBA;
                sprite = "bombaSI.gif";
                break;
            case SUBE:
                vx = 0;
                vy = -VELOCIDAD_BOMBA;
                sprite = "bombaS.gif";
                break;
            case SUBE_DER:
                vx = VELOCIDAD_BOMBA;
                vy = -VELOCIDAD_BOMBA;
                sprite = "bombaSD.gif";
                break;
            case IZQ:
                vx = -VELOCIDAD_BOMBA;
                vy = 0;
                sprite = "bombaI.gif";
                break;
            case DER:
                vx = VELOCIDAD_BOMBA;
                vy = 0;
                sprite = "bombaD.gif";
                break;
            case BAJA_IZQ:
                vx = -VELOCIDAD_BOMBA;
                vy = VELOCIDAD_BOMBA;
                sprite = "bombaBI.gif";
                break;
            case BAJA:
                vx = 0;
                vy = VELOCIDAD_BOMBA;
                sprite = "bombaB.gif";
                break;
            case BAJA_DER:
                vx = VELOCIDAD_BOMBA;
                vy = VELOCIDAD_BOMBA;
                sprite = "bombaBD.gif";
                break;
        }
        setSpriteNombre(new String[] {sprite});
    }
    
    public void actua(){
        super.actua();
        x += vx;
        y += vy;
        if (y < 0 || y > Escenario.ALTO || x < 0 || x > Escenario.ANCHO) 
            borra();
    }
}
