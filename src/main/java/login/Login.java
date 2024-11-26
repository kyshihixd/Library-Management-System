/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import login.LoginPage;
import Home.HomePage;
import java.sql.*;

/**
 *
 * @author rat
 */
public class Login {
    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
        
        String url="jdbc:mysql://localhost:3306/sys";
        String user="root";
        String password="12345678";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database"+url);
            
            String query = "insert into customer(id, vehicle_number, registration_date,contact_number) values(2,'123','2022-2-2','0123456789')";
            Statement statement = connection.createStatement();
            statement.execute(query);
        
        
        
            } 
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            } catch (SQLException throwables) {
            throwables.printStackTrace();
            }
            }
            }
        
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
           */
   
       
   

