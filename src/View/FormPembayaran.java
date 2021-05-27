/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PembayaranController;
import DAO.PemesananDAO;
import DAO.PenyewaanDAO;
import Koneksi.koneksi;
import Model.Pemesanan;
import Model.Penyewaan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FormPembayaran extends javax.swing.JFrame {

    public JComboBox<String> getCbIdNota() {
        return cbIdNota;
    }

    public JComboBox<String> getCbKdPemakaian() {
        return cbKdPemakaian;
    }

    public JTable getTableBayar() {
        return tableBayar;
    }

    public JTable getTablePesan() {
        return tablePesan;
    }

    public JTable getTableSewa() {
        return tableSewa;
    }

    public JTextField getTxtIdTrans() {
        return txtIdTrans;
    }

    public JTextField getTxtSubTotalPesan() {
        return txtSubTotalPesan;
    }

    public JTextField getTxtSubTotalSewa() {
        return txtSubTotalSewa;
    }

    public JTextField getTxtTotalBayar() {
        return txtTotalBayar;
    }

    public JLabel getjLabel18() {
        return jLabel18;
    }

    public JLabel getA() {
        return a;
    }

    public JLabel getUser() {
        return user;
    }
    

    /**
     * Creates new form FormPembayaran
     */
    
    private void tablePesan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Nota");
        model.addColumn("Kode Pelanggan");
        model.addColumn("SubTotal Pesan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from pemesanan";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                });
                
                Object[] x = new Object[1];
                x[0] = rs.getString(1);
                
                cbIdNota.addItem(x[0].toString());
            }
            tablePesan.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void tableSewa(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pemakaian");
        model.addColumn("Kode Pelanggan");
        model.addColumn("SubTotal Sewa");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from penyewaan";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(3),
                    rs.getString(8)
                });
                
                Object[] x = new Object[1];
                x[0] = rs.getString(1);
                
                cbKdPemakaian.addItem((String) x[0]);
            }
            tableSewa.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void tableBayar(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Kode Pemakaian");
        model.addColumn("Id Nota");
        model.addColumn("Total Bayar");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from pembayaran";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)                    
                });
            }
            if (rs.last()) {
                idtrans.setText(rs.getString(1));
            }
            tableBayar.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    PembayaranController bayarController;
    PemesananDAO pesanDAO;
    Pemesanan pesan;
    PenyewaanDAO sewaDAO;
    Penyewaan sewa;
    
    public FormPembayaran() {
        initComponents();
        cbIdNota.removeAllItems();
        cbKdPemakaian.removeAllItems();
        tableBayar();
        tablePesan();
        tableSewa();
        bayarController = new PembayaranController(this);
        bayarController.clear();
        pesanDAO = new PemesananDAO();
        sewaDAO = new PenyewaanDAO();
        delete.setVisible(false);
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
        jLabel3 = new javax.swing.JLabel();
        txtIdTrans = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbIdNota = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbKdPemakaian = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtSubTotalSewa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSubTotalPesan = new javax.swing.JTextField();
        insert = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePesan = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSewa = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableBayar = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalBayar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        idtrans = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel3.setText("ID Transaksi");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(270, 130, 74, 17);
        jPanel1.add(txtIdTrans);
        txtIdTrans.setBounds(270, 150, 160, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ID Nota");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(270, 280, 47, 17);

        cbIdNota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbIdNotaItemStateChanged(evt);
            }
        });
        cbIdNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbIdNotaMouseClicked(evt);
            }
        });
        jPanel1.add(cbIdNota);
        cbIdNota.setBounds(270, 300, 160, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Kode Pemakaian");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(270, 180, 100, 17);

        cbKdPemakaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbKdPemakaianMouseClicked(evt);
            }
        });
        cbKdPemakaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKdPemakaianActionPerformed(evt);
            }
        });
        jPanel1.add(cbKdPemakaian);
        cbKdPemakaian.setBounds(270, 200, 160, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Sub Total Sewa");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(270, 230, 94, 17);

        txtSubTotalSewa.setEditable(false);
        jPanel1.add(txtSubTotalSewa);
        txtSubTotalSewa.setBounds(270, 250, 160, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Sub Total Pesan");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(270, 330, 98, 17);

        txtSubTotalPesan.setEditable(false);
        jPanel1.add(txtSubTotalPesan);
        txtSubTotalPesan.setBounds(270, 360, 160, 30);

        insert.setText("INSERT");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });
        jPanel1.add(insert);
        insert.setBounds(270, 480, 80, 23);

        hapus.setText("CLEAR");
        jPanel1.add(hapus);
        hapus.setBounds(350, 480, 80, 23);

        tablePesan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablePesan);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(460, 170, 317, 129);

        tableSewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableSewa);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(810, 170, 317, 129);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Daftar Penyewaan");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(910, 140, 112, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Daftar Pemesanan");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(550, 140, 112, 17);

        tableBayar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBayarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableBayar);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(540, 350, 494, 151);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Daftar Pembayaran");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(740, 330, 130, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Total Bayar");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(270, 390, 69, 17);
        jPanel1.add(txtTotalBayar);
        txtTotalBayar.setBounds(270, 410, 160, 30);
        jPanel1.add(jLabel10);
        jLabel10.setBounds(0, 0, 23, 25);

        jLabel13.setText("-----------------------------------------");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 140, 170, 14);

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel1.add(delete);
        delete.setBounds(300, 510, 90, 23);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("PEMBAYARAN");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 100, 220, 44);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Home");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14);
        jLabel14.setBounds(20, 160, 70, 29);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("Pelanggan");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel23);
        jLabel23.setBounds(20, 200, 109, 29);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Penyewaan");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 250, 121, 29);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("Pemesanan");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel16);
        jLabel16.setBounds(20, 300, 132, 29);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Pembayaran");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 350, 132, 29);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Report");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel18);
        jLabel18.setBounds(20, 390, 71, 29);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Keluar");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel19);
        jLabel19.setBounds(20, 440, 67, 29);

        jLabel12.setText("ID Transaksi Terakhir :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(920, 540, 140, 20);
        jPanel1.add(idtrans);
        idtrans.setBounds(1060, 540, 50, 20);

        jLabel20.setText("FUTSAL TIME");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(44, 511, 100, 14);

        jLabel21.setText("1.0");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(66, 525, 90, 14);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Pembayaran – 1.png"))); // NOI18N
        jPanel1.add(jLabel22);
        jLabel22.setBounds(0, 0, 1152, 600);

        a.setText("jLabel12");
        jPanel1.add(a);
        a.setBounds(20, 50, 40, 14);

        user.setText("jLabel12");
        jPanel1.add(user);
        user.setBounds(40, 10, 40, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1166, 599));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbIdNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbIdNotaMouseClicked
        String id = cbIdNota.getSelectedItem().toString();
        try {
            pesan = new Pemesanan();
            pesan = pesanDAO.getPesan(id);
            
            txtSubTotalPesan.setText(String.valueOf(pesan.subtotalmesan));
        } catch (SQLException ex) {
            Logger.getLogger(FormPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int a = Integer.valueOf(txtSubTotalPesan.getText());
        int b = Integer.valueOf(txtSubTotalSewa.getText());
        int total = a + b;
        txtTotalBayar.setText(String.valueOf(total));
    }//GEN-LAST:event_cbIdNotaMouseClicked

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        if (insert.getText().equals("INSERT")) {
            try {
                bayarController.insert();
            } catch (SQLException ex) {
                Logger.getLogger(FormPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableBayar();
        } else{
            try {
                bayarController.update();
                insert.setText("INSERT");
                delete.setVisible(false);
                txtIdTrans.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(FormPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableBayar();
            delete.setVisible(false);
        }
    }//GEN-LAST:event_insertActionPerformed

    private void cbIdNotaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbIdNotaItemStateChanged
        
    }//GEN-LAST:event_cbIdNotaItemStateChanged

    private void cbKdPemakaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKdPemakaianActionPerformed
        
    }//GEN-LAST:event_cbKdPemakaianActionPerformed

    private void cbKdPemakaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbKdPemakaianMouseClicked
        String id = cbKdPemakaian.getSelectedItem().toString();
        try {
            sewa = new Penyewaan();
            sewa = sewaDAO.getSewa(id);
            
            txtSubTotalSewa.setText(String.valueOf(sewa.bayasewa));
        } catch (SQLException ex) {
            Logger.getLogger(FormPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbKdPemakaianMouseClicked

    private void tableBayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBayarMouseClicked
        try {
            bayarController.onMouseClickTableBayar();
            insert.setText("UPDATE");
            delete.setVisible(true);
            txtIdTrans.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(FormPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableBayarMouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        FormUtama utama = new FormUtama();
        if (a.getText().equals("operator")) {
            utama.getjLabel18().setVisible(false);
            utama.getA().setText(a.getText());
            utama.getKet().setText("Operator");
            utama.getjLabel4().setText("Edit Text");
            utama.getUser().setText(user.getText());
            utama.getUser().setEnabled(false);
        } else{
            utama.getKet().setText("Administrator");
        }
        utama.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        FormMember member = new FormMember();
        if (a.getText().equals("operator")) {
            member.getjLabel18().setVisible(false);
            member.getA().setText(a.getText());
        }
        member.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        FormBooking sewa = new FormBooking();
        if (a.getText().equals("operator")) {
            sewa.getjLabel18().setVisible(false);
            sewa.getA().setText(a.getText());
        }
        sewa.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        FormPemesanan pesan = new FormPemesanan();
        if (a.getText().equals("operator")) {
            pesan.getjLabel18().setVisible(false);
            pesan.getA().setText(a.getText());
            pesan.getTambahbrg().setVisible(false);
        }
        pesan.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        new FormLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        new FormReport().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        bayarController.delete();
        insert.setText("INSERT");
        delete.setVisible(false);
        tableBayar();
    }//GEN-LAST:event_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JComboBox<String> cbIdNota;
    private javax.swing.JComboBox<String> cbKdPemakaian;
    private javax.swing.JButton delete;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel idtrans;
    private javax.swing.JButton insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableBayar;
    private javax.swing.JTable tablePesan;
    private javax.swing.JTable tableSewa;
    private javax.swing.JTextField txtIdTrans;
    private javax.swing.JTextField txtSubTotalPesan;
    private javax.swing.JTextField txtSubTotalSewa;
    private javax.swing.JTextField txtTotalBayar;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
