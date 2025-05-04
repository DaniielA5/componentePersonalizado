# 🎨 Proyecto “Componente Visual” en Java Swing

Este repositorio contiene un *componente visual personalizado* en Java Swing que permite cargar y mostrar imágenes con funcionalidad de zoom. Fue desarrollado como parte de una práctica académica en el Instituto Tecnológico de Oaxaca.

---

## 📽 Video tutorial

🔗 [Ver tutorial en YouTube](https://www.youtube.com/watch?v=TU-ENLACE-AQUI)

---

## 🧩 Breve explicación del componente

La clase Componente extiende JPanel e implementa Serializable. Sus características principales son:

- 📁 *Carga de imagen* desde una ruta de archivo.
- 🔍 *Zoom dinámico*: permite agrandar o reducir la imagen con los métodos zoomIn() y zoomOut().
- 🎯 *Centrado automático* de la imagen al redimensionar o aplicar zoom.
- 🎨 *Repaint automático* cada vez que se modifica la imagen o el zoom.

---

## 🛠 Métodos y propiedades más relevantes
```
java
// Clase Componente (proyecto_componente_visual/Componente.java)

private String rutaImagen;
private double zoom = 1.0;

/** Asigna la ruta de la imagen y repinta el componente */
public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
    repaint();
}

/** Aumenta el zoom en un 10% y repinta */
public void zoomIn() {
    zoom += 0.1;
    repaint();
}

/** Disminuye el zoom en un 10% (hasta un límite mínimo) y repinta */
public void zoomOut() {
    zoom = Math.max(0.1, zoom - 0.1);
    repaint();
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (rutaImagen != null && !rutaImagen.isEmpty()) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image img = icon.getImage();
        int w = (int)(img.getWidth(this) * zoom);
        int h = (int)(img.getHeight(this) * zoom);
        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;
        g.drawImage(img, x, y, w, h, this);
    }
}
```
En prueba_componente/Collage.java se inicializan cuatro instancias de Componente y se asocian a los botones de zoom correspondientes.

---

## 🖼 Capturas de pantalla
- ** Ventana de ingreso de rutas (Prueba)

- ** Ventana de collage con zoom (Collage)

---

## 👥 Créditos del equipo
- ** Juarez Ramirez Daniel Alexis
- ** Bautista Centeno Francisco Elias 

##  ✅ Estado del proyecto
- ** versiones actuales
- ** Funcionalidades implementadas: carga de imagen, zoom in/out, centrado automático.
