
package nnuevocomponente;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.File;
import java.io.Serializable;
import javax.swing.*;

public class Componente01 extends JPanel implements Serializable {

    private File rutaImagen;
    private float zoom = 1.0f;
    private Color bordeColor = Color.BLACK;
    private int bordeGrosor = 2;
    private boolean mostrarBorde = true;
    private String textoSuperpuesto = "";
    private Color colorTexto = Color.WHITE;
    private Font fuenteTexto = new Font("Arial", Font.BOLD, 14);
    private boolean mostrarControlesZoom = true;
    private String alineacionTexto = "izquierda"; 
    private boolean imagenEscalada = true;
    private float transparenciaImagen = 1.0f;
    private boolean activarClickImagen = false;
    private String mensajeAlClick = "¡Has hecho clic!";
    private boolean fondoActivo = false;
    private Color colorFondoPersonalizado = Color.LIGHT_GRAY;
    private boolean mostrarNombreArchivo = false;

    private final JButton mas;
    private final JButton menos;

    public Componente01() {
        setLayout(null);
        mas = new JButton("+");
        mas.setBounds(10, 10, 50, 25);
        menos = new JButton("-");
        menos.setBounds(70, 10, 50, 25);
        add(mas);
        add(menos);

        mas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoom += 0.1f;
                repaint();
            }
        });

        menos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zoom > 0.2f) {
                    zoom -= 0.1f;
                    repaint();
                }
            }
        });

        updateZoomControls();
    }

    public String getAlineacionTexto() {
        return alineacionTexto;
    }

    public void setAlineacionTexto(String alineacionTexto) {    
        this.alineacionTexto = alineacionTexto;
        repaint();
    }

    public boolean isImagenEscalada() {    
        return imagenEscalada;
    }

    public void setImagenEscalada(boolean imagenEscalada) {    
        this.imagenEscalada = imagenEscalada;    
        repaint();
    }

    public float getTransparenciaImagen() {
        return transparenciaImagen;
    }

    public void setTransparenciaImagen(float transparenciaImagen) {
        this.transparenciaImagen = transparenciaImagen;
        repaint();
    }
    
    public boolean isActivarClickImagen() {
        return activarClickImagen;
    }

    public void setActivarClickImagen(boolean activarClickImagen) {
        this.activarClickImagen = activarClickImagen;
    }

    public String getMensajeAlClick() {
        return mensajeAlClick;
    }
 
    public void setMensajeAlClick(String mensajeAlClick) {
        this.mensajeAlClick = mensajeAlClick;
    }

    public boolean isFondoActivo() {
        return fondoActivo;
    }

    public void setFondoActivo(boolean fondoActivo) {
        this.fondoActivo = fondoActivo;
        repaint();
    }

    public Color getColorFondoPersonalizado() {
        return colorFondoPersonalizado;
    }

    public void setColorFondoPersonalizado(Color colorFondoPersonalizado) {
        this.colorFondoPersonalizado = colorFondoPersonalizado;
        repaint();
    }

    public boolean isMostrarNombreArchivo() {
        return mostrarNombreArchivo;
    }

    public void setMostrarNombreArchivo(boolean mostrarNombreArchivo) {
        this.mostrarNombreArchivo = mostrarNombreArchivo;
        repaint();
    }


    public File getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(File rutaImagen) {
        this.rutaImagen = rutaImagen;
        repaint();
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
        repaint();
    }

    public Color getBordeColor() {
        return bordeColor;
    }

    public void setBordeColor(Color bordeColor) {
        this.bordeColor = bordeColor;
        repaint();
    }

    public int getBordeGrosor() {
        return bordeGrosor;
    }

    public void setBordeGrosor(int bordeGrosor) {
        this.bordeGrosor = bordeGrosor;
        repaint();
    }

    public boolean isMostrarBorde() {
        return mostrarBorde;
    }

    public void setMostrarBorde(boolean mostrarBorde) {
        this.mostrarBorde = mostrarBorde;
        repaint();
    }

    public String getTextoSuperpuesto() {
        return textoSuperpuesto;
    }

    public void setTextoSuperpuesto(String textoSuperpuesto) {
        this.textoSuperpuesto = textoSuperpuesto;
        repaint();
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
        repaint();
    }

    public Font getFuenteTexto() {
        return fuenteTexto;
    }

    public void setFuenteTexto(Font fuenteTexto) {
        this.fuenteTexto = fuenteTexto;
        repaint();
    }

    public boolean isMostrarControlesZoom() {
        return mostrarControlesZoom;
    }

    public void setMostrarControlesZoom(boolean mostrarControlesZoom) {
        this.mostrarControlesZoom = mostrarControlesZoom;
        updateZoomControls();
        repaint();
    }

    private void updateZoomControls() {
        mas.setVisible(mostrarControlesZoom);
        menos.setVisible(mostrarControlesZoom);
    }

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    
        if (fondoActivo) {        
            g.setColor(colorFondoPersonalizado);        
            g.fillRect(0, 0, getWidth(), getHeight());
        }    
        if (rutaImagen != null && rutaImagen.exists()) {        
            ImageIcon imagenIcon = new ImageIcon(rutaImagen.getAbsolutePath());        
            Image imagen = imagenIcon.getImage();
            int ancho = imagenEscalada ? (int) (getWidth() * zoom) : imagen.getWidth(null);
            int alto = imagenEscalada ? (int) (getHeight() * zoom) : imagen.getHeight(null);
            int x = 0;
            int y = 0;
            if (!imagenEscalada) {
                x = (getWidth() - ancho) / 2;
                y = (getHeight() - alto) / 2;
            }        
            Graphics g2 = g.create();
            ((Graphics) g2).drawImage(imagen, x, y, ancho, alto, this);
            g2.dispose();        
            if (!textoSuperpuesto.isEmpty() || mostrarNombreArchivo) {
                String texto = mostrarNombreArchivo && rutaImagen != null ? rutaImagen.getName() : textoSuperpuesto;
                g.setFont(fuenteTexto);
                g.setColor(colorTexto);
                int textX = switch (alineacionTexto) {
                    case "centro" -> (getWidth() - g.getFontMetrics().stringWidth(texto)) / 2;
                    case "derecha" -> getWidth() - g.getFontMetrics().stringWidth(texto) - 10;
                    default -> 10;
                };
                g.drawString(texto, textX, getHeight() - 10);
            }
            if (mostrarBorde) {
                g.setColor(bordeColor);
                for (int i = 0; i < bordeGrosor; i++) {
                    g.drawRect(i, i, getWidth() - i * 2 - 1, getHeight() - i * 2 - 1);
                }
            }
        }
    }
}