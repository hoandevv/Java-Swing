/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.HocVienController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.HocVien;

/**
 *
 * @author Hoan
 */
public class HocVienView extends JFrame {
    private HocVienController controller;
    
    private JPanel panelForm;
    private JPanel panelTable;
    
    private JTextField txtMaHV, txtTenHV, txtLop, txtDiem;
    
    private JTable table ;
    private DefaultTableModel tableModel;

    public HocVienView(){
        controller = new HocVienController ();
        initMenu();
        initPanels();
        
        setTitle("Quản lý học viên");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Chức năng");
        
        JMenuItem mnuThem = new JMenuItem("Thêm học viên");
        JMenuItem mnuSua = new JMenuItem("Sửa thông tin học viên");

        menu.add(mnuThem);
        menu.add(mnuSua);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
         // ===== MENU EVENT =====
        mnuThem.addActionListener(e -> {
            controller.chonchedoTHEM();
            clearForm();
            txtMaHV.setEditable(true);
            ShowFormOnly();
            enableTableSelection(false);
        });

        mnuSua.addActionListener(e -> {
            controller.chonchedoSUA();
            txtMaHV.setEditable(false);
            showFormAndTable();
            loadTable();
            enableTableSelection(true);
        });
    }
    private void initPanels(){
        panelForm = new JPanel(new GridLayout(5,2,5,5));
        txtMaHV = new JTextField();
        txtTenHV = new JTextField();
        txtLop = new JTextField();
        txtDiem = new JTextField();

        panelForm.add(new JLabel("Mã học viên"));
        panelForm.add(txtMaHV);
        panelForm.add(new JLabel("Tên học viên"));
        panelForm.add(txtTenHV);
        panelForm.add(new JLabel("Lớp"));
        panelForm.add(txtLop);
        panelForm.add(new JLabel("Điểm TB"));
        panelForm.add(txtDiem);

        JButton btnLuu = new JButton("Lưu");
        
        panelTable = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(
                new Object[]{"Mã HV", "Tên HV", "Lớp", "Điểm TB"}, 0
        );
        table = new JTable(tableModel);
        panelTable.add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== TABLE CLICK =====
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) return;

            txtMaHV.setText(tableModel.getValueAt(row, 0).toString());
            txtTenHV.setText(tableModel.getValueAt(row, 1).toString());
            txtLop.setText(tableModel.getValueAt(row, 2).toString());
            txtDiem.setText(tableModel.getValueAt(row, 3).toString());
        });

        // ===== SAVE BUTTON =====
            btnLuu.addActionListener(e -> {
         try {
             controller.luu(
                 txtMaHV.getText(),
                 txtTenHV.getText(),
                 txtLop.getText(),
                 Double.parseDouble(txtDiem.getText())
             );

             loadTable();          
             showFormAndTable();  
             clearForm();
             txtMaHV.setEditable(true);

         } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage());
         }
     });
        panelForm.add(btnLuu);
    }
      private void ShowFormOnly() {
        getContentPane().removeAll();
        getContentPane().add(panelForm, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    
     private void showFormAndTable() {
        getContentPane().removeAll();
        getContentPane().add(panelForm, BorderLayout.NORTH);
        getContentPane().add(panelTable, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    private void loadTable(){
        try {
            List<HocVien> list = controller.layDanhSach();
            tableModel.setRowCount(0);
            
              for (HocVien hv : list) {
                tableModel.addRow(new Object[]{
                        hv.getMaHV(),
                        hv.getTenHocVien(),
                        hv.getLop(),
                        hv.getDiemTB()
                });
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }
    private void enableTableSelection(boolean enable) {
    table.setRowSelectionAllowed(enable);
    table.setColumnSelectionAllowed(false);
    table.setEnabled(enable);
}

    
     private void clearForm() {
        txtMaHV.setText("");
        txtTenHV.setText("");
        txtLop.setText("");
        txtDiem.setText("");
    }
    
}  