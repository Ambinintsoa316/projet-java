import java.sql.*;
import java.sql.PreparedStatement;

public class Db {

    String host = "jdbc:postgresql://localhost:5432/cepe";
    String user = "postgres";
    String mdp = "#322*63#";
    Connection con;

    public  Db(){

        try{

            con = DriverManager.getConnection(host, user, mdp);
            // JOptionPane.showMessageDialog(null, "database connected successfully");
            System.out.println("Database connected successfully !");
            PreparedStatement pstmt = con.prepareStatement(host);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
