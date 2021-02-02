package Proyecto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetosApp implements ActionListener {
	//ESPECIFICACIONES Y RETOS COMO OBJETOS ESTATICOS:
	private final static String[] RETOS={"¿Cuando vas a tener mejores hábitos?",
			"¿Cuando haras verdadero ejercicio?",
			"¿Cuando aprenderas inglés?",
			"¿Cuando empezarás a ser valiente?",
            "¿Cuando dejarás la mentira?",
            "¿Cuando vas a empezar a ser pacifico?",
            "¿Cuando seras un gran matemático?",
            "¿Cuando expresarás genuinamente tus sentimientos?"};
	private final static Especificaciones ESP=new Especificaciones("Retos","¡HOLA ALI!",
			"¡Preparate para un encuentro contigo mismo!","\"Pulse el ícono para iniciar\"",
			"AmericaLatina", "recursos/inicio.png", 660, 234);

	public static void main (String[] args){
		//Constructor del JFrame
		ClaseMarco marcoPrincipal= new ClaseMarco(ESP, RETOS, -1); //El marco principal iniciará con una bienvenida
		//Construido el marco, se hace visible
		marcoPrincipal.setVisible(true);

		//Si el usuario crea otro frame (marco) actionPerformed se encargará de ello
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		//Auxiliares para la obtención del estado del frame anterior:
		ClaseMarco tmp= (ClaseMarco) ((JComponent) a.getSource()).getTopLevelAncestor();
		if(a.getSource() instanceof ClaseBoton){//Si es el boton
			if(tmp.check){//Si la casilla check está marcada y si la fuente es el boton
				//Se construye otro marco
				ClaseMarco nuevoMarco = new ClaseMarco(ESP, RETOS, tmp.getTexto().getI());//getI() (indice del pie) se usa para crear otro frame desde ese punto
				//Construido el marco, tambien se hace visible
				nuevoMarco.setVisible(true);
			}
		}
		else if(a.getSource() instanceof JMenuItem){//Si la fuente es el item
			System.exit(0);// cierra todas las ventanas
		}
	}
}
