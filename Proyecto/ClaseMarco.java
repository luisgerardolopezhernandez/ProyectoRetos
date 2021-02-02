package Proyecto;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.awt.*;
import static java.awt.Color.*;


public class ClaseMarco extends JFrame implements ItemListener{
    boolean check;//Este campo no será private para que los Listeners (RetosApp y ClaseEtiqueta) accedan al objeto particular
    private final JMenuBar BARRA;
    private final JMenu MENU_1;
    private final JMenu MENU_2;
    private final JMenuItem[] ITEMS;
    private final JMenuItem CERRAR_TODO;
    private final ClaseBoton BOTON;
    private final ClaseEtiqueta SALUDO, TEXTO, PIE;
    private final JCheckBox CHECK_BOX;
    private final GridBagLayout LAYOUT;

    //CONSTRUCTOR
    public ClaseMarco(Especificaciones esp, String[] retos, int indiceReto){//indiceReto negativo indica que el frame es principal
        super(esp.getTitulo());

        //Componentes del marco:
        BARRA=new JMenuBar();
        MENU_1=new JMenu("Archivo");
        MENU_2=new JMenu("Navegar");
        ITEMS=new JMenuItem[retos.length];
        CERRAR_TODO=new JMenuItem("Cerrar todo",'c');
        BOTON=new ClaseBoton(esp.getIcono());
        SALUDO=new ClaseEtiqueta(esp.getFuente(), esp.getSaludo());
        TEXTO=new ClaseEtiqueta(esp.getFuente(), esp.getBienvenida(), retos, indiceReto);
        PIE=new ClaseEtiqueta(esp.getFuente(), esp.getInstrucciones(), (indiceReto<0)?-1:retos.length, indiceReto);
        CHECK_BOX=new JCheckBox("Nueva ventana");
        LAYOUT=new GridBagLayout();// manejador de diseño del JFrame

        //Agregar componentes:
        BARRA.add(MENU_1);
        BARRA.add(MENU_2);
        setJMenuBar(BARRA);
        agregarComponentesConGridBag();
        setLayout(LAYOUT);
        CHECK_BOX.addItemListener(this);// agregar ClaseMarco como receptor de CHECK_BOX

        //Detalles visuales:
        DetallesDeBarra();
        getContentPane().setBackground(DARK_GRAY);// this.setBackground(unColor) no funciona
        CHECK_BOX.setForeground(WHITE);
        CHECK_BOX.setBackground(DARK_GRAY);
        setIconImage(esp.getIcono().getImage());// icono del frame
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // no se cierra todo el programa si solo se cierra un JFrame
        setSize(esp.getAncho(), esp.getAlto());// dimensiones del JFrame (ClaseMarco)
        if(esp.getAncho()==0||esp.getAlto()==0) setSize(getPreferredSize());// (si ancho o altura son cero se elegirá el "Prefered Size")
        if(indiceReto<0)setLocationRelativeTo(null); // se centrara si el frame es el principal (indiceReto<0)
    }

    //METODOS PARA AGREGAR ELEMENTOS A LOS PANELES CON GRIDBAGLAYOUT
    private void agregarComponentesConGridBag(){
        GridBagConstraints c=new GridBagConstraints();
        c.weightx=2.0;
        c.weighty=2.0;
        c.insets=new Insets(0,27,15,0);
        add(BOTON,c);// este add() con estos parametros es un metodo auxiliar no incluido en java.awt.Component

        c.gridwidth=GridBagConstraints.RELATIVE;
        add(SALUDO,c);

        c.gridwidth=GridBagConstraints.REMAINDER;
        c.insets=new Insets(0,0,25,0);
        add(CHECK_BOX,c);

        c.gridwidth=GridBagConstraints.REMAINDER;
        c.insets=new Insets(0,0,0,0);
        add(TEXTO,c);
        add(PIE,c);
    }

    //DETALLES VISUALES DE LA BARRA DE HERRAMIENTAS
    private void DetallesDeBarra(){
        final Font FUENTE=new Font(Font.SANS_SERIF,Font.PLAIN,13);
        BARRA.setBackground(DARK_GRAY);
        BARRA.setForeground(WHITE);
        BARRA.setBorderPainted(false);

        MENU_1.setBackground(DARK_GRAY);
        MENU_1.setForeground(WHITE);
        MENU_1.setFont(FUENTE);
        MENU_1.add(CERRAR_TODO);
        MENU_1.setMnemonic('a');
        CERRAR_TODO.setBackground(DARK_GRAY);
        CERRAR_TODO.setForeground(WHITE);
        CERRAR_TODO.setFont(FUENTE);
        CERRAR_TODO.addActionListener(new RetosApp());

        MENU_2.setBackground(DARK_GRAY);
        MENU_2.setForeground(WHITE);
        MENU_2.setFont(FUENTE);
        MENU_2.setMnemonic('n');
        for(int i=0;i<ITEMS.length;i++) {
            char c =Integer.toString(i+1).charAt(0);
            ITEMS[i] = new JMenuItem("Reto número " + (i+1)+"",c);
            ITEMS[i].setBackground(DARK_GRAY);
            ITEMS[i].setForeground(WHITE);
            ITEMS[i].setFont(FUENTE);
            ITEMS[i].addActionListener(SALUDO);
            ITEMS[i].addItemListener(this);
            MENU_2.add(ITEMS[i]);
        }
    }

    //CAMBIO DE LA VARIABLE BOOLEANA SI CAMBIA EL ESTADO DEL CHECKBOX
    @Override
    public void itemStateChanged(ItemEvent i) { check=i.getStateChange()==ItemEvent.SELECTED;}

    //MÉTODO AUXILIAR PARA AGREGAR LOS COMPONENTES CON LAS RESTRICCIONES
    private void add(Component comp, GridBagConstraints c){ LAYOUT.setConstraints(add(comp), c); }/* el método que agrega componentes
                                                                                                  al JFrame tambien lo regresa*/
    //GETTERS DEL TEXTO, DEL PIE Y DEL BOTON
    ClaseEtiqueta getTexto() { return TEXTO; }
    ClaseEtiqueta getPie() {return PIE; }
    ClaseBoton getBoton(){return BOTON; }
}
