/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.util.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
    
    public static List<Map<String, Object>> stringToListMap(String str, List<Map<String, Object>> list){
        String[] books = str.split("\n");
        
        for (String string : books){
            String[] bookDetails = string.split(",");
            Map<String, Object> map = new HashMap<>();
            if (bookDetails.length == 6) {
                map.put("title", bookDetails[0].trim());
                map.put("author", bookDetails[1].trim());
                map.put("genre", bookDetails[2].trim());
                map.put("isbn", bookDetails[3].trim());
                map.put("total_copies", Integer.parseInt(bookDetails[4].trim()));
                map.put("available_copies", Integer.parseInt(bookDetails[5].trim()));
            }
            list.add(map);
        }
        

        return list;
    }
    
    public static Object getIdFromTableRow(JTable table, int row){
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        return tableModel.getValueAt(row, 0);
    }
    
    public static Map<String, Object> getDataFromID(Object id, String key, List<Map<String, Object>> map){
        for (Map<String, Object> row : map) {
            if (row.get(key).equals(id)) {
                return row; 
            }
        }
        return null;
    }
    
    public static List<Map<String, Object>> BooksToTableList(){
        String query = "SELECT * FROM books";
        List<Map<String, Object>> booksList = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> book = new HashMap<>();
                for (int i = columnCount; i > 0; i--) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    book.put(columnName, columnValue);
                }
                booksList.add(book);
            }
            System.out.println(booksList);
            System.out.println("Books retrieved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database or executing the query.");
        }

        return booksList;
    }
    
    public static List<Map<String, Object>> UsersToTableList(){
        String query = "SELECT * FROM users";
        List<Map<String, Object>> booksList = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

    
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> book = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    book.put(columnName, columnValue);
                }
                booksList.add(book);
            }
            System.out.println(booksList);
            System.out.println("Books retrieved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database or executing the query.");
        }

        return booksList;
    }
    
    public static List<Map<String, Object>> BorrowsToTableList(){
        String query = "SELECT * FROM borrows_records";
        List<Map<String, Object>> booksList = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> book = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    book.put(columnName, columnValue);
                }
                booksList.add(book);
            }
            System.out.println(booksList);
            System.out.println("Books retrieved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database or executing the query.");
        }
        
        return booksList;
    }
            
            
    public static void mapToMBTable(List<Map<String, Object>> map, JTable table){
        if (map == null || map.isEmpty()) {
        System.out.println("No data to display in the table.");
        return;
        }

        Map<String, Object> firstRow = map.get(0);
        String[] columnNames = firstRow.keySet().toArray(new String[0]);

        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.setRowCount(0);
        int col = tableModel.getColumnCount();
        for (Map<String, Object> row : map) {
            Object[] rowData = new Object[col];
            rowData[0] = row.get("book_id");
            rowData[1] = row.get("title");
            rowData[2] = row.get("author");
            rowData[3] = row.get("total_copies");
            rowData[4] = row.get("available_copies");
            tableModel.addRow(rowData);
        }
    }
    
    public static void main(String[] arg){
        List<Map<String, Object>> temp = BooksToTableList();
        Map<String, Object> item1 = temp.get(0);
        System.out.println(item1);
        
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

