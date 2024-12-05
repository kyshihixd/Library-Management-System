/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author rat
 */
public class Utility {
    public static void addBooks(String bookInput){
        
        String[] books = bookInput.split("\n");
            
            for (String str : books){
                String[] bookDetails = str.split(","); 
                if (bookDetails.length != 5) {
                    System.out.println("Invalid input format. Use: Title, Author, Genre, ISBN, Copies");
                    return;
                }

                String title = bookDetails[0].trim();
                String author = bookDetails[1].trim();
                String genre = bookDetails[2].trim();
                String isbn = bookDetails[3].trim();
                int copies;

                try {
                    copies = Integer.parseInt(bookDetails[4].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Copies must be a valid number.");
                    return;
                }

                String query = "INSERT INTO books (title, author, genre, isbn, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)";

                try (Connection connection = ConnectionDB.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, title);
                        statement.setString(2, author);
                        statement.setString(3, genre);
                        statement.setString(4, isbn);
                        statement.setInt(5, copies);
                        statement.setInt(6, copies);

                        statement.executeUpdate();
                        System.out.println("Input successful");

                }
                catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error connecting to the database or executing the query.");
                }
            }
    }
    
    public static void main(String[] arg){
        
    }

    static class ActionButton extends JButton {
        private boolean mousePress;
        public ActionButton(){
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3,3,3,3));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                mousePress= true;
            }
            
            @Override
            public void mouseReleased(MouseEvent me){
                mousePress= false;
            }
        });
    }
        @Override
        protected void paintComponent(Graphics grphcs){
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            int size = Math.min(width, height);
            int x = (width -size) / 2;
            int y = (height - size) / 2;
            if (mousePress){
                g2.setColor(new Color(158,158, 158));
            } else {
                g2.setColor(new Color(199, 199, 199));
            }
            g2.fill( new Ellipse2D.Double(x,y,size,size));
            g2.dispose();
            super.paintComponent(grphcs);
        }
   }
    
}

