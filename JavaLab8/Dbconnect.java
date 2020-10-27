package JavaLab8;
import java.sql.*;
class DbMethods{

    Connection con = null;
    Statement st;
    String query;

    DbMethods() {
        try {
            String host = "jdbc:mysql://localhost:3306/books?serverTimezone=UTC";
            String uName = "root";
            String uPass = "1234";
            con = DriverManager.getConnection(host, uName, uPass);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAll(){
        try {
            query = "SELECT * FROM authors";
            st = con.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                System.out.println(res.getString("AuthorId") + " | " + res.getString("FirstName"));
            }
        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }

    public void getByAuthor(int AuthorId){
        try {
            query = "SELECT a.AuthorId, a.FirstName, b.ISBN, b.Copyright, b.Title FROM authors a, titles b, authorisbn c where a.AuthorId = 2 AND b.ISBN = c.ISBN AND a.AuthorId = c.AuthorId";
            st = con.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                System.out.println(res.getInt("AuthorId") + " | " + res.getString("FirstName") +
                       " | "+ res.getInt("ISBN") + " | " + res.getInt("Copyright") + " | "
                        + res.getString("Title"));
            }
        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }

    public void getAuthorOfTitle(String heading){
        try {

            query = "SELECT c.AuthorId, a.FirstName, a.LastName FROM authors a, authorisbn c WHERE a.AuthorId = c.AuthorId AND c.ISBN = (SELECT ISBN from titles where Title = "+"\"" + heading + "\"" + ")";
            st = con.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                System.out.println(res.getInt("AuthorId") + " | " + res.getString("FirstName") +
                        " | " + res.getString("LastName"));
            }
        }
        catch (Exception e){
            System.out.println("Error!"+ e);
        }
    }
}
public class Dbconnect {

    public static void main(String[] args) {

           DbMethods obj = new DbMethods();
           //obj.getAll();
           //obj.getByAuthor(2);
           obj.getAuthorOfTitle("Python");


    }
}