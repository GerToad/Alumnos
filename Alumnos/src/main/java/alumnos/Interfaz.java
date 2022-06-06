package alumnos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Interfaz extends JFrame implements ActionListener{
    private Label title;
    private Button alumnos, editar, borrar, cerrar, actualizar;
    private JTable tab;
    String[][] data = new String[30][7];
    String[] columnNames = {"Lista", "Nombre", "Apellido", "D1", "D2", "D3", "Promedio" };
    Conectar con = new Conectar();
    Connection cn = con.conexion();


    public Interfaz() {

        title = new Label("Bienvenido");
        title.setBounds(250,30,150,30);
        title.setFont(new Font("Consolas", 1, 13));
        add(title);

        tabla();
        tab = new JTable(data, columnNames);
        tab.setBounds(55, 120, 490, 300);

        JScrollPane sp = new JScrollPane(tab);
        add(sp);


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

	    actualizar = new Button("Actualizar");
	    actualizar.setBounds(395, 70, 150, 30);
	    add(actualizar);
	    actualizar.addActionListener(this);

	    cerrar = new Button("Cerrar");
	    cerrar.setBounds(250, 510, 100, 30);
	    add(cerrar);
	    cerrar.addActionListener(this);

	    // General settings
	    setTitle("Alumnos");
	    setLayout(new FlowLayout());
	    setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public int getRows(){
        String sql = "SELECT * FROM alumno";
        Statement st;
        int rows = 0;
        try{

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                rows = rs.getRow();
            }

        }catch(SQLException row){
            System.err.println("Error");
        }

        return rows;
    }

    public void tabla(){
        // Table
        String sql = "SELECT * FROM alumno";
        Statement st;

        try{

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                int row = rs.getRow();

                for(int i=0; i<7; i++){
                    data[row-1][i] = rs.getString(i+1);
                }

            }

        }catch(SQLException e){
            System.err.println("Error");
        }

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cerrar){
            System.exit(0);
        }
        if(e.getSource() == actualizar){
            System.out.println("Actualizando");
            tabla();
            tab.repaint();
        }
        if(e.getSource() == alumnos){
            String title = "Registro";
            Registros app = new Registros(title);
        }
        if(e.getSource() == editar){
            String title = "Editar alumno";
            int row = tab.getSelectedRow();
            Object id = tab.getValueAt(row, 0);
            Registros app = new Registros(title);
            app.setId(id);
            app.fillOut();
            //System.out.println(app.id);
        }
        if(e.getSource() == borrar){
            int row = tab.getSelectedRow();
            Object id = tab.getValueAt(row, 0);
            String borrar = "DELETE FROM alumno WHERE NumeroLista = "+id;
            try{
                PreparedStatement ps = cn.prepareStatement(borrar);
                int response = ps.executeUpdate();

                if(response > 0){
                    JOptionPane.showMessageDialog(null, "Alumno borrado");
                    for(int i=0; i<9; i++){
                        for (int j = 0; j < 7; j++) {
                            data[i][j] = null;
                        }
                    }
                    tabla();
                    tab.repaint();
                }
            }catch(Exception error){
                System.out.println(error);
            }
        }

    }
}
