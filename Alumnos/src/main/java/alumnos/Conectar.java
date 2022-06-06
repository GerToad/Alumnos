package alumnos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {

    Connection cn;

    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            cn = DriverManager.getConnection("jdbc:mysql://localhost/calificaciones", "root", "1234");

            System.out.println("Conectado");
        }catch(Exception e){

            System.out.println(e);
        }

        return cn;
    }
}
