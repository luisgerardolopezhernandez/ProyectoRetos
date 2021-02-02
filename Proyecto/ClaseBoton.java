package Proyecto;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
public class ClaseBoton extends JButton{
    ClaseBoton(ImageIcon icono){
        super();
        addActionListener(new ClaseEtiqueta(null,null));// agregar ClaseEtiqueta como receptor de BOTON
        addActionListener(new RetosApp());// agregar Retos como receptor de BOTON
        setIcon(resizeIcon(icono,80,80));
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(80,80));
        setBorderPainted(false);
    }

    //METODO PARA REDIMENSION DE LA IMAGEN
    private ImageIcon resizeIcon(ImageIcon img, int w, int h){
        Image image=img.getImage();
        Image newImage=image.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        return new ImageIcon(newImage);
    }
}
