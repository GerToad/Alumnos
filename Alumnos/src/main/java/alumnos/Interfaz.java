package alumnos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interfaz extends JFrame implements ActionListener{
    private Label title;
    private Button alumnos, editar, borrar, cerrar;
    private JTable tab;
    private JScrollPane sp;

    public Interfaz() {
        title = new Label("Bienvenido");
        title.setBounds(250,30,150,30);
        title.setFont(new Font("Consolas", 1, 13));
        add(title);

        // Table
        String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE", "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT", "Kundan Kumar Jha", "4031", "CSE" }
        };
        String[] names = { "Nombre", "Apellido", "D1", "D2", "D3", "Promedio" };
        tab = new JTable(data, names);
        tab.setBounds(55, 120, 490, 300);
        add(tab);

        //sp = new JScrollPane(tab);
        //add(sp);

	    // Buttons
	    alumnos = new Button("Registar alumno");
	    alumnos.setBounds(55, 70, 150, 30);
	    add(alumnos);
	    alumnos.addActionListener(this);

	    editar = new Button("Editar alumno");
	    editar.setBounds(225, 70, 150, 30);
	    add(editar);
	    editar.addActionListener(this);

	    borrar = new Button("Borrar alumno");
	    borrar.setBounds(395, 70, 150, 30);
	    add(borrar);
	    borrar.addActionListener(this);

	    cerrar = new Button("Cerrar");
	    cerrar.setBounds(250, 510, 100, 30);
	    add(cerrar);
	    cerrar.addActionListener(this);

	    // General settings
	    setTitle("Alumnos");
	    setLayout(null);
	    setSize(600, 600);
	    setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cerrar){
            System.exit(0);
        }
        if(e.getSource() == alumnos){
            System.out.println("Studenten");
            String title = "Registar alumno";
            Registros app = new Registros(title);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setResizable(false);
            app.setLocationRelativeTo(null);
        }
        if(e.getSource() == editar){
            System.out.println("Edit");
            String title = "Editar alumno";
            Registros app = new Registros(title);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setResizable(false);
            app.setLocationRelativeTo(null);
        }
        if(e.getSource() == borrar){
            System.out.println("Delete");
        }

    }
}
