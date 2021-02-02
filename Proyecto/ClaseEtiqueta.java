package Proyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//LA CLASE CONSTRUIRA LOS JLABELS "SALUDO", "PIE", Y "TEXTO" DE LOS OBJETOS CLASEMARCO
public class ClaseEtiqueta extends JLabel implements ActionListener{
    private int i;
    static String[] retos;// lo inicializará el constructor de "texto" y lo usará actionPerformed

    //CONSTRUCTOR PARA EL SALUDO
    ClaseEtiqueta(String fuente, String saludo){// este saludo será el mismo para cada JFrame creado
        setText(saludo);
        setFont(new Font(fuente,Font.BOLD,48));
        setForeground(Color.WHITE);
    }

    //CONSTRUCTOR PARA EL PIE Y LAS INSTRUCCIONES
    ClaseEtiqueta(String fuente, String instrucciones, int retosLength, int indiceReto){
        setForeground(Color.WHITE);
        setFont(new Font(fuente,Font.ITALIC,24)); // la fuente y el color son los mismos en ambos casos

        if(retosLength>=0) {// si es un marco no principal:
            i = indiceReto;// indice de este objeto pie es incializado desde RetosApp
            setText("(este es tu reto #" + (iInc()+1) + ")");// se aplica el texto elegido en indiceReto
        }
        else
            setText(instrucciones); // si no, solo se aplica el campo instricciones
    }

    //CONSTRUCTOR PARA LOS TEXTOS
    ClaseEtiqueta(String fuente, String bienvenida, String[] r, int indiceReto){
        setForeground(Color.GREEN);
        setFont(new Font(fuente,Font.BOLD,24));// la fuente y el color son los mismos en ambos casos
        retos=r;// guardo retos en esta clase, pues lo utilizará el actionPerformed

        if(indiceReto>=0) { // si no es un marco principal:
            i = indiceReto;// indice de este objeto texto es incializado desde RetosApp
            setText(retos[iInc()]);// se aplica el texto elegido en indiceReto
        }
        else
            setText(bienvenida.toUpperCase());// si no, solo se aplica el campo bienvenida
    }

    //GETTER Y SETTER DEL ÍNDICE DEL TEXTO
    private int iInc(){
        if(i==retos.length) i=0;
        return i++;
    }
    int getI(){return i;}
    private void setI(int i){this.i=i;}

    //METODO QUE SOLO ROTA TEXTOS SI EL CHECK ESTÁ DESMARCADO
    @Override
    public void actionPerformed(ActionEvent a) {
        ClaseMarco tmp;
        JComponent src= (JComponent) a.getSource();
        if(src instanceof JMenuItem){
            tmp=(ClaseMarco) ((JMenuItem) src).getItemListeners()[0];
            tmp.getPie().setI( ((JMenuItem) src).getMnemonic()-49 );
            tmp.getTexto().setI( ((JMenuItem) src).getMnemonic()-49 ); //'0'==49
        }
        else
            tmp=(ClaseMarco) src.getTopLevelAncestor();
        final ClaseEtiqueta pie=tmp.getPie();
        final ClaseEtiqueta texto=tmp.getTexto();
        final ClaseBoton boton=tmp.getBoton();
        if(!tmp.check) {
            pie.setText("(este es tu reto #" + (pie.iInc() + 1) + ")");
            texto.setText(retos[texto.iInc()]);
        }
        else
            new RetosApp().actionPerformed(new ActionEvent(boton,ActionEvent.ACTION_PERFORMED,null));/*Activación manual del manejador
                                                                                                                de eventos que crea ventana*/
    }
}
