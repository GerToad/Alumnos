package alumnos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Interfaz extends JFrame implements ActionListener{
    private Label title;
    private Button alumnos, editar, borrar, cerrar, actualizar, reporte;
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

	    reporte = new Button("Reporte PDF");
	    reporte.setBounds(395, 70, 150, 30);
	    add(reporte);
	    reporte.addActionListener(this);

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
        if(e.getSource() == reporte){
            Document doc = new Document();

            try{
                String route = System.getProperty("user.home");
                //System.out.println(route);
                PdfWriter.getInstance(doc, new FileOutputStream(route + "/Documents/Lista.pdf"));
                doc.open();

                PdfPTable tabla = new PdfPTable(7);
                tabla.addCell("# lista");
                tabla.addCell("Nombre");
                tabla.addCell("Apellido");
                tabla.addCell("1er Parcial");
                tabla.addCell("2do Parcial");
                tabla.addCell("3er Parcial");
                tabla.addCell("Promedio");

                String sql = "SELECT * FROM alumno";

                try{

                    PreparedStatement ps = cn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()){
                        do{
                            tabla.addCell(rs.getString(1));
                            tabla.addCell(rs.getString(2));
                            tabla.addCell(rs.getString(3));
                            tabla.addCell(rs.getString(4));
                            tabla.addCell(rs.getString(5));
                            tabla.addCell(rs.getString(6));
                            tabla.addCell(rs.getString(7));
                        }while(rs.next());
                        doc.add(tabla);
                    }
                }catch(DocumentException | SQLException docs){
                    System.out.println(docs);
                }
                doc.close();
                JOptionPane.showMessageDialog(null, "Reporte creado");

            }catch(DocumentException | HeadlessException | FileNotFoundException pdf){
                System.out.println(pdf);
            }
        }

    }
}
