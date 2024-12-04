/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author rat
 */
public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

/* GET TEXT FROM TEXT AREA
    if (evt.getSource()==jButton1){
            System.out.println("Input: "+ jTextArea1.getText());
        }



        String query = "";
        try (Connection connection = ConnectionDB.getConnection();
             Statement statement = connection.createStatement()) {
        
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database or executing the query.");
        }
*/
