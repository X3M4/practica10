package etapa4
;


 import java.net.URL;
 import java.applet.Applet;
 import java.applet.AudioClip;
 import java.io.File;
 
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;

public class SonidoCache extends RecursosCache
{
    protected Object cargaRecurso(URL url){
        try{
            return Applet.newAudioClip(url);
        } catch (Exception e){
            System.out.println("Error al cargar el sonido " + url);
            System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
            return null;
        }
    }
    
    public AudioClip getAudioCLip(String nombre){
        return (AudioClip)getRecurso(nombre);
    }
    
    public void tocaSonido(String nombre){
        new Thread(
            new Runnable(){
                public void run(){
                    getAudioCLip(nombre).play();
                }
            }
        ).start();
    }
    
    public void bucleSonido(String nombre){
        new Thread(
            new Runnable(){
                public void run(){
                    try{
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(getUrl(nombre)));
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    } catch(Exception e){}
                }
            }
        ).start();
        getAudioCLip(nombre).loop();
    }
}
