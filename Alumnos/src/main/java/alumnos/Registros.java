package alumnos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Registros extends JFrame implements ActionListener{
    private Label title, nombre, apellidoPaterno, calificaciones, d1, d2, d3;
    private TextField op1, op2, op3, op4, op5;
    private Button registro, cerrar;

    public Registros(String titulo) {

        // Labels
        title = new Label(titulo);
        title.setBounds(250,30,150,30);
        title.setFont(new Font("Consolas", 1, 13));
        add(title);

        nombre = new Label("Nombre");
        nombre.setBounds(50,70,150,30);
        nombre.setFont(new Font("Consolas", 1, 13));
        add(nombre);

        apellidoPaterno = new Label("Apellido paterno");
        apellidoPaterno.setBounds(50,110,150,30);
        apellidoPaterno.setFont(new Font("Consolas", 1, 13));
        add(apellidoPaterno);

        calificaciones = new Label("Calificaciones");
        calificaciones.setBounds(250,190,150,30);
        calificaciones.setFont(new Font("Consolas", 1, 13));
        add(calificaciones);

        d1 = new Label("D1;");
        d1.setBounds(75,230,25,30);
        add(d1);

        d2 = new Label("D2:");
        d2.setBounds(230,230,25,30);
        add(d2);

        d3 = new Label("D3:");
        d3.setBounds(385,230,25,30);
        add(d3);

        // Text fields
        op1 = new TextField();
        op1.setBounds(200,70,200,30);
        add(op1);

        op2 = new TextField();
        op2.setBounds(200,110,200,30);
        add(op2);

        op3 = new TextField();
        op3.setBounds(120,230,50,30);
        add(op3);

        op4 = new TextField();
        op4.setBounds(275,230,50,30);
        add(op4);

        op5 = new TextField();
        op5.setBounds(430,230,50,30);
        add(op5);

        // Buttons
        cerrar = new Button("Cerrar");
        cerrar.setBounds(350, 310, 100, 30);
        add(cerrar);
        cerrar.addActionListener(this);

        registro = new Button("Enviar");
        registro.setBounds(150, 310, 100, 30);
        add(registro);
        registro.addActionListener(this);

        // General settings
        setTitle("Registro");
        setLayout(null);
        setSize(600, 380);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cerrar){
            System.exit(0);
        }
    }
}
