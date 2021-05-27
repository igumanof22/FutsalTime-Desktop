/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MenuBarangController;
import Controller.NotaDetailController;
import Controller.PemesananController;
import Koneksi.koneksi;
import java.awt.event.KeyEvent;
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
public class FormPemesanan extends javax.swing.JFrame {

    public JComboBox<String> getCbKdMenu() {
        return cbKdMenu;
    }

    public JComboBox<String> getCbKdPelanggan() {
        return cbKdPelanggan;
    }

    public JTable getTableMenuPesan() {
        return tableMenuPesan;
    }

    public JTable getTableDetail() {
        return tableDetail;
    }

    public JTextField getTxtIdNota() {
        return txtIdNota;
    }

    public JTextField getTxtJumlah() {
        return txtJumlah;
    }

    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public JComboBox<String> getCbIdNota() {
        return cbIdNota;
    }

    public JTextField getTxtSubTotalPesan() {
        return txtSubTotalPesan;
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

    public JLabel getTambahbrg() {
        return tambahbrg;
    }
    
    private void awal(){
        cbIdNota.setVisible(false);
        cbKdPelanggan.setVisible(false);
        txtSubTotalPesan.setVisible(false);
        Id.setVisible(false);
        kode.setVisible(false);
        subtotal.setVisible(false);
        pesan.setVisible(false);
        delete.setVisible(false);
        
    
        idd.setVisible(true);
        idd.setText("Id Nota");
        kdm.setVisible(true);
        j.setVisible(true);
        j.setText("Jumlah");
        t.setVisible(true);
        t.setText("SubTotal");
        jLabel1.setText("PEMESANAN");
        txtIdNota.setVisible(true);
        cbKdMenu.setVisible(true);
        txtJumlah.setVisible(true);
        txtSubTotal.setVisible(true);
        txtSubTotal.setEnabled(false);
        change.setVisible(true);
        
        x=1;
    }
    
    private void akhir(){
        idd.setVisible(false);
        kdm.setVisible(false);
        j.setVisible(false);
        t.setVisible(false);
        txtIdNota.setVisible(false);
        cbKdMenu.setVisible(false);
        txtJumlah.setVisible(false);
        txtSubTotal.setVisible(false);
        change.setVisible(false);
        delete.setVisible(false);
        
        cbIdNota.setVisible(true);
        cbKdPelanggan.setVisible(true);
        txtSubTotalPesan.setVisible(true);
        Id.setVisible(true);
        kode.setVisible(true);
        subtotal.setVisible(true);
        pesan.setVisible(true);
        
        x=2;
    }
    
    private void tambahbrg(){
        cbIdNota.setVisible(false);
        cbKdPelanggan.setVisible(false);
        txtSubTotalPesan.setVisible(false);
        Id.setVisible(false);
        kode.setVisible(false);
        subtotal.setVisible(false);
        pesan.setVisible(false);
        delete.setVisible(false);
        
    
        idd.setVisible(true);
        idd.setText("Kode Menu/Barang");
        kdm.setVisible(false);
        j.setVisible(true);
        j.setText("Nama");
        t.setVisible(true);
        t.setText("Harga");
        jLabel1.setText("MENU & BARANG");
        txtIdNota.setVisible(true);
        cbKdMenu.setVisible(false);
        txtJumlah.setVisible(true);
        txtSubTotal.setVisible(true);
        txtSubTotal.setEnabled(true);
        change.setVisible(true);
        
        x=3;
    }
    
    private void isiPelanggan(){
        cbKdPelanggan.removeAllItems();
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from pelanggan";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Object[] x = new Object[1];
                x[0] = rs.getString(1);
                
                cbKdPelanggan.addItem(x[0].toString());
            }
        } catch(Exception e){
        }
    }
    
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
            }
            tableMenuPesan.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tableDetail(){
        cbIdNota.removeAllItems();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Nota");
        model.addColumn("Kode Menu/Barang");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        
        try{            
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from notadetail";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Object[] z = new Object[4];
            z[0]="";
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                });
                
                if (z[0].equals(rs.getString(1))) {
                    z[0]=rs.getString(1);
                }else{
                    cbIdNota.addItem(rs.getString(1));
                    z[0]=rs.getString(1);
                }                                
            }
            if (rs.last()){
                nonota.setText(rs.getString(1));
            }
            tableDetail.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tableMenu(){
        cbKdMenu.removeAllItems();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Menu/Barang");
        model.addColumn("Nama");
        model.addColumn("Harga");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from menubarang";
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
                
                cbKdMenu.addItem(x[0].toString());
            }
            tableMenuPesan.setModel(model);
        } catch(Exception e){
        }
    }
    
    /**
     * Creates new form FormPemesanan
     */
    PemesananController pesanController;
    NotaDetailController notaController;
    MenuBarangController mnbrgController;
    int x;
    boolean y;
    
    public FormPemesanan() {
        initComponents();
        awal();
        tableDetail();
        tableMenu();
        pesanController = new PemesananController(this);
        pesanController.clear();
        notaController = new NotaDetailController(this);
        notaController.clear();
        mnbrgController = new MenuBarangController(this);
        mnbrgController.clear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idd = new javax.swing.JLabel();
        txtIdNota = new javax.swing.JTextField();
        kdm = new javax.swing.JLabel();
        j = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        t = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMenuPesan = new javax.swing.JTable();
        judulMenuPesan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetail = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        kode = new javax.swing.JLabel();
        cbKdMenu = new javax.swing.JComboBox<>();
        cbKdPelanggan = new javax.swing.JComboBox<>();
        Id = new javax.swing.JLabel();
        cbIdNota = new javax.swing.JComboBox<>();
        submit = new javax.swing.JButton();
        subtotal = new javax.swing.JLabel();
        change = new javax.swing.JLabel();
        txtSubTotalPesan = new javax.swing.JTextField();
        pesan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        nonota = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tambahbrg = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1150, 600));
        setSize(new java.awt.Dimension(1150, 560));
        getContentPane().setLayout(null);

        jLabel13.setText("-----------------------------------------");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(20, 140, 170, 14);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("PEMESANAN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 280, 44);

        idd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idd.setText("ID Nota");
        getContentPane().add(idd);
        idd.setBounds(320, 130, 160, 22);
        getContentPane().add(txtIdNota);
        txtIdNota.setBounds(320, 150, 160, 30);

        kdm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kdm.setText("Kode Menu / Barang");
        getContentPane().add(kdm);
        kdm.setBounds(320, 190, 163, 22);

        j.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        j.setText("Jumlah");
        getContentPane().add(j);
        j.setBounds(320, 250, 56, 22);

        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahKeyPressed(evt);
            }
        });
        getContentPane().add(txtJumlah);
        txtJumlah.setBounds(320, 270, 160, 30);

        t.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        t.setText("SubTotal");
        getContentPane().add(t);
        t.setBounds(320, 310, 70, 22);

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });
        getContentPane().add(txtSubTotal);
        txtSubTotal.setBounds(320, 330, 160, 30);

        jButton4.setText("CLEAR");
        getContentPane().add(jButton4);
        jButton4.setBounds(330, 420, 80, 30);

        tableMenuPesan.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMenuPesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMenuPesanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableMenuPesan);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(690, 350, 392, 137);

        judulMenuPesan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        judulMenuPesan.setText("Daftar Menu & Barang");
        getContentPane().add(judulMenuPesan);
        judulMenuPesan.setBounds(810, 320, 135, 17);

        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDetail);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(662, 180, 450, 129);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Daftar Detail Pemesanan");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(800, 140, 149, 17);

        kode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kode.setText("Kode Pelanggan");
        getContentPane().add(kode);
        kode.setBounds(320, 190, 127, 22);

        cbKdMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        getContentPane().add(cbKdMenu);
        cbKdMenu.setBounds(320, 210, 160, 30);

        cbKdPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        getContentPane().add(cbKdPelanggan);
        cbKdPelanggan.setBounds(320, 210, 160, 30);

        Id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Id.setText("ID Nota");
        getContentPane().add(Id);
        Id.setBounds(320, 130, 62, 22);

        cbIdNota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cbIdNota.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IdNotaChange(evt);
            }
        });
        getContentPane().add(cbIdNota);
        cbIdNota.setBounds(320, 150, 160, 30);

        submit.setText("INSERT");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });
        getContentPane().add(submit);
        submit.setBounds(330, 380, 80, 30);

        subtotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        subtotal.setText("SubTotal Pesan");
        getContentPane().add(subtotal);
        subtotal.setBounds(320, 250, 122, 22);

        change.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        change.setText("Untuk Siapa?");
        change.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeMouseClicked(evt);
            }
        });
        getContentPane().add(change);
        change.setBounds(370, 470, 74, 14);

        txtSubTotalPesan.setEditable(false);
        txtSubTotalPesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalPesanActionPerformed(evt);
            }
        });
        getContentPane().add(txtSubTotalPesan);
        txtSubTotalPesan.setBounds(320, 270, 160, 30);

        pesan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pesan.setText("Pesan?");
        pesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pesanMouseClicked(evt);
            }
        });
        getContentPane().add(pesan);
        pesan.setBounds(390, 470, 40, 14);

        jLabel3.setText("ID Nota Terakhir :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(950, 540, 110, 20);

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete);
        delete.setBounds(430, 400, 80, 30);
        getContentPane().add(nonota);
        nonota.setBounds(1060, 540, 50, 20);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Home");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14);
        jLabel14.setBounds(20, 160, 70, 29);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Pelanggan");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel22);
        jLabel22.setBounds(20, 200, 109, 29);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Penyewaan");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel15);
        jLabel15.setBounds(20, 250, 121, 29);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setText("Pemesanan");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 300, 132, 29);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("Pembayaran");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel17);
        jLabel17.setBounds(20, 350, 132, 29);

        tambahbrg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tambahbrg.setText("Tambah Menu & Barang");
        tambahbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahbrgMouseClicked(evt);
            }
        });
        getContentPane().add(tambahbrg);
        tambahbrg.setBounds(910, 490, 170, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel18.setText("Report");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel18);
        jLabel18.setBounds(20, 390, 71, 29);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel19.setText("Keluar");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel19);
        jLabel19.setBounds(20, 440, 67, 29);

        jLabel20.setText("FUTSAL TIME");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(44, 511, 100, 14);

        jLabel21.setText("1.0");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(66, 525, 90, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Pembelian – 1.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1150, 600);

        a.setText("jLabel3");
        getContentPane().add(a);
        a.setBounds(10, 50, 34, 14);

        user.setText("jLabel3");
        getContentPane().add(user);
        user.setBounds(60, 0, 34, 14);

        setSize(new java.awt.Dimension(1166, 599));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseClicked
        akhir();
        isiPelanggan();
        tablePesan();
        judulMenuPesan.setText("Daftar Pemesanan");
        submit.setText("INSERT");
    }//GEN-LAST:event_changeMouseClicked

    private void txtSubTotalPesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalPesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalPesanActionPerformed

    private void pesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesanMouseClicked
        awal();
        tableMenu();
        judulMenuPesan.setText("Daftar Menu & Barang");
        submit.setText("INSERT");
    }//GEN-LAST:event_pesanMouseClicked

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        if (submit.getText().equals("INSERT")) {
            switch (x) {
                case 1:
                    notaController.insert();
                    tableDetail();
                    awal();
                    break;
                case 2:
                    try {
                        pesanController.insert();
                        tablePesan();
                        awal();
                    } catch (SQLException ex) {
                        Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
                    }   break;
                case 3:
                    mnbrgController.insert();
                    tableMenu();
                    awal();
                    break;
                default:
                    break;
            }
        } else{
            switch (x) {
                case 1:
                    notaController.update();
                    tableDetail();
                    awal();
                    submit.setText("INSERT");
                    break;
                case 2:
                    pesanController.update();
                    tablePesan();
                    awal();
                    cbIdNota.setEnabled(true);
                    submit.setText("INSERT");
                    break;
                case 3:
                    mnbrgController.update();
                    tableMenu();
                    awal();
                    submit.setText("INSERT");
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_submitActionPerformed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed
        int z = 0;
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            int jum = Integer.parseInt(txtJumlah.getText());
            try{
                koneksi k = new koneksi();
                Connection con = k.getConnection();
                String sql = "Select *from menubarang where idmbrg='"+cbKdMenu.getSelectedItem().toString()+"'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
            
                while(rs.next()){
                    rs.getString(1);
                    rs.getString(2);
                    rs.getString(3);
                    
                    z = rs.getInt(3)*jum;
                }
                txtSubTotal.setText(String.valueOf(z));
            } catch(Exception e){
            }
        }
    }//GEN-LAST:event_txtJumlahKeyPressed

    private void IdNotaChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IdNotaChange
        int z = 0;
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from notadetail where idnota='"+cbIdNota.getSelectedItem().toString()+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                rs.getString(1);
                rs.getString(2);
                rs.getString(3);
                rs.getString(4);
                    
                z = z+rs.getInt(4);
                
            }
            txtSubTotalPesan.setText(String.valueOf(z));
        } catch(Exception e){
        }
    }//GEN-LAST:event_IdNotaChange

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked
        try {
            awal();
            notaController.onMouseClickTableDetail();
            submit.setText("UPDATE");
            delete.setVisible(true);
            y = true;
        } catch (SQLException ex) {
            Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableDetailMouseClicked

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

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        FormMember member = new FormMember();
        if (a.getText().equals("operator")) {
            member.getjLabel18().setVisible(false);
            member.getA().setText(a.getText());
        }
        member.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        FormBooking sewa = new FormBooking();
        if (a.getText().equals("operator")) {
            sewa.getjLabel18().setVisible(false);
            sewa.getA().setText(a.getText());
        }
        sewa.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        FormPembayaran bayar = new FormPembayaran();
        if (a.getText().equals("operator")) {
            bayar.getjLabel18().setVisible(false);
            bayar.getA().setText(a.getText());
        }
        bayar.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        new FormLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        new FormReport().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (y == true) {  
            notaController.delete();
            tableDetail();
            awal();
        } else{
            if (judulMenuPesan.getText().equalsIgnoreCase("Daftar Pemesanan")) {
                pesanController.delete();
                tablePesan();
                awal();
            } else if (judulMenuPesan.getText().equalsIgnoreCase("Daftar Menu & Barang")){
                mnbrgController.delete();
                tableMenu();
                awal();
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void tableMenuPesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMenuPesanMouseClicked
        if (judulMenuPesan.getText().equalsIgnoreCase("Daftar Pemesanan")) {
            try {
                akhir();
                pesanController.onMouseClickTableMenuPesan();
                delete.setVisible(true);
                cbIdNota.setEnabled(false);
                submit.setText("UPDATE");
            } catch (SQLException ex) {
                Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (judulMenuPesan.getText().equalsIgnoreCase("Daftar Menu & Barang")){
            try {
                tambahbrg();
                mnbrgController.onMouseClickTableMenuBarang();
                delete.setVisible(true);
                submit.setText("UPDATE");
            } catch (SQLException ex) {
                Logger.getLogger(FormPemesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        y = false;
    }//GEN-LAST:event_tableMenuPesanMouseClicked

    private void tambahbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbrgMouseClicked
        tambahbrg();
    }//GEN-LAST:event_tambahbrgMouseClicked

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

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
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPemesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel Id;
    private javax.swing.JLabel a;
    private javax.swing.JComboBox<String> cbIdNota;
    private javax.swing.JComboBox<String> cbKdMenu;
    private javax.swing.JComboBox<String> cbKdPelanggan;
    private javax.swing.JLabel change;
    private javax.swing.JButton delete;
    private javax.swing.JLabel idd;
    private javax.swing.JLabel j;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel judulMenuPesan;
    private javax.swing.JLabel kdm;
    private javax.swing.JLabel kode;
    private javax.swing.JLabel nonota;
    private javax.swing.JLabel pesan;
    private javax.swing.JButton submit;
    private javax.swing.JLabel subtotal;
    private javax.swing.JLabel t;
    private javax.swing.JTable tableDetail;
    private javax.swing.JTable tableMenuPesan;
    private javax.swing.JLabel tambahbrg;
    private javax.swing.JTextField txtIdNota;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtSubTotalPesan;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
