package etapa1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

import java.util.HashMap;

public class Bichos extends Canvas{
   public static final int ANCHO = 800;
   public static final int ALTO = 600;
   public static final int RETARDO = 20;
   public HashMap sprites;
   private BufferedImage extra = null;
   private int posX = ANCHO / 2;
   private int posY = ALTO / 2;
   private int vX = 1;

   public Bichos(){
       sprites = new HashMap();
       JFrame ventana = new JFrame("Bichos - Jose Maria Fernandez");
       ventana.setResizable(false);

       JPanel panel = (JPanel)ventana.getContentPane();
       panel.setBounds(0,0, ANCHO, ALTO);
       ventana.setBounds(0,0, ANCHO, ALTO);
       panel.setPreferredSize(new Dimension(ANCHO, ALTO));
       panel.setLayout(new BorderLayout()); 
       panel.add(this, BorderLayout.CENTER); 
       ventana.setVisible(true);

       ventana.addWindowListener(
           new WindowAdapter() { 
               public void windowClosing(WindowEvent e) {
                   System.exit(0);
                }
            }
       );
       
   }
   
   public BufferedImage cargaImagen(String nombre){
       URL url = null;
       try{
           url = getClass().getResource(nombre);
           return ImageIO.read(url);
       } catch(Exception e){
           System.out.println("Error al cargar imagen " + nombre + " de " + url);
           System.out.println("ERROR: " + e.getClass().getName() + " " + e.getMessage());
           return null;
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
   
   public void actualizaMundo(){
       //posX = (int)(Math.random() * ANCHO);
       //posY = (int)(Math.random() * ALTO);
       posX += vX;
       if (posX < 0 || posX > ANCHO - 40) vX = -vX;
   }
   
   public void juego(){
       while(isVisible()){
           actualizaMundo();
           paint(getGraphics());
           try{
               Thread.sleep(RETARDO);
           } catch(InterruptedException e){}
       }
   }

   public void paint(Graphics g) {
       g.setColor(getBackground());
       g.fillRect(0, 0, getWidth(), getHeight());
       g.drawImage(getSprite("res/extra.gif"), posX, posY, this);
   }

   public static void main(String[] args) {
       Bichos b = new Bichos();
       b.juego();
   }
}
