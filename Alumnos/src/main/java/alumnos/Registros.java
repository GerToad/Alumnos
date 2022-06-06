package alumnos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.lang.Math;

public class Registros extends JFrame implements ActionListener{
    private Label title, nombre, apellidoPaterno, calificaciones, d1, d2, d3;
    private TextField op1, op2, op3, op4, op5;
    private Button registro, cerrar;
    String titulo, name, surname;
    int c1, c2, c3, promedio;
    Conectar con = new Conectar();
    Connection cn = con.conexion();
    Object id;

    public Registros(String titulo) {

        // Labels
        title = new Label(titulo);
        title.setBounds(250,30,150,30);
        title.setFont(new Font("Consolas", 1, 13));
        add(title);
        this.titulo = titulo;

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

        d1 = new Label("D1:");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

    }

        public void fillOut(){
                String consulta = "SELECT * FROM alumno WHERE alumno.NumeroLista = " + id;
                Statement st;

                try{
                        st = cn.createStatement();
                        ResultSet rs = st.executeQuery(consulta);
                        while(rs.next()){
                                for(int i=1; i<=7; i++){
                                        System.out.println(rs.getString(i));
                                }
                                op1.setText(rs.getString(2));
                                op2.setText(rs.getString(3));
                                op3.setText(rs.getString(4));
                                op4.setText(rs.getString(5));
                                op5.setText(rs.getString(6));

                        }
                }catch(SQLException err){
                        System.err.println("Error al cargar el alumno");
                }

        }

        public String[] getValues(){
                name = op1.getText();
                surname = op2.getText();
                String c1 = op3.getText();
                String c2 = op4.getText();
                String c3 = op5.getText();

                String[] values = {name, surname, c1, c2, c3};

                return values;
        }

        public void promedio(String d1, String d2, String d3){
                c1 = Integer.parseInt(d1);
                c2 = Integer.parseInt(d2);
                c3 = Integer.parseInt(d3);
                float prm = (c1+c2+c3)/3f;
                promedio = Math.round(prm);
        }

        public void clean(){
                op1.setText("");
                op2.setText("");
                op3.setText("");
                op4.setText("");
                op5.setText("");
        }

        public void setId(Object id){
                this.id = id;
        }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cerrar){
            setVisible(false);
        }

        if(e.getSource() == registro){
                String[] values = getValues();
                promedio(values[2], values[3], values[4]);
                if(titulo == "Registro"){
                        String sql = "INSERT INTO alumno (Nombre, ApellidoPaterno, D1, D2, D3, PromedioRedondeado) VALUES(?,?,?,?,?,?)";
                        Conectar con = new Conectar();
                        Connection cn = con.conexion();

                        try{
                                PreparedStatement ps = cn.prepareStatement(sql);
                                ps.setString(1, values[0]);
                                ps.setString(2, values[1]);
                                ps.setInt(3, c1);
                                ps.setInt(4, c2);
                                ps.setInt(5, c3);
                                ps.setInt(6, promedio);

                                ps.executeUpdate();
                                clean();

                        }catch(SQLException er){
                                System.err.println(er);
                                System.err.println("Error al guaradar los datos");
                        }

                }else{
                        String actualizar = "UPDATE alumno SET Nombre ='"+values[0]+"', ApellidoPaterno ='"+values[1]+"', D1='"+c1+"', D2='"+c2+"', D3='"+c3+"', PromedioRedondeado='"+promedio+"' WHERE alumno.NumeroLista = "+ id;
                        Conectar con = new Conectar();
                        Connection cn = con.conexion();

                        try{
                                PreparedStatement ps = cn.prepareStatement(actualizar);
                                int response = ps.executeUpdate();

                                if(response > 0){
                                        JOptionPane.showMessageDialog(null, "Datos actualizados");
                                        setVisible(false);
                                }
                        }catch(Exception error){
                                System.out.println(error);
                        }
                }
        }
    }
}
