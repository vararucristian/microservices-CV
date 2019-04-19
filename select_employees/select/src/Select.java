import java.sql.*;

public class Select {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "myuser", "xxxx");
             Statement stmt = conn.createStatement();
        ){
        String strSelect = "select * from employees;";
        System.out.println("Interogarea SQL a fost: " + strSelect + "\n");
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("Inregistrarile selectate sunt:\n");
            int rowCount = 0;
            while(rset.next()) {
                String CNP = rset.getString("CNP");
                String fName = rset.getString("firstName");
                String    lName  = rset.getString("lastName");
                int   ID  = rset.getInt ("ID");
                String gen= rset.getString ("gendre");
                String adresa= rset.getString ("adress");
                int salariu= rset.getInt("salary");
                int cod= rset.getInt ("contractCode");
                String pozitie =rset.getString ("position");
                System.out.println(" Angajatul cu ID="  +ID + " are urmatoarele date:");
                System.out.println(" Nume: "  + fName );
                System.out.println(" Prenume: "  + lName) ;
                System.out.println(" CNP: "  + CNP);
                System.out.println(" Gen: "  + gen );
                System.out.println(" Adresa: "  + adresa );
                System.out.println(" Salariu: "  + salariu +" lei");
                System.out.println(" Codul contactului: "  + cod );
                System.out.println(" Pozitie: "  + pozitie +"\n");
                ++rowCount;
            }
            System.out.println("Numar total de inregistrari selectate = " + rowCount);

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    }
