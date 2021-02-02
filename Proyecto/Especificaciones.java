package Proyecto;
import javax.swing.ImageIcon;
public class Especificaciones {
    final private String TITULO;
    final private String SALUDO;
    final private String BIENVENIDA;
    final private String INSRUCCIONES;
    final private String FUENTE;
    final private ImageIcon ICONO;
    final private int ANCHO;
    final private int ALTO;
    public Especificaciones(String titulo, String saludo, String bienvenida, String instrucciones,
                            String fuente, String rutaImagen, int ancho, int alto){
     TITULO =titulo;
     SALUDO =saludo;
     BIENVENIDA=bienvenida;
     INSRUCCIONES=instrucciones;
     FUENTE =fuente;
     ICONO=new ImageIcon(rutaImagen);
     ANCHO=ancho;
     ALTO=alto;
    }
    public String getTitulo() { return TITULO; }
    public String getSaludo() { return SALUDO; }
    public String getBienvenida(){return BIENVENIDA;}
    public String getInstrucciones(){return INSRUCCIONES; }
    public String getFuente() { return FUENTE; }
    public ImageIcon getIcono() { return ICONO; }
    public int getAncho(){return ANCHO;}
    public int getAlto(){return ALTO;}
}
