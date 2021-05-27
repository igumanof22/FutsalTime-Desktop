/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PenyewaanController;
import Koneksi.koneksi;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FormBooking extends javax.swing.JFrame {

    public JTextField getWaktuAkhir() {
        return WaktuAkhir;
    }

    public JTextField getWaktuAwal() {
        return WaktuAwal;
    }

    public JTextField getJamakhir() {
        return jamakhir;
    }

    public JTextField getJamawal() {
        return jamawal;
    }

    public JTextField getMenitakhir() {
        return menitakhir;
    }

    public JTextField getMenitawal() {
        return menitawal;
    }

    public JComboBox<String> getCbKdLapangan() {
        return cbKdLapangan;
    }

    public JComboBox<String> getCbKdPelanggan() {
        return cbKdPelanggan;
    }

    public JTable getTableLapangan() {
        return tableLapangan;
    }

    public JTable getTableSewa() {
        return tableSewa;
    }

    public JSpinner getBulan() {
        return Bulan;
    }

    public JSpinner getHari() {
        return Hari;
    }

    public JDateChooser getTanggal() {
        return Tanggal;
    }

    public JYearChooser getTahun() {
        return Tahun;
    }
    
    public JTextField getTxtDP() {
        return txtDP;
    }

    public JTextField getTxtKdPemakaian() {
        return txtKdPemakaian;
    }

    public JTextField getTxtSewa() {
        return txtSewa;
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
    
    private void tableLapangan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Lapangan");
        model.addColumn("Nama Lapangan");
        model.addColumn("Harga Siang");
        model.addColumn("Harga Malam");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from lapangan";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                });
                
                Object[] x = new Object[1];
                x[0] = rs.getString(1);
                
                cbKdLapangan.addItem(x[0].toString());
            }
            tableLapangan.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void tableCheckLapangan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Lapangan");
        model.addColumn("Nama Lapangan");
        model.addColumn("Tanggal Main");
        model.addColumn("Jam Awal");
        model.addColumn("Jam Akhir");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select kdlapangan, namalpg, tglmain, jamawal, jamakhir from lapangan join penyewaan using(kdlapangan)";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)                    
                });
            }
            tableLapangan.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void tableSewa(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pemakaian");
        model.addColumn("Kode Lapangan");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Tanggal Main");
        model.addColumn("Jam Awal");
        model.addColumn("Jam Akhir");
        model.addColumn("Uang Muka");
        model.addColumn("Bayar Sewa");
        model.addColumn("Total Sewa");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from penyewaan";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
                });
            }
            if (rs.last()) {
                kode.setText(rs.getString(1));
            }
            tableSewa.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void isiPelanggan(){
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
            System.out.println("Error => "+e);
        }
    }
    
    

    /**
     * Creates new form FormBooking
     */
    PenyewaanController sewaController;
    
    public FormBooking() {
        initComponents();
        cbKdLapangan.removeAllItems();
        cbKdPelanggan.removeAllItems();
        tableSewa();
        tableLapangan();
        isiPelanggan();
        sewaController = new PenyewaanController(this);
        unshowed();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insert = new javax.swing.JButton();
        txtKdPemakaian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbKdPelanggan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSewa = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cbKdLapangan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLapangan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtSewa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDP = new javax.swing.JTextField();
        titik1 = new javax.swing.JLabel();
        jamawal = new javax.swing.JTextField();
        menitawal = new javax.swing.JTextField();
        jamakhir = new javax.swing.JTextField();
        titik2 = new javax.swing.JLabel();
        menitakhir = new javax.swing.JTextField();
        WaktuAwal = new javax.swing.JTextField();
        WaktuAkhir = new javax.swing.JTextField();
        hapus = new javax.swing.JButton();
        Hari = new javax.swing.JSpinner();
        Bulan = new javax.swing.JSpinner();
        Tahun = new com.toedter.calendar.JYearChooser();
        Tanggal = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        kode = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cektgl = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        insert.setText("INSERT");
        insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertActionPerformed(evt);
            }
        });
        getContentPane().add(insert);
        insert.setBounds(470, 440, 80, 30);

        txtKdPemakaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKdPemakaianKeyPressed(evt);
            }
        });
        getContentPane().add(txtKdPemakaian);
        txtKdPemakaian.setBounds(220, 130, 203, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Uang Muka");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 460, 68, 17);

        cbKdPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        getContentPane().add(cbKdPelanggan);
        cbKdPelanggan.setBounds(220, 250, 203, 30);

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
        tableSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSewaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSewa);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(554, 180, 580, 197);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Jam Awal");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(220, 350, 56, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode Pemakaian");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 110, 100, 17);

        jLabel13.setText("-----------------------------------------");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(20, 140, 170, 14);

        cbKdLapangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        getContentPane().add(cbKdLapangan);
        cbKdLapangan.setBounds(220, 190, 203, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Bayar Sewa");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(220, 520, 71, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tanggal Main");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(220, 290, 79, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Jam Akhir");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(220, 410, 59, 17);

        clear.setText("CLEAR");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        getContentPane().add(clear);
        clear.setBounds(470, 510, 80, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("PENYEWAAN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 220, 44);

        tableLapangan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableLapangan);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(620, 420, 452, 91);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Daftar Penyewaan Lapangan");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(770, 160, 260, 17);

        txtSewa.setEditable(false);
        getContentPane().add(txtSewa);
        txtSewa.setBounds(300, 520, 130, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Daftar Lapangan");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(790, 400, 170, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kode Lapangan");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(220, 170, 95, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Kode Pelanggan");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(220, 230, 98, 20);

        txtDP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDPPropertyChange(evt);
            }
        });
        txtDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDPKeyPressed(evt);
            }
        });
        getContentPane().add(txtDP);
        txtDP.setBounds(220, 480, 210, 30);

        titik1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titik1.setText(":");
        getContentPane().add(titik1);
        titik1.setBounds(270, 380, 10, 14);
        getContentPane().add(jamawal);
        jamawal.setBounds(230, 370, 30, 30);
        getContentPane().add(menitawal);
        menitawal.setBounds(280, 370, 30, 30);
        getContentPane().add(jamakhir);
        jamakhir.setBounds(230, 430, 30, 30);

        titik2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titik2.setText(":");
        getContentPane().add(titik2);
        titik2.setBounds(270, 440, 10, 14);
        getContentPane().add(menitakhir);
        menitakhir.setBounds(280, 430, 30, 30);

        WaktuAwal.setEditable(false);
        getContentPane().add(WaktuAwal);
        WaktuAwal.setBounds(320, 370, 110, 30);

        WaktuAkhir.setEditable(false);
        getContentPane().add(WaktuAkhir);
        WaktuAkhir.setBounds(320, 430, 110, 30);

        hapus.setText("DELETE");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus);
        hapus.setBounds(470, 480, 80, 30);

        Hari.setModel(new javax.swing.SpinnerListModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        getContentPane().add(Hari);
        Hari.setBounds(220, 310, 50, 30);

        Bulan.setModel(new javax.swing.SpinnerListModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        getContentPane().add(Bulan);
        Bulan.setBounds(270, 310, 50, 30);
        getContentPane().add(Tahun);
        Tahun.setBounds(320, 310, 60, 30);

        Tanggal.setEnabled(false);
        getContentPane().add(Tanggal);
        Tanggal.setBounds(400, 310, 130, 30);

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
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
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

        jLabel24.setText("Kode Pemakaian Terakhir :");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(910, 540, 150, 20);
        getContentPane().add(kode);
        kode.setBounds(1060, 540, 50, 20);

        jLabel20.setText("FUTSAL TIME");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(44, 511, 100, 14);

        jLabel21.setText("1.0");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(66, 525, 90, 14);

        cektgl.setBackground(new java.awt.Color(255, 255, 255));
        cektgl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cektgl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cektglMouseClicked(evt);
            }
        });

        jLabel23.setText("Check Lapangan");

        javax.swing.GroupLayout cektglLayout = new javax.swing.GroupLayout(cektgl);
        cektgl.setLayout(cektglLayout);
        cektglLayout.setHorizontalGroup(
            cektglLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cektglLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cektglLayout.setVerticalGroup(
            cektglLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cektglLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(cektgl);
        cektgl.setBounds(1010, 70, 110, 30);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Login – 1.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 0, 1150, 600);

        a.setText("jLabel24");
        getContentPane().add(a);
        a.setBounds(0, 70, 40, 14);

        user.setText("jLabel24");
        getContentPane().add(user);
        user.setBounds(10, 40, 40, 14);

        setSize(new java.awt.Dimension(1166, 599));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDPKeyPressed
        String kode = cbKdLapangan.getSelectedItem().toString();
        int selisih = 0, hargasiang = 0, hargamalam = 0;
        
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            try{
                koneksi k = new koneksi();
                Connection con = k.getConnection();
                String sql = "Select *from lapangan where kdlapangan = '"+kode+"'";
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql);
          
                while(rs.next()){
                    hargasiang = rs.getInt(3);
                    hargamalam = rs.getInt(4);
                    }
            } catch(Exception e){
                System.out.println("Error => "+e);
            }
            if (Objects.equals(Integer.valueOf(menitawal.getText()), Integer.valueOf(menitakhir.getText()))) {
                selisih = Integer.valueOf(jamakhir.getText()) - Integer.valueOf(jamawal.getText());
            }
            int d = Integer.valueOf(txtDP.getText());
            int sewa = 0;
            if (Integer.valueOf(jamawal.getText()) >= 8 && Integer.valueOf(jamawal.getText()) <= 16){
                sewa = (selisih*hargasiang)-d;
            } else if (Integer.valueOf(jamawal.getText()) >= 17 && Integer.valueOf(jamawal.getText()) <= 23){
                sewa = (selisih*hargamalam)-d;
            }
            txtSewa.setText(String.valueOf(sewa));
        }
    }//GEN-LAST:event_txtDPKeyPressed

    private void tableSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSewaMouseClicked
        try {
            sewaController.onMouseClickTableBooking();
            showed();
        } catch (SQLException ex) {
            Logger.getLogger(FormBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableSewaMouseClicked

    private void insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertActionPerformed
        if (insert.getText().equals("INSERT")) {
            try {
                sewaController.insert();
            } catch (SQLException ex) {
                Logger.getLogger(FormBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableSewa();
        } else{
            try {
                sewaController.update();
            } catch (SQLException ex) {
                Logger.getLogger(FormBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
            unshowed();
            tableSewa();
        }
    }//GEN-LAST:event_insertActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        sewaController.delete();
        tableSewa();
        unshowed();
    }//GEN-LAST:event_hapusActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        sewaController.clear();
    }//GEN-LAST:event_clearActionPerformed

    private void txtKdPemakaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKdPemakaianKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            sewaController.getSewa();
            showed();
        }
    }//GEN-LAST:event_txtKdPemakaianKeyPressed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        FormUtama utama = new FormUtama();
        if (a.getText().equals("operator")) {
            utama.getjLabel18().setVisible(false);
            utama.getKet().setText("Operator");
            utama.getjLabel4().setText("Edit Text");
            utama.getA().setText(a.getText());
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

    private void cektglMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cektglMouseClicked
        tableCheckLapangan();
    }//GEN-LAST:event_cektglMouseClicked

    private void txtDPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDPPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDPPropertyChange

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
            java.util.logging.Logger.getLogger(FormBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JSpinner Bulan;
    private javax.swing.JSpinner Hari;
    private com.toedter.calendar.JYearChooser Tahun;
    private com.toedter.calendar.JDateChooser Tanggal;
    private javax.swing.JTextField WaktuAkhir;
    private javax.swing.JTextField WaktuAwal;
    private javax.swing.JLabel a;
    private javax.swing.JComboBox<String> cbKdLapangan;
    private javax.swing.JComboBox<String> cbKdPelanggan;
    private javax.swing.JPanel cektgl;
    private javax.swing.JButton clear;
    private javax.swing.JButton hapus;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jamakhir;
    private javax.swing.JTextField jamawal;
    private javax.swing.JLabel kode;
    private javax.swing.JTextField menitakhir;
    private javax.swing.JTextField menitawal;
    private javax.swing.JTable tableLapangan;
    private javax.swing.JTable tableSewa;
    private javax.swing.JLabel titik1;
    private javax.swing.JLabel titik2;
    private javax.swing.JTextField txtDP;
    private javax.swing.JTextField txtKdPemakaian;
    private javax.swing.JTextField txtSewa;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables

    private void showed() {
        WaktuAwal.setVisible(true);
        WaktuAkhir.setVisible(true);
        Tanggal.setVisible(true);
        hapus.setVisible(true);
        insert.setText("UPDATE");
    }
    
    private void unshowed() {
        WaktuAwal.setVisible(false);
        WaktuAkhir.setVisible(false);
        Tanggal.setVisible(false);
        hapus.setVisible(false);
        insert.setText("INSERT");
    }
}
