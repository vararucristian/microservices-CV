import java.sql.*;

public class Select2 {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "myuser", "xxxx");
             Statement stmt = conn.createStatement();
        ){
            int id_supervizor=3;
            String strSelect = "select * from employees e1 join supervision on  e1.ID=supervision.id_superviser join employees e2 on supervision.id_supervised=e2.ID where e1.ID= "+id_supervizor+";";
            System.out.println("Interogarea SQL a fost: " + strSelect + "\n");
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("Inregistrarile selectate sunt:\n");
            System.out.println("Angajatii subordonati direct angajatului cu ID="+id_supervizor+" sunt:\n");
            int rowCount = 0;
            while(rset.next()) {
                String CNP = rset.getString("e2.CNP");
                String fName = rset.getString("e2.firstName");
                String    lName  = rset.getString("e2.lastName");
                int   ID  = rset.getInt ("e2.ID");
                String gen= rset.getString ("e2.gendre");
                String adresa= rset.getString ("e2.adress");
                int salariu= rset.getInt("e2.salary");
                int cod= rset.getInt ("e2.contractCode");
                String pozitie =rset.getString ("e2.position");
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
