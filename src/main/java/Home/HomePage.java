/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Home;

import java.awt.Color;
import java.sql.Statement;
import javax.swing.JPanel;
import Home.AddBooks;
import java.sql.*;
import Utility.TableActionCellRender;
import Utility.TableActionCellEditor;
import Utility.TableActionEvent;
import Utility.*;
import static Utility.Utility.mapToMBTable;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import login.Login;
/**
 *
 * @author rat
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    public static List<Map<String, Object>> toBeAddedMB = new ArrayList<>();
    public static List<Map<String, Object>> toBeAddedMU = new ArrayList<>();
    public static List<Map<String, Object>> toBeAddedBRB = new ArrayList<>();
    
    public static List<Map<String, Object>> toBeUpdatedMB = new ArrayList<>();
    public static List<Map<String, Object>> toBeUpdatedMU = new ArrayList<>();
    public static List<Map<String, Object>> toBeUpdatedBRB = new ArrayList<>();
    
    public static List<Map<String, Object>> toBeDeletedMB = new ArrayList<>();
    public static List<Map<String, Object>> toBeDeletedMU = new ArrayList<>();
    public static List<Map<String, Object>> toBeDeletedBRB = new ArrayList<>();
    
    public static Queue<String> tracker = new LinkedList<>();
    
    List<Map<String, Object>> booksList;
    List<Map<String, Object>> usersList;
    List<Map<String, Object>> borrowsList;
    public HomePage() {
        initComponents();
        theader();
        booksList = Utility.BooksToTableList();
        Utility.mapToMBTable(booksList, MBTable); // SET TABLE MB
        
        usersList = Utility.UsersToTableList();
        Utility.mapToMUTable(usersList, MUTable);
        
        borrowsList = Utility.BorrowsToTableList();
        Utility.mapToBRBTable(borrowsList,usersList,booksList, BRBTable);
        
        TableActionEvent event1 = new TableActionEvent(){
            public void onEdit(int row){
                if (MBTable.isEditing()) {
                    MBTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Edit "+ row);
                Object id = Utility.getIdFromTableRow(MBTable, row);
                Map<String, Object> selectedBook = Utility.getDataFromID(id, "book_id", booksList);
                
                if (selectedBook != null) {
                    new onEditMB(HomePage.this, selectedBook).setVisible(true);
                } else {
                    System.out.println("Error: Book not found in toBeAddedMB.");
                }
                System.out.println(toBeUpdatedMB); 
            }
    
            public void onDelete(int row){
                if (MBTable.isEditing()) {
                    MBTable.getCellEditor().stopCellEditing();
                }
                Object id = Utility.getIdFromTableRow(MBTable, row);
                Map<String, Object> selectedBook = Utility.getDataFromID(id, "book_id", booksList);
                booksList.remove(selectedBook);
                toBeDeletedMB.add(selectedBook);
                tracker.add("delMB");
                mapToMBTable(booksList, MBTable);
                System.out.println(toBeDeletedMB);
            }
            
            public void onView(int row){
                System.out.println("View  "+ row);
                Object id = Utility.getIdFromTableRow(MBTable, row);
                Map<String, Object> data = Utility.getDataFromID(id, "book_id", booksList);
                    if (data != null) {
                    StringBuilder message = new StringBuilder("Book Details:\n");
                    message.append("Book ID: ").append(data.get("book_id")).append("\n");
                    message.append("Title: ").append(data.get("title")).append("\n");
                    message.append("Author: ").append(data.get("author")).append("\n");
                    message.append("Genre: ").append(data.get("genre")).append("\n");
                    message.append("ISBN: ").append(data.get("isbn")).append("\n");
                    message.append("Total Copies: ").append(data.get("total_copies")).append("\n");
                    message.append("Available Copies: ").append(data.get("available_copies"));

                    JOptionPane.showMessageDialog(
                        null,
                        message.toString(), 
                        "View Book Details", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No data found for the selected book.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                    
            }
        };
        
        
        MBTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        MBTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event1));
        
        
        TableActionEvent event2 = new TableActionEvent(){
            public void onEdit(int row){
                if (MUTable.isEditing()) {
                    MUTable.getCellEditor().stopCellEditing();
                }
                System.out.println("Edit "+ row);
                Object id = Utility.getIdFromTableRow(MUTable, row);
                Map<String, Object> selectedUser = Utility.getDataFromID(id, "users_id", usersList);
                
                if (selectedUser != null) {
                    new onEditMU(HomePage.this, selectedUser).setVisible(true);
                } else {
                    System.out.println("Error: Book not found in toBeAddedMB.");
                }
                System.out.println(toBeUpdatedMU); 
            }
    
            public void onDelete(int row){
                if (MUTable.isEditing()) {
                    MUTable.getCellEditor().stopCellEditing();
                }
                Object id = Utility.getIdFromTableRow(MUTable, row);
                Map<String, Object> selectedBook = Utility.getDataFromID(id, "users_id", usersList);
                usersList.remove(selectedBook);
                toBeDeletedMU.add(selectedBook);
                tracker.add("delMU");
                Utility.mapToMUTable(usersList, MUTable);
                System.out.println(toBeDeletedMU);
            }
            
            public void onView(int row){
                System.out.println("View  "+ row);
                Object id = Utility.getIdFromTableRow(MUTable, row);
                Map<String, Object> data = Utility.getDataFromID(id, "users_id", usersList);
                    if (data != null) {
                    StringBuilder message = new StringBuilder("User Details:\n");
                    message.append("User ID: ").append(data.get("users_id")).append("\n");
                    message.append("External ID: ").append(data.get("external_id")).append("\n");
                    message.append("Username: ").append(data.get("username")).append("\n");
                    message.append("Email: ").append(data.get("email")).append("\n");
                    message.append("Phone Number: ").append(data.get("phone")).append("\n");
                    message.append("Join Date: ").append(data.get("join_date")).append("\n");

                    JOptionPane.showMessageDialog(
                        null,
                        message.toString(), 
                        "View User Details", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No data found for the selected user.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
        
        MUTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        MUTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event2));
        
        
        TableActionEvent event3 = new TableActionEvent(){
            public void onEdit(int row) {
                if (BRBTable.isEditing()) {
                    BRBTable.getCellEditor().stopCellEditing();
                }

                System.out.println("Edit " + row);

                Object id = Utility.getIdFromTableRow(BRBTable, row);
                Map<String, Object> selectedBorrow = Utility.getDataFromID(id, "record_id", borrowsList);

                if (selectedBorrow.get("return_date") != null) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Cannot edit record. The book is already returned.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {    
                    
                    Map<String, Object> updatedBook = null;
                    for (int i = 0; i < booksList.size(); i++) {
                        Map<String, Object> book = booksList.get(i);
                        if (book.get("book_id").equals(selectedBorrow.get("book_id"))) {
                            updatedBook = new HashMap<>(book); 
                            updatedBook.put("available_copies", (int)book.get("available_copies") + 1);

                            booksList.remove(i);
                            break;
                        }
                    }

                    if (updatedBook != null) {
                        booksList.add(updatedBook);
                        toBeUpdatedMB.add(updatedBook);
                        tracker.add("updateMB");
                        tracker.add("updateBRB");
                        
                        refreshMBTable();
                        System.out.println("Book updated successfully: " + updatedBook);
                    } else {
                        System.out.println("Error: Book not found in booksList.");
                    }

                        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                        selectedBorrow.put("return_date", currentDate);

                        java.sql.Date dueDate = (java.sql.Date) selectedBorrow.get("due_date");
                        if (currentDate.after(dueDate)) {
                            selectedBorrow.put("fine", 50.00);
                        } else {
                            selectedBorrow.put("fine", 0.00);
                        }

                        toBeUpdatedBRB.add(selectedBorrow);
                        tracker.add("updateBRB");

                        updateBRBTable();
                        System.out.println("Updated borrow record: " + selectedBorrow);
                    }
            }

    
            public void onDelete(int row){
                if (BRBTable.isEditing()) {
                    BRBTable.getCellEditor().stopCellEditing();
                }
                Object id = Utility.getIdFromTableRow(BRBTable, row);
                Map<String, Object> selectedUser = Utility.getDataFromID(id, "record_id", borrowsList);
                if (selectedUser.get("return_date") == null){
                    JOptionPane.showMessageDialog(
                        null,
                        "Cannot delete unfinished transaction",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                else {
                    borrowsList.remove(selectedUser);
                    toBeDeletedBRB.add(selectedUser);
                    tracker.add("delBRB");
                    Utility.mapToBRBTable(borrowsList,usersList,booksList, BRBTable);
                    System.out.println(toBeDeletedBRB);
                }
                
            }
            
            public void onView(int row){
                System.out.println("View  "+ row);
                Object id = Utility.getIdFromTableRow(BRBTable, row);
                Map<String, Object> data = Utility.getDataFromID(id, "record_id", borrowsList);
                Object bookId = data.get("book_id");
                Object userId = data.get("users_id");

                String bookName = booksList.stream()
                    .filter(book -> book.get("book_id").equals(bookId))
                    .map(book -> (String) book.get("title"))
                    .findFirst()
                    .orElse("Unknown Book");

                String userName = usersList.stream()
                    .filter(user -> user.get("users_id").equals(userId))
                    .map(user -> (String) user.get("username"))
                    .findFirst()
                    .orElse("Unknown User");
                    if (data != null) {
                    StringBuilder message = new StringBuilder("User Details:\n");
                    message.append("Record ID: ").append(data.get("record_id")).append("\n");
                    message.append("Book name: ").append(bookName).append("\n");
                    message.append("User name: ").append(userName).append("\n");
                    message.append("Borrow Date: ").append(data.get("borrow_date")).append("\n");
                    message.append("Due Date: ").append(data.get("due_date")).append("\n");
                    message.append("Return Date: ").append(data.get("return_date")).append("\n");
                    message.append("Fine: ").append(data.get("fine")).append("\n");

                    JOptionPane.showMessageDialog(
                        null,
                        message.toString(), 
                        "View User Details", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "No data found for the selected user.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
        
        BRBTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        BRBTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event3));
       
    
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Program is exiting...");
                    System.out.println("All changes now stored in the database");
                    processPendingData(tracker);
                })); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btn_Home = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_A = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btn_B = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        ManageBooks = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        MBAddbook = new javax.swing.JButton();
        MBSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        MBTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        ManageUsers = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        MUTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        MUSearch = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        BorrowReturn = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        BRBSearch = new javax.swing.JTextField();
        BorrowBt = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        BRBTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(97, 208, 199));
        jPanel2.setDoubleBuffered(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(450, 300));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setMinimumSize(new java.awt.Dimension(100, 20));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Librams");

        btn_Home.setBackground(new java.awt.Color(147, 255, 249));
        btn_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_HomeMouseClicked(evt);
            }
        });

        jLabel2.setText("Manage Books");

        javax.swing.GroupLayout btn_HomeLayout = new javax.swing.GroupLayout(btn_Home);
        btn_Home.setLayout(btn_HomeLayout);
        btn_HomeLayout.setHorizontalGroup(
            btn_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HomeLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_HomeLayout.setVerticalGroup(
            btn_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_HomeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_A.setBackground(new java.awt.Color(122, 232, 224));
        btn_A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AMouseClicked(evt);
            }
        });

        jLabel7.setText("Manage Users");

        javax.swing.GroupLayout btn_ALayout = new javax.swing.GroupLayout(btn_A);
        btn_A.setLayout(btn_ALayout);
        btn_ALayout.setHorizontalGroup(
            btn_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ALayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_ALayout.setVerticalGroup(
            btn_ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ALayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_B.setBackground(new java.awt.Color(122, 232, 224));
        btn_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_BMouseClicked(evt);
            }
        });

        jLabel9.setText("Borrow/Return Books");

        javax.swing.GroupLayout btn_BLayout = new javax.swing.GroupLayout(btn_B);
        btn_B.setLayout(btn_BLayout);
        btn_BLayout.setHorizontalGroup(
            btn_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_BLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_BLayout.setVerticalGroup(
            btn_BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_BLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
            .addComponent(btn_Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btn_Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1000, 800));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ManageBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(128, 128, 128));
        jPanel3.setAlignmentX(0.0F);
        jPanel3.setAlignmentY(0.0F);
        jPanel3.setAutoscrolls(true);
        jPanel3.setFocusCycleRoot(true);
        jPanel3.setFocusTraversalPolicyProvider(true);
        jPanel3.setPreferredSize(new java.awt.Dimension(1145, 100));
        jPanel3.setVerifyInputWhenFocusTarget(false);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 24));
        jLabel4.setText("Manage Books");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addContainerGap(513, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        ManageBooks.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 750, 186));

        MBAddbook.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MBAddbook.setText("Add Book");
        MBAddbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MBAddbookActionPerformed(evt);
            }
        });
        ManageBooks.add(MBAddbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 238, 110, -1));

        MBSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MBSearchActionPerformed(evt);
            }
        });
        ManageBooks.add(MBSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 185, -1));

        MBTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Title", "Author", "Total copies", "Available", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MBTable.setRowHeight(40);
        jScrollPane3.setViewportView(MBTable);
        if (MBTable.getColumnModel().getColumnCount() > 0) {
            MBTable.getColumnModel().getColumn(0).setMinWidth(75);
            MBTable.getColumnModel().getColumn(0).setPreferredWidth(75);
            MBTable.getColumnModel().getColumn(0).setMaxWidth(75);
            MBTable.getColumnModel().getColumn(1).setResizable(false);
            MBTable.getColumnModel().getColumn(2).setResizable(false);
            MBTable.getColumnModel().getColumn(3).setResizable(false);
            MBTable.getColumnModel().getColumn(3).setPreferredWidth(1);
            MBTable.getColumnModel().getColumn(4).setResizable(false);
            MBTable.getColumnModel().getColumn(4).setPreferredWidth(1);
            MBTable.getColumnModel().getColumn(5).setMinWidth(135);
            MBTable.getColumnModel().getColumn(5).setPreferredWidth(135);
            MBTable.getColumnModel().getColumn(5).setMaxWidth(135);
        }

        ManageBooks.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 279, 719, 503));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Search here");
        ManageBooks.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 241, -1, -1));

        jLayeredPane1.setLayer(ManageBooks, 4);
        jLayeredPane1.add(ManageBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        ManageUsers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(128, 128, 128));
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setAutoscrolls(true);
        jPanel4.setFocusCycleRoot(true);
        jPanel4.setFocusTraversalPolicyProvider(true);
        jPanel4.setPreferredSize(new java.awt.Dimension(1145, 100));
        jPanel4.setVerifyInputWhenFocusTarget(false);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel8.setText("Manage Users");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel8)
                .addContainerGap(892, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        ManageUsers.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1120, 186));

        MUTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        MUTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Name", "Phone Number", "Join Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MUTable.setRowHeight(40);
        jScrollPane4.setViewportView(MUTable);
        if (MUTable.getColumnModel().getColumnCount() > 0) {
            MUTable.getColumnModel().getColumn(0).setResizable(false);
            MUTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            MUTable.getColumnModel().getColumn(1).setResizable(false);
            MUTable.getColumnModel().getColumn(2).setResizable(false);
            MUTable.getColumnModel().getColumn(3).setResizable(false);
            MUTable.getColumnModel().getColumn(4).setMinWidth(135);
            MUTable.getColumnModel().getColumn(4).setPreferredWidth(135);
            MUTable.getColumnModel().getColumn(4).setMaxWidth(135);
        }

        ManageUsers.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 279, 719, 503));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Search here");
        ManageUsers.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 241, -1, -1));

        MUSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MUSearchActionPerformed(evt);
            }
        });
        ManageUsers.add(MUSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 238, 170, -1));

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton6.setText("Add User");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        ManageUsers.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 238, 120, -1));

        jLayeredPane1.setLayer(ManageUsers, 3);
        jLayeredPane1.add(ManageUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        BorrowReturn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(128, 128, 128));
        jPanel5.setAlignmentX(0.0F);
        jPanel5.setAlignmentY(0.0F);
        jPanel5.setAutoscrolls(true);
        jPanel5.setFocusCycleRoot(true);
        jPanel5.setFocusTraversalPolicyProvider(true);
        jPanel5.setPreferredSize(new java.awt.Dimension(1145, 100));
        jPanel5.setVerifyInputWhenFocusTarget(false);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel10.setText("Borrow/ Return books");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel10)
                .addContainerGap(1174, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        BorrowReturn.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1490, 186));

        BRBSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRBSearchActionPerformed(evt);
            }
        });
        BorrowReturn.add(BRBSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 238, 160, -1));

        BorrowBt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        BorrowBt.setText("Borrow Books");
        BorrowBt.setPreferredSize(new java.awt.Dimension(110, 23));
        BorrowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrowBtActionPerformed(evt);
            }
        });
        BorrowReturn.add(BorrowBt, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 238, 140, -1));

        BRBTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        BRBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Borrower Name", "Book Name", "Due Date", "Return Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BRBTable.setRowHeight(40);
        jScrollPane5.setViewportView(BRBTable);
        if (BRBTable.getColumnModel().getColumnCount() > 0) {
            BRBTable.getColumnModel().getColumn(0).setResizable(false);
            BRBTable.getColumnModel().getColumn(0).setPreferredWidth(20);
            BRBTable.getColumnModel().getColumn(1).setResizable(false);
            BRBTable.getColumnModel().getColumn(1).setPreferredWidth(160);
            BRBTable.getColumnModel().getColumn(2).setResizable(false);
            BRBTable.getColumnModel().getColumn(2).setPreferredWidth(160);
            BRBTable.getColumnModel().getColumn(3).setResizable(false);
            BRBTable.getColumnModel().getColumn(3).setPreferredWidth(45);
            BRBTable.getColumnModel().getColumn(4).setResizable(false);
            BRBTable.getColumnModel().getColumn(4).setPreferredWidth(45);
            BRBTable.getColumnModel().getColumn(5).setMinWidth(135);
            BRBTable.getColumnModel().getColumn(5).setPreferredWidth(135);
            BRBTable.getColumnModel().getColumn(5).setMaxWidth(135);
        }

        BorrowReturn.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 279, 719, 503));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Search here");
        BorrowReturn.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, -1, -1));

        jLayeredPane1.setLayer(BorrowReturn, 2);
        jLayeredPane1.add(BorrowReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 800));

        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 750, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_BMouseClicked
        setColor(btn_B);
        resetColor(btn_Home);
        resetColor(btn_A);
        ManageBooks.setVisible(false);
        ManageUsers.setVisible(false);
        BorrowReturn.setVisible(true);
    }//GEN-LAST:event_btn_BMouseClicked

    private void btn_HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HomeMouseClicked
        setColor(btn_Home);
        resetColor(btn_A);
        resetColor(btn_B);
        ManageBooks.setVisible(true);
        ManageUsers.setVisible(false);
        BorrowReturn.setVisible(false);
    }//GEN-LAST:event_btn_HomeMouseClicked

    private void btn_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AMouseClicked
        setColor(btn_A);
        resetColor(btn_Home);
        resetColor(btn_B);
        ManageBooks.setVisible(false);
        ManageUsers.setVisible(true);
        BorrowReturn.setVisible(false);
    }//GEN-LAST:event_btn_AMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AddUsers obj = new AddUsers(this);
        obj.show();
        System.out.println(toBeAddedMU);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void BorrowBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrowBtActionPerformed
        AddBorrows obj = new AddBorrows(this);
        obj.show();
        System.out.println(toBeAddedBRB);
    }//GEN-LAST:event_BorrowBtActionPerformed

    private void MBAddbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MBAddbookActionPerformed
        AddBooks obj = new AddBooks(this);
        obj.show();
        System.out.println(toBeAddedMB);
    }//GEN-LAST:event_MBAddbookActionPerformed

    private void MBSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MBSearchActionPerformed
        String searchQuery = MBSearch.getText().toLowerCase();

        List<Map<String, Object>> filteredBooks = booksList.stream()
                .filter(book -> book.values().stream()
                        .anyMatch(value -> value.toString().toLowerCase().contains(searchQuery)))
                .toList();

        mapToMBTable(filteredBooks, MBTable);
    }//GEN-LAST:event_MBSearchActionPerformed

    private void MUSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MUSearchActionPerformed
                String searchQuery = MUSearch.getText().toLowerCase();

        List<Map<String, Object>> filteredBooks = usersList.stream()
                .filter(user -> user.values().stream()
                        .anyMatch(value -> value.toString().toLowerCase().contains(searchQuery)))
                .toList();

        Utility.mapToMUTable(filteredBooks, MUTable);
    }//GEN-LAST:event_MUSearchActionPerformed

    private void BRBSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRBSearchActionPerformed
        String lowerCaseQuery = BRBSearch.getText().toLowerCase();

        List<Map<String, Object>> filteredBorrows = borrowsList.stream()
            .filter(record -> {
                String bookName = booksList.stream()
                        .filter(book -> book.get("book_id").equals(record.get("book_id")))
                        .map(book -> (String) book.get("title"))
                        .findFirst()
                        .orElse("");

                String userName = usersList.stream()
                        .filter(user -> user.get("users_id").equals(record.get("users_id")))
                        .map(user -> (String) user.get("username"))
                        .findFirst()
                        .orElse("");

                
                String recordId = String.valueOf(record.get("record_id"));
                String dueDate = record.get("due_date") != null ? record.get("due_date").toString() : "";
                String returnDate = record.get("return_date") != null ? record.get("return_date").toString() : "";

                
                return recordId.toLowerCase().contains(lowerCaseQuery) ||
                       bookName.toLowerCase().contains(lowerCaseQuery) ||
                       userName.toLowerCase().contains(lowerCaseQuery) ||
                       dueDate.toLowerCase().contains(lowerCaseQuery) ||
                       returnDate.toLowerCase().contains(lowerCaseQuery);
            })
            .toList();

    
        Utility.mapToBRBTable(filteredBorrows, usersList, booksList, BRBTable);
    }//GEN-LAST:event_BRBSearchActionPerformed
    
    public void refreshMBTable() {
        DefaultTableModel model = (DefaultTableModel) MBTable.getModel();
        model.setRowCount(0);
        for (Map<String, Object> book : toBeAddedMB) {
            if (booksList.contains(book))
                continue;
            booksList.add(book);
        }
        mapToMBTable(booksList, MBTable);   
    }
    
    public void updateMBTable() {
        if (!toBeUpdatedMB.isEmpty()) {
        Map<String, Object> book = toBeUpdatedMB.get(toBeUpdatedMB.size() - 1);

        for (int i = 0; i < booksList.size(); i++) {
            Map<String, Object> booktemp = booksList.get(i);
            if (booktemp.get("book_id").equals(book.get("book_id"))) {
                booksList.remove(i);
                break; 
            }
        }
        booksList.add(book);
        mapToMBTable(booksList, MBTable);
        }  
    }
    
    public void refreshMUTable() {
        DefaultTableModel model = (DefaultTableModel) MUTable.getModel();
        model.setRowCount(0);
        for (Map<String, Object>user : toBeAddedMU) {
            if (usersList.contains(user))
                continue;
            usersList.add(user);
        }
        Utility.mapToMUTable(usersList, MUTable);   
    }
    
    public void updateMUTable() {
        if (!toBeUpdatedMU.isEmpty()) {
        Map<String, Object> book = toBeUpdatedMU.get(toBeUpdatedMU.size() - 1);

        for (int i = 0; i < usersList.size(); i++) {
            Map<String, Object> booktemp = usersList.get(i);
            if (booktemp.get("users_id").equals(book.get("users_id"))) {
                usersList.remove(i);
                break; 
            }
        }
        usersList.add(book);
        Utility.mapToMUTable(usersList, MUTable);
        }  
    }
    
    public void refreshBRBTable() {
        DefaultTableModel model = (DefaultTableModel) BRBTable.getModel();
        model.setRowCount(0);
        for (Map<String, Object>user : toBeAddedBRB) {
            if (borrowsList.contains(user))
                continue;
            borrowsList.add(user);
        }
        Utility.mapToBRBTable(borrowsList, usersList, booksList, BRBTable);   
    }
    
    public void updateBRBTable() {
        if (!toBeUpdatedBRB.isEmpty()) {
        Map<String, Object> book = toBeUpdatedBRB.get(toBeUpdatedBRB.size() - 1);

        for (int i = 0; i < borrowsList.size(); i++) {
            Map<String, Object> booktemp = borrowsList.get(i);
            if (booktemp.get("users_id").equals(book.get("users_id"))) {
                borrowsList.remove(i);
                break; 
            }
        }
        borrowsList.add(book);
        Utility.mapToBRBTable(borrowsList, usersList, booksList, BRBTable);   
        }  
    }
    
    private void theader(){
        JTableHeader MBHead = MBTable.getTableHeader();
        JTableHeader MUHead = MUTable.getTableHeader();
        JTableHeader BRBHead = BRBTable.getTableHeader();
        MBHead.setFont(new Font("Century Gothic",Font.PLAIN, 12));
        MUHead.setFont(new Font("Century Gothic",Font.PLAIN, 12));
        BRBHead.setFont(new Font("Century Gothic",Font.PLAIN, 12));
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(147,255,249));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(122,232,224));
    }
    
    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    }  
    
    public static void processPendingData(Queue<String> tracker) {
        while (!tracker.isEmpty()) {
            String operation = tracker.poll();

            try (Connection connection = ConnectionDB.getConnection()) {
                switch (operation) {
                    case "addMB":
                        if (!toBeAddedMB.isEmpty()) {
                            Map<String, Object> book = toBeAddedMB.get(0);
                            String query = "INSERT INTO books (title, author, genre, isbn, total_copies, available_copies) VALUES (?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setString(1, (String) book.get("title"));
                                stmt.setString(2, (String) book.get("author"));
                                stmt.setString(3, (String) book.get("genre"));
                                stmt.setString(4, (String) book.get("isbn"));
                                stmt.setInt(5, (int) book.get("total_copies"));
                                stmt.setInt(6, (int) book.get("available_copies"));
                                stmt.executeUpdate();
                            }
                            toBeAddedMB.remove(0); 
                        }
                        break;

                    case "delMB": 
                        if (!toBeDeletedMB.isEmpty()) {
                            Map<String, Object> book = toBeDeletedMB.get(0); 
                            String query = "DELETE FROM books WHERE book_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setInt(1, (int) book.get("book_id"));
                                stmt.executeUpdate();
                            }
                            toBeDeletedMB.remove(0);
                        }
                        break;

                    case "updateMB":
                        if (!toBeUpdatedMB.isEmpty()) {
                            Map<String, Object> book = toBeUpdatedMB.get(0);
                            String query = "UPDATE books SET title = ?, author = ?, genre = ?, isbn = ?, total_copies = ?, available_copies = ? WHERE book_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setString(1, (String) book.get("title"));
                                stmt.setString(2, (String) book.get("author"));
                                stmt.setString(3, (String) book.get("genre"));
                                stmt.setString(4, (String) book.get("isbn"));
                                stmt.setInt(5, (int) book.get("total_copies"));
                                stmt.setInt(6, (int) book.get("available_copies"));
                                stmt.setInt(7, (int) book.get("book_id"));
                                stmt.executeUpdate();
                            }
                            toBeUpdatedMB.remove(0);
                        }
                        break;

                    case "addMU":
                        if (!toBeAddedMU.isEmpty()) {
                            Map<String, Object> user = toBeAddedMU.get(0);
                            String query = "INSERT INTO users (username, email, phone, join_date) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setString(1, (String) user.get("username"));
                                stmt.setString(2, (String) user.get("email"));
                                stmt.setString(3, (String) user.get("phone"));
                                java.sql.Date joinDate = (java.sql.Date) user.get("join_date");
                                stmt.setDate(4, joinDate);
                                stmt.executeUpdate();
                            }
                            toBeAddedMU.remove(0);
                        }
                        break;

                    case "addBRB": 
                        if (!toBeAddedBRB.isEmpty()) {
                            Map<String, Object> borrow = toBeAddedBRB.get(0);
                            String query = "INSERT INTO borrow_records (users_id, book_id, borrow_date, due_date, return_date, fine) VALUES (?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setInt(1, (int) borrow.get("users_id"));
                                stmt.setInt(2, (int) borrow.get("book_id"));
                                stmt.setDate(3, (java.sql.Date) borrow.get("borrow_date"));
                                stmt.setDate(4, (java.sql.Date) borrow.get("due_date"));
                                stmt.setDate(5, (java.sql.Date) borrow.get("return_date"));
                                stmt.setDouble(6, (double) borrow.get("fine"));
                                stmt.executeUpdate();
                            }
                            toBeAddedBRB.remove(0); 
                        }
                        break;
                    
                    case "delMU": 
                        if (!toBeDeletedMU.isEmpty()) {
                            Map<String, Object> user = toBeDeletedMU.get(0);
                            String query = "DELETE FROM books WHERE book_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setInt(1, (int) user.get("users_id"));
                                stmt.executeUpdate();
                            }
                            toBeDeletedMU.remove(0); 
                        }
                        break;
                        
                    case "delBRB": 
                        if (!toBeDeletedBRB.isEmpty()) {
                            Map<String, Object> user = toBeDeletedBRB.get(0);
                            String query = "DELETE FROM borrow_records WHERE record_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setInt(1, (int) user.get("record_id"));
                                stmt.executeUpdate();
                            }
                            toBeDeletedBRB.remove(0); 
                        }
                        break;  
                        
                    case "updateMU": 
                        if (!toBeUpdatedMU.isEmpty()) { 
                            Map<String, Object> user = toBeUpdatedMU.get(0);
                            String query = "UPDATE users SET external_id = ?, username = ?, email = ?, phone = ?, join_date = ? WHERE users_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setString(1, (String) user.get("external_id"));
                                stmt.setString(2, (String) user.get("username"));
                                stmt.setString(3, (String) user.get("email"));
                                stmt.setString(4, (String) user.get("phone"));
                                stmt.setDate(5, (java.sql.Date) user.get("join_date"));
                                stmt.setInt(6, (int) user.get("users_id")); 
                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println("Error updating user: " + e.getMessage());
                                e.printStackTrace();
                            }
                            toBeUpdatedMU.remove(0);
                        }
                        break;

                    case "updateBRB":
                        if (!toBeUpdatedBRB.isEmpty()) { 
                            Map<String, Object> borrow = toBeUpdatedBRB.get(0);
                            String query = "UPDATE borrow_records SET users_id = ?, book_id = ?, borrow_date = ?, due_date = ?, return_date = ?, fine = ? WHERE record_id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setInt(1, (int) borrow.get("users_id"));
                                stmt.setInt(2, (int) borrow.get("book_id"));
                                stmt.setDate(3, (java.sql.Date) borrow.get("borrow_date"));
                                stmt.setDate(4, (java.sql.Date) borrow.get("due_date"));
                                stmt.setDate(5, (java.sql.Date) borrow.get("return_date"));
                                Object fineObj = borrow.get("fine");
                                if (fineObj instanceof BigDecimal) {
                                    stmt.setDouble(6, ((BigDecimal) fineObj).doubleValue());
                                } else if (fineObj instanceof Double) {
                                    stmt.setDouble(6, (Double) fineObj);
                                } else {
                                    stmt.setDouble(6, 0.0);
                                }
                                stmt.setInt(7, (int) borrow.get("record_id"));
                                stmt.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println("Error updating borrow record: " + e.getMessage());
                                e.printStackTrace();
                            }
                            toBeUpdatedBRB.remove(0);
                        }
                        break;
    
                        
                    default:
                        System.out.println("Unknown operation: " + operation);
                }
            } catch (SQLException e) {
                System.out.println("Error processing operation: " + operation);
                e.printStackTrace();
            }
        }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HomePage home = new HomePage();
                home.setVisible(true);
               
            }
        });
        
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BRBSearch;
    private javax.swing.JTable BRBTable;
    private javax.swing.JButton BorrowBt;
    private javax.swing.JPanel BorrowReturn;
    private javax.swing.JButton MBAddbook;
    private javax.swing.JTextField MBSearch;
    private javax.swing.JTable MBTable;
    private javax.swing.JTextField MUSearch;
    private javax.swing.JTable MUTable;
    private javax.swing.JPanel ManageBooks;
    private javax.swing.JPanel ManageUsers;
    private javax.swing.JPanel btn_A;
    private javax.swing.JPanel btn_B;
    private javax.swing.JPanel btn_Home;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
