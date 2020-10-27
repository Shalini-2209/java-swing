package JavaLab8;

import java.sql.*;

class Insertion{
    Connection conn;
    Statement state;
    Insertion(){
        try {
            String host = "jdbc:mysql://localhost:3306/books?serverTimezone=UTC";
            String uName = "root";
            String uPass = "1234";
            conn = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAuthor(int id, String fName, String lName){
        try{
            PreparedStatement stmt = conn.prepareStatement("insert into authors values(?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, fName);
            stmt.setString(3, lName);

            int count = stmt.executeUpdate();

            System.out.println(count + " row/s affected");

        }
        catch (Exception e){
            System.out.println("Error!"+ e);
        }
    }

    public void editAuthor(String fName, String lName, int id){
        try {
            String query = "UPDATE authors SET FirstName=" + fName +",LastName= " +lName+  "WHERE AuthorId = " + id;
            state = conn.createStatement();
            ResultSet res = state.executeQuery(query);

        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }
}

public class DbInsert {
    public static void main(String[] args) {
        Insertion val = new Insertion();
        //val.insertAuthor(5,"Sheldon", "Cooper");
    }
}
