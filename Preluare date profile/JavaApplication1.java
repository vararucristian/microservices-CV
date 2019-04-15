
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApplication1 {

   
    public static void main(String[] args) {
        JavaApplication1 pro=new JavaApplication1();
        pro.createConnection();
    }
    
    void createConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profile", "root","Masalupaul98");
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM profile");
            while(rs.next())
            {
                String lname=rs.getString("last_name");
                System.out.print(lname+" ");
                
                String name=rs.getString("name");
                System.out.println(name);
            }
            System.out.println("Database Connection succ !");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JavaApplication1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
