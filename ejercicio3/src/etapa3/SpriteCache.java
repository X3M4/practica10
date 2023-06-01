package etapa3;

 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.net.URL;
 import java.util.HashMap;
 import java.io.File;

public class SpriteCache
{
    public HashMap sprites;
    
    public SpriteCache(){
        sprites = new HashMap();
    }
    
    public BufferedImage cargaImagen(String nombre){
        URL url = null;
        File f = null;
        try{
            url = getClass().getResource("res/" + nombre);
            return ImageIO.read(url);
        } catch(Exception e){
            System.out.println("Error al cargar imagen " + nombre + " de " + url);
            System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
            try{
                url = getClass().getClassLoader().getResource("etapa3/res/");
                return ImageIO.read(new File(url.getPath() + nombre));
            } catch(Exception e1){
                System.out.println("Error al cargar imagen del ficheto " + nombre + " de " + url);
                System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
                return null;
            }
        }
    }
    
    public BufferedImage getSprite(String nombre){
        BufferedImage img = (BufferedImage) sprites.get(nombre);
        if (img == null){
            img = cargaImagen(nombre);
            sprites.put(nombre, img);
        }
        return img;
    }
}
