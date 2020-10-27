package JavaLab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DbOperations{

    Connection con = null;
    Statement st;
    String query;
    ResultSet res;

    DbOperations() {
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
            res = st.executeQuery(query);

        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }

    public void getByAuthor(int aid){
        try {
            query = "SELECT a.AuthorId, a.FirstName, b.ISBN, b.Copyright, b.Title FROM authors a, titles b, authorisbn c where a.AuthorId =" + aid + " AND b.ISBN = c.ISBN AND a.AuthorId = c.AuthorId";
            st = con.createStatement();
            res = st.executeQuery(query);

        }
        catch (Exception e){
            System.out.println("Error!");
        }
    }

    public void getAuthorOfTitle(String heading){
        try {

            query = "SELECT c.AuthorId, a.FirstName, a.LastName FROM authors a, authorisbn c WHERE a.AuthorId = c.AuthorId AND c.ISBN = (SELECT ISBN from titles where Title = "+"\"" + heading + "\"" + ") ORDER BY LastName, FirstName";
            st = con.createStatement();
            res = st.executeQuery(query);

        }
        catch (Exception e){
            System.out.println("Error!"+ e);
        }
    }
}

public class SelectForm {

    private JPanel Main;
    private JLabel Title;
    private JButton getAuthors;
    private JComboBox BookTitles;
    private JLabel FindAuthor;
    private JComboBox comboBox1;
    private JTextArea Result;
    DbOperations opr = new DbOperations();

    public SelectForm() {


        getAuthors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opr.getAll();
                try {
                    Result.setText("");
                    String attribute = "Author Id" + "\t" + "First Name" + "\t" + "Last Name";
                    Result.append(attribute);
                    while (opr.res.next()) {
                        String row = opr.res.getString("AuthorId") + " \t " + opr.res.getString("FirstName") + " \t " + opr.res.getString("LastName");
                        //System.out.println(opr.res.getString("AuthorId") + " | " + opr.res.getString("FirstName"));
                        Result.append("\n" + row);
                    }
                }
                catch (Exception err){
                    System.out.println("Error");
                }
            }
        });


        BookTitles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value;
                value = BookTitles.getSelectedItem().toString();
                opr.getAuthorOfTitle(value);
                System.out.println("Action performed");
                try{
                    Result.setText("");
                    String attribute = "Author Id" + "\t" + "First Name" + "\t" + "Last Name" ;
                    attribute += "\n ----------------------------------------------------------";
                    Result.append(attribute);
                    while (opr.res.next()) {
                        String row = opr.res.getString("AuthorId") + " \t " + opr.res.getString("FirstName") + " \t " + opr.res.getString("LastName");
                        Result.append("\n" + row);
                    }
                }
                catch (Exception err){
                    System.out.println("Err"+ err);
                }
            }
        });


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result.setText("");
                try {
                    String attribute = "Author Id" + "\t" + "First Name" + "\t" + "ISBN" + "\t" + "Copyright" + "\t" + "Books" ;
                    attribute += "\n ---------------------------------------------------------------------------------------------------------------------------------------------";
                    Result.append(attribute);
                    int value;
                    value = Integer.parseInt((String)comboBox1.getSelectedItem());
                    System.out.println(value);
                    opr.getByAuthor(value);
                    System.out.println("Action");
                    while (opr.res.next()) {
                        String row = opr.res.getInt("AuthorId") + " \t " + opr.res.getString("FirstName") +
                                " \t " + opr.res.getInt("ISBN") + " \t " + opr.res.getInt("Copyright") + " \t "
                                + opr.res.getString("Title");
                        Result.append( "\n" + row);
                    }

                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });
    }

    public static void main(String[] args) {

            JFrame frame = new JFrame("SelectForm");
            frame.setContentPane(new SelectForm().Main);
            frame.setSize(500,1000);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
    }
}

