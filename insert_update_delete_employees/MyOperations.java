import java.sql.*;

public class MyOperations {
    public void mySelect(){
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                                    "myuser", "xxxx");
            Statement statement = connection.createStatement();
            ){
           String strSelect = "select * from employees";

            ResultSet resultSet = statement.executeQuery(strSelect);
            int rowCount = 0;
            while(resultSet.next()){
                String cnp = resultSet.getString("CNP");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String gendre = resultSet.getString("gendre");
                String adress = resultSet.getString("adress");
                int id = resultSet.getInt("ID");
                int salary = resultSet.getInt("salary");
                int contractCode = resultSet.getInt("contractCode");
                String position = resultSet.getString("position");
                System.out.println(cnp + ", " + firstName + ", " + lastName + ", " + gendre + ", " +
                        adress + ", " + id + ", " + salary + ", " + contractCode + ", " + position);
                rowCount++;
            }
            System.out.println("Total number of records: " + rowCount);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void myInsertEmployee(String cnp, String firstName, String lastName, String gendre, String adress, int id, int salary, int contractCode, String position){
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx");
                Statement statement = connection.createStatement();
        ){
            String strInsert = "insert into employees values(" + cnp + ", " + firstName + ", " + lastName + ", " +
                    gendre + ", " + adress + ", " + id + ", " + salary + ", " + contractCode + ", " + position + ")";
            int resultSet = statement.executeUpdate(strInsert);
            System.out.println("Row inserted");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void myInsertSupervisor(int idSupervisor, int idEmployee){
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx");
                Statement statement = connection.createStatement();
        ){
            String strInsert = "insert into supervision values(" + idSupervisor + ", " + idEmployee + ")";
            int resultSet = statement.executeUpdate(strInsert);
            System.out.println("Row inserted");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void myDelete(int id){

        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx");
                Statement statement = connection.createStatement();
        ){
            String strDelete = "delete from employees where ID = " + id;
            int resultSet = statement.executeUpdate(strDelete);

	    strDelete = "delete from supervision where id_supervised = " + id;
	    resultSet = statement.executeUpdate(strDelete);

            System.out.println("Row deleted");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void myUpdate(int id, int salary){
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "myuser", "xxxx");
                Statement statement = connection.createStatement();
        ){
            String strUpdate = "update employees set salary = " + salary + " where ID = " + id;
            int resultSet = statement.executeUpdate(strUpdate);

            System.out.println("Row updated");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
