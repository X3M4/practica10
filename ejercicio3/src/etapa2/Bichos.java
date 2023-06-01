 package etapa2;

 import javax.swing.JFrame;
 import javax.swing.JPanel;
 import java.awt.*;
 import java.awt.event.*;
 
 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.net.URL;
 
 import java.awt.image.BufferStrategy;
 
 import java.util.HashMap;

public class Bichos extends Canvas{
    public static final int ANCHO = 800;
    public static final int ALTO = 600;
    public static final int RETARDO = 20;
    public HashMap sprites;
    //public BufferedImage buffer;
    private int posX = ANCHO / 2;
    private int posY = ALTO / 2;
    private int vX = 1;
    public BufferStrategy strategy;
    private int fps;

    public Bichos(){
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
        
        sprites = new HashMap();
        //buffer = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
        
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        requestFocus();
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
            long tiempo = System.currentTimeMillis();
            actualizaMundo();
            pintaMundo();
            //paint(getGraphics());
            tiempo = System.currentTimeMillis() - tiempo;
            if (tiempo != 0) fps = (int)(1000L/tiempo); else fps = -1;
            try{
                Thread.sleep(RETARDO);
            } catch(InterruptedException e){}
        }
    }
    
    public void pintaMundo(){
        Graphics g = strategy.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(getSprite("res/extra.gif"), posX, posY, this);
        
        if (fps >= 0){
            g.setColor(Color.green);
            g.drawString(fps + " fps", 5, ALTO - 50);
        } else {
            g.setColor(Color.white);
            g.drawString("--- fps", 5, ALTO - 50);
        }
        
        strategy.show();
    }

    /*public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, this);
    }*/

    public static void main(String[] args) {
        Bichos b = new Bichos();
        b.juego();
    }
}