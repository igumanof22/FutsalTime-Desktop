/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.PembayaranController;
import Controller.PemesananController;
import Controller.PenyewaanController;
import Koneksi.koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class FormReport extends javax.swing.JFrame {
    PembayaranController bayarController;
    PenyewaanController sewaController;
    PemesananController pesanController;
    FormBooking viewSewa;
    FormPemesanan viewPesan;
    FormPembayaran viewBayar;
    int bt=0;
    String Bulan,Tahun;

    /**
     * Creates new form FormReport
     */
    public FormReport() {
        initComponents();
        sewaController = new PenyewaanController(viewSewa);
        bayarController = new PembayaranController(viewBayar);
        pesanController = new PemesananController(viewPesan);
    }
    
    private void tableSewaBulan(){
        Bulan = JOptionPane.showInputDialog(this, "Masukkan Bulan \n Contoh : 01", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pemakaian");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Kode Lapangan");
        model.addColumn("Tanggal Main");
        model.addColumn("Jam Awal");
        model.addColumn("Jam Akhir");
        model.addColumn("Total Sewa");
        model.addColumn("Pemasukkan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT penyewaan.`kdpemakaian`, penyewaan.`kdpelanggan`, " +
                        "pelanggan.`namaplg`, penyewaan.`kdlapangan`, penyewaan.`tglmain`, " +
                        "penyewaan.`jamawal`, penyewaan.`jamakhir`, penyewaan.`totalsewa`, " +
                        "penyewaan.`pemasukkan` FROM `pelanggan` pelanggan INNER JOIN `penyewaan` penyewaan "+ 
                        "ON pelanggan.`kdpelanggan` = penyewaan.`kdpelanggan` " +
                        "WHERE month(tglmain) = '"+Bulan+"' and year(tglmain) = '"+Tahun+"'";
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
            table.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tableSewaTahun(){
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pemakaian");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Kode Lapangan");
        model.addColumn("Tanggal Main");
        model.addColumn("Jam Awal");
        model.addColumn("Jam Akhir");
        model.addColumn("Total Sewa");
        model.addColumn("Pemasukkan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT penyewaan.`kdpemakaian`, penyewaan.`kdpelanggan`, " +
                        "pelanggan.`namaplg`, penyewaan.`kdlapangan`, penyewaan.`tglmain`, " +
                        "penyewaan.`jamawal`, penyewaan.`jamakhir`, penyewaan.`totalsewa`, " +
                        "penyewaan.`pemasukkan` FROM `pelanggan` pelanggan INNER JOIN `penyewaan` penyewaan "+ 
                        "ON pelanggan.`kdpelanggan` = penyewaan.`kdpelanggan` " +
                        "WHERE year(tglmain) = '"+Tahun+"'";
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
            table.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tableBayarBulan(){
        Bulan = JOptionPane.showInputDialog(this, "Masukkan Bulan \n Contoh : 01", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Transaksi");
        model.addColumn("Kode Pemakaian");
        model.addColumn("SubTotal Sewa");
        model.addColumn("ID Nota");
        model.addColumn("SubTotal Pesan");
        model.addColumn("Total Bayar");
        model.addColumn("Pemasukkan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT pembayaran.`idtransaksi`, pembayaran.`kdpemakaian`, " +
                        "penyewaan.`totalsewa`, pembayaran.`idnota`, pemesanan.`subtotalmesan`, " +
                        "pembayaran.`total`, pembayaran.`pemasukkan` FROM `pemesanan` pemesanan INNER JOIN " +
                        "`pembayaran` pembayaran ON pemesanan.`idnota` = pembayaran.`idnota` INNER JOIN " +
                        "`penyewaan` penyewaan ON pembayaran.`kdpemakaian` = penyewaan.`kdpemakaian`" +
                        "WHERE month(tglmain) = '"+Bulan+"' and year(tglmain) = '"+Tahun+"'";
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
                    rs.getString(7)
                });
            }
            table.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tableBayarTahun(){
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Transaksi");
        model.addColumn("Kode Pemakaian");
        model.addColumn("SubTotal Sewa");
        model.addColumn("ID Nota");
        model.addColumn("SubTotal Pesan");
        model.addColumn("Total Bayar");
        model.addColumn("Pemasukkan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT pembayaran.`idtransaksi`, pembayaran.`kdpemakaian`, " +
                        "penyewaan.`totalsewa`, pembayaran.`idnota`, pemesanan.`subtotalmesan`, " +
                        "pembayaran.`total`, pembayaran.`pemasukkan` FROM `pemesanan` pemesanan INNER JOIN " +
                        "`pembayaran` pembayaran ON pemesanan.`idnota` = pembayaran.`idnota` INNER JOIN " +
                        "`penyewaan` penyewaan ON pembayaran.`kdpemakaian` = penyewaan.`kdpemakaian`" +
                        "WHERE year(tglmain) = '"+Tahun+"'";
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
                    rs.getString(7)
                });
            }
            table.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tablePesanBulan(){
        Bulan = JOptionPane.showInputDialog(this, "Masukkan Bulan \n Contoh : 01", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Nota");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("ID Menu/Barang");
        model.addColumn("Nama Menu/Barang");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Sub Total Pesan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT notadetail.`idnota`, pelanggan.`kdpelanggan`, " +
                        "pelanggan.`namaplg`, notadetail.`idmbrg`, menubarang.`nama`, " +
                        "notadetail.`jumlah`, notadetail.`totalharga`, pemesanan.`subtotalmesan` " +
                        "FROM `pelanggan` pelanggan INNER JOIN `pemesanan` pemesanan ON " + 
                        "pelanggan.`kdpelanggan` = pemesanan.`kdpelanggan`\n" +
                        "INNER JOIN `notadetail` notadetail ON pemesanan.`idnota` = notadetail.`idnota`\n" +
                        "INNER JOIN `menubarang` menubarang ON notadetail.`idmbrg` = menubarang.`idmbrg`\n" +
                        "INNER JOIN `penyewaan` penyewaan ON pelanggan.`kdpelanggan` = penyewaan.`kdpelanggan` " +
                        "WHERE month(tglmain) = '"+Bulan+"' and year(tglmain) = '"+Tahun+"'";
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
                    rs.getString(8)
                });
            }
            table.setModel(model);
        } catch(Exception e){
        }
    }
    
    private void tablePesanTahun(){
        Tahun = JOptionPane.showInputDialog(this, "Masukkan Tahun \n Contoh : 2014", 
                "Laporan Penyewaan Per-Bulan", JOptionPane.QUESTION_MESSAGE);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Nota");
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("ID Menu/Barang");
        model.addColumn("Nama Menu/Barang");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Sub Total Pesan");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "SELECT notadetail.`idnota`, pelanggan.`kdpelanggan`, " +
                        "pelanggan.`namaplg`, notadetail.`idmbrg`, menubarang.`nama`, " +
                        "notadetail.`jumlah`, notadetail.`totalharga`, pemesanan.`subtotalmesan` " +
                        "FROM `pelanggan` pelanggan INNER JOIN `pemesanan` pemesanan ON " + 
                        "pelanggan.`kdpelanggan` = pemesanan.`kdpelanggan`\n" +
                        "INNER JOIN `notadetail` notadetail ON pemesanan.`idnota` = notadetail.`idnota`\n" +
                        "INNER JOIN `menubarang` menubarang ON notadetail.`idmbrg` = menubarang.`idmbrg`\n" +
                        "INNER JOIN `penyewaan` penyewaan ON pelanggan.`kdpelanggan` = penyewaan.`kdpelanggan` " +
                        "WHERE year(tglmain) = '"+Tahun+"'";
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
                    rs.getString(8)
                });
            }
            table.setModel(model);
        } catch(Exception e){
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cetak = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTahun = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jBulan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        txtPilihan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("REPORT");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 100, 170, 44);

        jLabel13.setText("-----------------------------------------");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(20, 140, 170, 14);

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

        jLabel20.setText("FUTSAL TIME");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(44, 511, 100, 14);

        jLabel21.setText("1.0");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(66, 525, 90, 14);

        cetak.setBackground(new java.awt.Color(255, 255, 255));
        cetak.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cetak");

        javax.swing.GroupLayout cetakLayout = new javax.swing.GroupLayout(cetak);
        cetak.setLayout(cetakLayout);
        cetakLayout.setHorizontalGroup(
            cetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        cetakLayout.setVerticalGroup(
            cetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(cetak);
        cetak.setBounds(780, 290, 100, 40);

        jTahun.setBackground(new java.awt.Color(255, 255, 255));
        jTahun.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTahun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTahunMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tahun");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jTahunLayout = new javax.swing.GroupLayout(jTahun);
        jTahun.setLayout(jTahunLayout);
        jTahunLayout.setHorizontalGroup(
            jTahunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTahunLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jTahunLayout.setVerticalGroup(
            jTahunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTahunLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jTahun);
        jTahun.setBounds(840, 240, 100, 40);

        jBulan.setBackground(new java.awt.Color(255, 255, 255));
        jBulan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jBulan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBulanMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Bulan");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jBulanLayout = new javax.swing.GroupLayout(jBulan);
        jBulan.setLayout(jBulanLayout);
        jBulanLayout.setHorizontalGroup(
            jBulanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBulanLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jBulanLayout.setVerticalGroup(
            jBulanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBulanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jBulan);
        jBulan.setBounds(730, 240, 100, 40);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Pilih Report Yang Mau dicetak");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(310, 120, 340, 60);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel23.setText("1. Penyewaan");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(310, 190, 170, 50);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setText("3. Pembayaran");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(310, 270, 170, 50);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel25.setText("2. Pemesanan");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(310, 230, 170, 50);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(310, 350, 810, 180);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Pilihan Report :");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(730, 190, 140, 30);
        getContentPane().add(txtPilihan);
        txtPilihan.setBounds(860, 190, 70, 30);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Login – 1.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 0, 1150, 600);

        setBounds(0, 0, 1166, 599);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        FormUtama v = new FormUtama();
        v.setVisible(true);
        v.getKet().setText("Administrator");
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        new FormMember().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        new FormBooking().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        new FormPemesanan().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        new FormPembayaran().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        new FormLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jBulanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBulanMouseClicked
        switch(txtPilihan.getText()){
            case "1":
                tableSewaBulan();
                bt = 1;
                break;
            case "2":
                tablePesanBulan();
                bt = 1;
                break;
            case "3":
                tableBayarBulan();
                bt = 1;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Pilih Report nya dulu");
                bt = 0;
                break;
        }
    }//GEN-LAST:event_jBulanMouseClicked

    private void jTahunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTahunMouseClicked
        switch(txtPilihan.getText()){
            case "1":
                tableSewaTahun();
                bt = 2;
                break;
            case "2":
                tablePesanTahun();
                bt = 2;
                break;
            case "3":
                tableBayarTahun();
                bt = 2;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Pilih Report nya dulu");
                bt = 0;
                break;
        }
    }//GEN-LAST:event_jTahunMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

    }//GEN-LAST:event_jLabel4MouseClicked

    private void cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakMouseClicked
        int x = JOptionPane.showConfirmDialog(this, "Yakin Ingin Mencetak?", "Cetak Report", JOptionPane.OK_CANCEL_OPTION);
        if (x == 0) {
            switch (bt) {
                case 1:
                    switch(txtPilihan.getText()){
                        case "1":
                            sewaController.previewReportBulanSewa(Bulan, Tahun);
                            break;
                        case "2":
                            pesanController.previewReportBulanPesan(Bulan, Tahun);
                            break;
                        case "3":
                            bayarController.previewReportBulanBayar(Bulan, Tahun);
                            break;
                        default:
                            JOptionPane.showMessageDialog(this, "Pilih Periode Report nya dulu");
                            break;
                    }   
                    break;
                case 2:
                    switch(txtPilihan.getText()){
                        case "1":
                            sewaController.previewReportTahunSewa(Tahun);
                            break;
                        case "2":
                            pesanController.previewReportTahunPesan(Tahun);
                            break;
                        case "3":
                            bayarController.previewReportTahunBayar(Tahun);
                        break;
                        default:
                            JOptionPane.showMessageDialog(this, "Pilih Periode Report nya dulu");
                            break;
                    }   
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Pilih Periode Report nya dulu");
                    break;
            }
        }
    }//GEN-LAST:event_cetakMouseClicked

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cetak;
    private javax.swing.JPanel jBulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jTahun;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtPilihan;
    // End of variables declaration//GEN-END:variables
}
