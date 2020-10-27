package JavaLab8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


class DbInsertion {
    Connection conn;
    Statement state;

    DbInsertion() {
        try {
            String host = "jdbc:mysql://localhost:3306/books?serverTimezone=UTC";
            String uName = "root";
            String uPass = "1234";
            conn = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAuthor(int id, String fName, String lName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into authors values(?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, fName);
            stmt.setString(3, lName);

            int count = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted with values (" + id + " " +  fName + " " + lName + ")");
            System.out.println(count + " row/s affected");

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }


    public void editAuthor(String fName, String lName, int id){
        try {
            String query = "UPDATE authors SET FirstName=" + "\"" + fName + "\""  + ", LastName= "+ "\"" +lName+ "\"" + " WHERE AuthorId = " + id;
            state = conn.createStatement();
            state.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Updated with new values (" + id + " " +  fName + " " + lName + ")");
        }
        catch (Exception e){
            System.out.println("Error!" + e);
        }
    }

    public void insertTitle(int num, String title, int year){
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into titles values(?,?,?)");
            stmt.setInt(1, num);
            stmt.setString(2, title);
            stmt.setInt(3, year);

            int count = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted with values (" + title + " " + num + " " + ")");
            //System.out.println(count + " row/s affected");

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }

    public void insertAuthorIsbn(int id, int num) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into authorisbn values(?,?)");
            stmt.setInt(1, id);
            stmt.setInt(2, num);

            int count = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inserted with values (" + id + " " + num + " " + ")");
            //System.out.println(count + " row/s affected");

        } catch (Exception e) {
            System.out.println("Error!" + e);
        }
    }
}
public class InsertDb {
    private JPanel Core;
    private JLabel Title;
    private JButton InsertAuthor;
    private JLabel FirstName;
    private JTextField Aid;
    private JTextField LName;
    private JLabel LastName;
    private JLabel AuthorId;
    private JTextField FName;
    private JPanel UpdateAuthor;
    private JButton UPDATEAUTHORButton;
    private JTextField isbn;
    private JButton INSERTAUTHORISBNButton;
    private JTextField Id;
    private JTextField book;
    private JTextField bno;
    private JTextField cpyright;
    private JButton INSERTTITLEButton;
    DbInsertion insert = new DbInsertion();


    public InsertDb() {
        InsertAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(Aid.getText());
                int id = Integer.parseInt(Aid.getText());
                System.out.println(id);
              insert.insertAuthor(id, FName.getText(), LName.getText());
            }
        });
        UPDATEAUTHORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(Aid.getText());
                insert.editAuthor(FName.getText(), LName.getText(), id);
            }
        });
        INSERTAUTHORISBNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(Id.getText());
                int num = Integer.parseInt(isbn.getText());
                insert.insertAuthorIsbn(id, num);
            }
        });
        INSERTTITLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(bno.getText());
                int year = Integer.parseInt(cpyright.getText());

                insert.insertTitle(num, book.getText(), year);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Insert and Update");
        frame.setContentPane(new InsertDb().Core);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
