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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    public static List<Map<String, Object>> toBeDetetedBRB = new ArrayList<>();
    
    
    List<Map<String, Object>> booksList;
    List<Map<String, Object>> usersList;
    List<Map<String, Object>> borrowsList;
    public HomePage() {
        initComponents();
        booksList = Utility.BooksToTableList();
        mapToMBTable(booksList, MBTable); // SET TABLE MB
        
        TableActionEvent event1 = new TableActionEvent(){
            public void onEdit(int row){
                System.out.println("Edit "+ row);
                Object id = Utility.getIdFromTableRow(MBTable, row);
                Map<String, Object> selectedBook = Utility.getDataFromID(id, "book_id", booksList);
                
                if (selectedBook != null) {
                    new onEditMB(HomePage.this, selectedBook).setVisible(true);
                } else {
                    System.out.println("Error: Book not found in toBeAddedMB.");
                }
                System.out.println(id);
                System.out.println(selectedBook);
            }
    
            public void onDelete(int row){
                DefaultTableModel model = (DefaultTableModel) MBTable.getModel();
                model.removeRow(row);
                toBeDeletedMB.add(booksList.get(row));
                System.out.println(toBeDeletedMB);
                booksList.remove(row);
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
                System.out.println("Edit "+ row);
            }
    
            public void onDelete(int row){
                System.out.println("Delete  "+ row);
            }
            
            public void onView(int row){
                System.out.println("View  "+ row);
            }
        };
        
        MUTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        MUTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event2));
        
        
        TableActionEvent event3 = new TableActionEvent(){
            public void onEdit(int row){
                System.out.println("Edit "+ row);
            }
    
            public void onDelete(int row){
                System.out.println("Delete  "+ row);
                DefaultTableModel model = (DefaultTableModel) MBTable.getModel();
                model.removeRow(row);
            }
            
            public void onView(int row){
                System.out.println("View  "+ row);
            }
        };
        
        BRBTable.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        BRBTable.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event3));
       
    
        /*
        Object[] row1 = {1, "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "9780743273565", 5, 3};
        Object[] row2 = {2, "1984", "George Orwell", "Dystopian", "9780451524935", 3, 3};
        Object[] row3 = {3, "To Kill a Mockingbird", "Harper Lee", "Fiction", "9780060935467", 4, 2};
        Object[] row4 = {4, "The Hobbit", "J.R.R. Tolkien", "Fantasy", "9780547928227", 2, 1};
        DefaultTableModel dt = (DefaultTableModel)MBTable.getModel();
        dt.addRow(row1);
        dt.addRow(row2);
        dt.addRow(row3);
        dt.addRow(row1);
        */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Program is exiting...");
                    System.out.println("All changes now stored in the database");
                    processPendingData();
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
        btn_C = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
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
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        BorrowReturn = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        BRBTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        Search = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

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

        btn_C.setBackground(new java.awt.Color(122, 232, 224));
        btn_C.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CMouseClicked(evt);
            }
        });

        jLabel11.setText("Search Books");

        javax.swing.GroupLayout btn_CLayout = new javax.swing.GroupLayout(btn_C);
        btn_C.setLayout(btn_CLayout);
        btn_CLayout.setHorizontalGroup(
            btn_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_CLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_CLayout.setVerticalGroup(
            btn_CLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_CLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel11)
                .addContainerGap(22, Short.MAX_VALUE))
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
            .addComponent(btn_C, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(0, 0, 0)
                .addComponent(btn_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel5.setText("Search here");
        ManageUsers.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 241, -1, -1));
        ManageUsers.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 238, 170, -1));

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
        BorrowReturn.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 238, 160, -1));

        jButton8.setText("Borrow Books");
        jButton8.setPreferredSize(new java.awt.Dimension(110, 23));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        BorrowReturn.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 238, 140, -1));

        BRBTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Borrower Name", "Book Name", "Borrow Date", "Due Date", "Return Date", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BRBTable.setRowHeight(40);
        jScrollPane5.setViewportView(BRBTable);
        if (BRBTable.getColumnModel().getColumnCount() > 0) {
            BRBTable.getColumnModel().getColumn(0).setResizable(false);
            BRBTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            BRBTable.getColumnModel().getColumn(1).setResizable(false);
            BRBTable.getColumnModel().getColumn(2).setResizable(false);
            BRBTable.getColumnModel().getColumn(3).setResizable(false);
            BRBTable.getColumnModel().getColumn(4).setResizable(false);
            BRBTable.getColumnModel().getColumn(5).setResizable(false);
            BRBTable.getColumnModel().getColumn(6).setMinWidth(135);
            BRBTable.getColumnModel().getColumn(6).setPreferredWidth(135);
            BRBTable.getColumnModel().getColumn(6).setMaxWidth(135);
        }

        BorrowReturn.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 279, 719, 503));

        jLabel6.setText("Search here");
        BorrowReturn.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, -1, -1));

        jLayeredPane1.setLayer(BorrowReturn, 2);
        jLayeredPane1.add(BorrowReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 800));

        Search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(128, 128, 128));
        jPanel6.setAlignmentX(0.0F);
        jPanel6.setAlignmentY(0.0F);
        jPanel6.setAutoscrolls(true);
        jPanel6.setFocusCycleRoot(true);
        jPanel6.setFocusTraversalPolicyProvider(true);
        jPanel6.setPreferredSize(new java.awt.Dimension(1145, 100));
        jPanel6.setVerifyInputWhenFocusTarget(false);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel12.setText("Search");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        Search.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1562, 186));

        jLayeredPane1.setLayer(Search, 1);
        jLayeredPane1.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 800));

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
        resetColor(btn_C);
        ManageBooks.setVisible(false);
        ManageUsers.setVisible(false);
        BorrowReturn.setVisible(true);
        Search.setVisible(false);
    }//GEN-LAST:event_btn_BMouseClicked

    private void btn_HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_HomeMouseClicked
        setColor(btn_Home);
        resetColor(btn_A);
        resetColor(btn_B);
        resetColor(btn_C);
        ManageBooks.setVisible(true);
        ManageUsers.setVisible(false);
        BorrowReturn.setVisible(false);
        Search.setVisible(false);
    }//GEN-LAST:event_btn_HomeMouseClicked

    private void btn_AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AMouseClicked
        setColor(btn_A);
        resetColor(btn_Home);
        resetColor(btn_B);
        resetColor(btn_C);
        ManageBooks.setVisible(false);
        ManageUsers.setVisible(true);
        BorrowReturn.setVisible(false);
        Search.setVisible(false);
    }//GEN-LAST:event_btn_AMouseClicked

    private void btn_CMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CMouseClicked
        setColor(btn_C);
        resetColor(btn_Home);
        resetColor(btn_B);
        resetColor(btn_A);
        ManageBooks.setVisible(false);
        ManageUsers.setVisible(false);
        BorrowReturn.setVisible(false);
        Search.setVisible(true);
    }//GEN-LAST:event_btn_CMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void MBAddbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MBAddbookActionPerformed
        AddBooks obj = new AddBooks(this);
        obj.show();
    }//GEN-LAST:event_MBAddbookActionPerformed

    private void MBSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MBSearchActionPerformed
        String searchQuery = MBSearch.getText().toLowerCase(); // Get search input and make it case-insensitive

        // Filter the booksList or toBeAddedMB
        List<Map<String, Object>> filteredBooks = booksList.stream()
                .filter(book -> book.values().stream()
                        .anyMatch(value -> value.toString().toLowerCase().contains(searchQuery)))
                .toList();

        // Update the table with filtered results
        mapToMBTable(filteredBooks, MBTable);
    }//GEN-LAST:event_MBSearchActionPerformed
    
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
        DefaultTableModel model = (DefaultTableModel) MBTable.getModel();
        model.setRowCount(0);
        for (Map<String, Object> book : toBeUpdatedMB) {
            for (int i = 0; i < booksList.size(); i++) {
                Map<String, Object> booktemp = booksList.get(i);
                if (booktemp.get("book_id").equals(book.get("book_id"))) {
                    booksList.remove(i);
                    break; // Exit the loop once the book is removed
                }
            }
            booksList.add(book);
        }
        mapToMBTable(booksList, MBTable);   
    }
    
    void setColor(JPanel panel){
        panel.setBackground(new Color(147,255,249));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(122,232,224));
    }
    
    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    }  
    
    private static void processPendingData() {
        System.out.println("Processing pending data...");
        for (Map<String, Object> book : toBeAddedMB) {
            System.out.println("Saving book: " + book);
            // Example: Save to database or file
        }
        System.out.println("All pending data processed.");
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
    private javax.swing.JTable BRBTable;
    private javax.swing.JPanel BorrowReturn;
    private javax.swing.JButton MBAddbook;
    private javax.swing.JTextField MBSearch;
    private javax.swing.JTable MBTable;
    private javax.swing.JTable MUTable;
    private javax.swing.JPanel ManageBooks;
    private javax.swing.JPanel ManageUsers;
    private javax.swing.JPanel Search;
    private javax.swing.JPanel btn_A;
    private javax.swing.JPanel btn_B;
    private javax.swing.JPanel btn_C;
    private javax.swing.JPanel btn_Home;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
