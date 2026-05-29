package view;

import javax.swing.*;
import java.awt.*;

import static util.UIComponents.*;
import static util.UITheme.*;

public class MainMenuEmpleadoView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainMenuEmpleadoView() {

        setTitle("Empleado");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(header("MENU EMPLEADO"), BorderLayout.NORTH);
        add(menu(), BorderLayout.CENTER);
    }

    private JPanel menu() {

        JPanel p = new JPanel(new GridLayout(4,1,10,10));

        p.setBorder(BorderFactory.createEmptyBorder(40,100,40,100));

        p.add(button("Ver Productos", PRIMARY));
        p.add(button("Ver Clientes", PRIMARY));
        p.add(button("Ver Pedidos", PRIMARY));
        p.add(button("Salir", DANGER));

        return p;
    }
}