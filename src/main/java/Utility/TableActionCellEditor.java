/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author rat
 */
public class TableActionCellEditor extends DefaultCellEditor {
    
    private TableActionEvent event;
    public TableActionCellEditor(TableActionEvent event){
        super( new JCheckBox());
        this.event = event;
    }
    
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1){
        NewJPanel panel = new NewJPanel();
        panel.initEvent(event, i);
        panel.setBackground(jtable.getSelectionBackground());
        return panel;
    }
}
