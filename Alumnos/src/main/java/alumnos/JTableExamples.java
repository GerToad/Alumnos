package alumnos;

// Packages to import
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;

public class JTableExamples {
	// frame
	JFrame f;
	// Table
	JTable tab;

	// Constructor
	JTableExamples()
	{
		// Frame initialization
		f = new JFrame();

		// Frame Title
		f.setTitle("JTable Example");

		// Data to be displayed in the JTable
        // Table
        String[][] data = new String[30][7];
        String[] columnNames = {"Lista", "Nombre", "Apellido", "D1", "D2", "D3", "Promedio" };

        String sql = "SELECT * FROM alumno";
        Statement st;
        Conectar con = new Conectar();
        Connection cn = con.conexion();

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

        tab = new JTable(data, columnNames);
        tab.setBounds(55, 120, 490, 300);

        JScrollPane sp = new JScrollPane(tab);
        f.add(sp);

		// Frame Size
		f.setSize(600, 300);
		// Frame Visible = true
		f.setVisible(true);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Driver method
	public static void main(String[] args)
	{
		JTableExamples app = new JTableExamples();
	}
}
