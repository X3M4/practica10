package etapa4;

 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.net.URL;
 import java.util.HashMap;
 import java.io.File;

public abstract class RecursosCache
{
    public HashMap recursos;
    
    public RecursosCache(){
        recursos = new HashMap();
    }
    
    public Object cargaRecurso(String nombre){
        URL url = null;
        url = getClass().getResource("res/" + nombre);
        return cargaRecurso(url);
    }
    
    public Object getRecurso(String nombre){
        Object res = recursos.get(nombre);
        if (res == null){
            res = cargaRecurso(nombre);
            recursos.put(nombre, res);
        }
        return res;
    }
    
    public URL getUrl(String nombre){
        return getClass().getResource("res/" + nombre);
    }
    
    protected abstract Object cargaRecurso(URL url);
}
