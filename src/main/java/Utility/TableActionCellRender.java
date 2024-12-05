/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rat
 */
public class TableActionCellRender extends DefaultTableCellRenderer {
    
    public TableActionCellRender() {
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1,int row, int column ){
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, bln1, row, column);
        NewJPanel action = new NewJPanel();
        if (isSelected == false && row % 2 == 0){
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        }
        this.removeAll();
        this.add(action, BorderLayout.CENTER);
        return action;
        
    }
}
