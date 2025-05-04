
package proyecto_componente_visual;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Componente extends JPanel implements Serializable {
    
    private File rutaImagen;
    private float zoom = 1.0f;
    private final JButton mas;
    private final JButton menos;
    public Componente(){
        setLayout(null);
        mas = new JButton("+");
        mas.setBounds(10, 10, 50, 25);
        add(mas);
        menos = new JButton("-");
        menos.setBounds(70, 10, 50, 25);
        add(menos);
        mas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                zoom+=0.1f;
                repaint();
            }
        });
        menos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(zoom>0.2f){
                   zoom-=0.1f;
                   repaint();
                }
            }
        });
    }
    
    public File getRutaImagen() {
        return rutaImagen;
    }
    public void setRutaImagen(File rutaImagen) {
        this.rutaImagen = rutaImagen;
        repaint();
    } 
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(rutaImagen!=null && rutaImagen.exists()){
            ImageIcon imagenIcon = new ImageIcon(rutaImagen.getAbsolutePath());
            Image imagen = imagenIcon.getImage();
            int ancho = (int) (getWidth() * zoom);
            int alto = (int) (getHeight() * zoom);
            g.drawImage(imagen, 0, 0, ancho, alto, this);
        }
        
    }   
}
