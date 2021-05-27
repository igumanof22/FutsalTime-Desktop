/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MemberController;
import Controller.PelangganController;
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
public class FormMember extends javax.swing.JFrame {

    public JComboBox<String> getCbIdMember() {
        return cbIdMember;
    }
    
    public JTable getTableMember() {
        return tableMember;
    }
    
    public JTable getTablePelanggan() {
        return tablePelanggan;
    }
    
    public JTextField getTxtIdTelp() {
        return txtIdTelp;
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNama() {
        return txtNama;
    }

    public JTextField getTxtTelpAlamat() {
        return txtTelpAlamat;
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
    
    private void tablePelanggan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pelanggan");
        model.addColumn("Nama Pelanggan");
        model.addColumn("Id Member");
        model.addColumn("No. Telp");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from pelanggan";
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
                kode.setText(rs.getString(1));
            }
            tablePelanggan.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    private void tableMember(){
        cbIdMember.removeAllItems();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Member");
        model.addColumn("Nama Member");
        model.addColumn("No. Telp");
        model.addColumn("Alamat");
        
        try{
            koneksi k = new koneksi();
            Connection con = k.getConnection();
            String sql = "Select *from member";
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
                
                cbIdMember.addItem(x[0].toString());
            }
            tableMember.setModel(model);
        } catch(Exception e){
            System.out.println("Error => "+e);
        }
    }
    
    /**
     * Creates new form FormMember
     */
    PelangganController pelanggan;
    MemberController member;
    boolean x;
    
    public FormMember() {
        initComponents();
        tablePelanggan();
        tableMember();
        pelanggan = new PelangganController(this);
        member = new MemberController(this);
        jButton2.setVisible(false);
        change2plg();
    }
    
    private void change2member(){
        kodeid.setText("Id Member");
        nama.setText("Nama Member");
        idtelp.setText("No. Telp");
        telpalamat.setText("Alamat");
        jLabel1.setText("MEMBER");
        cbIdMember.setVisible(false);
        txtIdTelp.setVisible(true);
        plg.setVisible(true);
        change.setVisible(false);
        jButton1.setText("INSERT");
        x = true;
    }
    
    private void change2plg(){
        kodeid.setText("Kode Pelanggan");
        nama.setText("Nama Pelanggan");
        idtelp.setText("Id Member");
        telpalamat.setText("No. Telpon");
        jLabel1.setText("PELANGGAN");
        cbIdMember.setVisible(true);
        txtIdTelp.setVisible(false);
        plg.setVisible(false);
        change.setVisible(true);
        jButton1.setText("INSERT");
        x = false;
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
        kodeid = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        nama = new javax.swing.JLabel();
        change = new javax.swing.JLabel();
        idtelp = new javax.swing.JLabel();
        cbIdMember = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMember = new javax.swing.JTable();
        telpalamat = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTelpAlamat = new javax.swing.JTextField();
        txtIdTelp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePelanggan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        plg = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kode = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        kodeid.setText("Kode Pelanggan");
        kodeid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(kodeid);
        kodeid.setBounds(262, 153, 127, 22);

        jButton4.setText("CLEAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(365, 447, 80, 30);

        nama.setText("Nama Pelanggan");
        nama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(nama);
        nama.setBounds(262, 212, 132, 22);

        change.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        change.setText("Daftar Member?");
        change.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeMouseClicked(evt);
            }
        });
        jPanel1.add(change);
        change.setBounds(300, 530, 92, 14);

        idtelp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idtelp.setText("Id Member");
        jPanel1.add(idtelp);
        idtelp.setBounds(262, 271, 86, 22);

        jPanel1.add(cbIdMember);
        cbIdMember.setBounds(260, 300, 160, 30);

        tableMember.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMemberMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableMember);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(581, 349, 392, 137);

        telpalamat.setText("No. Telpon");
        telpalamat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(telpalamat);
        telpalamat.setBounds(262, 330, 87, 22);

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
        });
        jPanel1.add(txtId);
        txtId.setBounds(262, 181, 160, 30);

        jLabel7.setText("Daftar Pelanggan");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(704, 127, 105, 17);
        jPanel1.add(txtNama);
        txtNama.setBounds(262, 240, 160, 30);

        jLabel8.setText("Daftar Member");
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(721, 313, 91, 17);
        jPanel1.add(txtTelpAlamat);
        txtTelpAlamat.setBounds(262, 358, 160, 30);

        txtIdTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTelpActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdTelp);
        txtIdTelp.setBounds(262, 299, 160, 30);

        tablePelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePelanggan);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(581, 155, 392, 129);

        jButton1.setText("INSERT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(262, 447, 80, 30);

        plg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        plg.setText("Input Pelanggan");
        plg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plgMouseClicked(evt);
            }
        });
        jPanel1.add(plg);
        plg.setBounds(300, 530, 93, 14);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setText("PELANGGAN");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 100, 220, 44);

        jLabel13.setText("-----------------------------------------");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 140, 170, 14);

        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(310, 490, 80, 30);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setText("Home");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel23);
        jLabel23.setBounds(20, 160, 70, 29);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel22.setText("Pelanggan");
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel22);
        jLabel22.setBounds(20, 200, 109, 29);

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
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
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

        jLabel3.setText("Kode Pelanggan Terakhir :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(910, 540, 150, 20);
        jPanel1.add(kode);
        kode.setBounds(1060, 540, 50, 20);

        jLabel20.setText("FUTSAL TIME");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(44, 511, 100, 14);

        jLabel21.setText("1.0");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(66, 525, 90, 14);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Pelanggan – 1.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(0, 0, 1152, 600);

        a.setText("jLabel2");
        jPanel1.add(a);
        a.setBounds(20, 70, 34, 14);

        user.setText("jLabel2");
        jPanel1.add(user);
        user.setBounds(0, 0, 34, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1153, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1166, 599));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void changeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeMouseClicked
        change2member();
        pelanggan.clear();
        member.clear();
    }//GEN-LAST:event_changeMouseClicked

    private void txtIdTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTelpActionPerformed

    private void tableMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMemberMouseClicked
        try {
            change2member();
            member.onMouseClickTableMember();
            jButton1.setText("UPDATE");
            jButton2.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FormMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableMemberMouseClicked

    private void plgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plgMouseClicked
        change2plg();
        pelanggan.clear();
        member.clear();
    }//GEN-LAST:event_plgMouseClicked

    private void tablePelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePelangganMouseClicked
        try {
            change2plg();
            pelanggan.onMouseClickTablePelanggan();
            jButton1.setText("UPDATE");
            jButton2.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FormMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablePelangganMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(x==true){
            if (jButton1.getText().equals("UPDATE")) {
                member.update();
                jButton1.setText("INSERT");
                jButton2.setVisible(false);
            }else{
                member.insert();
            }
        } else{
            if (jButton1.getText().equals("UPDATE")) {
                pelanggan.update();
                jButton1.setText("INSERT");
                jButton2.setVisible(false);
            }else{
                pelanggan.insert();
            }
        }
        tablePelanggan();
        tableMember();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            if (x==true) {
                member.getMember();
            } else{
                pelanggan.getPelanggan();
            }
            jButton1.setText("UPDATE");
            jButton2.setVisible(true);
        }
    }//GEN-LAST:event_txtIdKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pelanggan.clear();
        member.clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(x==true){
            jButton1.setText("INSERT");
            jButton2.setVisible(false);
            member.delete();
        } else{
            jButton1.setText("INSERT");
            jButton2.setVisible(false);
            pelanggan.delete();
        }
        tablePelanggan();
        tableMember();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
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
            java.util.logging.Logger.getLogger(FormMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMember.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> cbIdMember;
    private javax.swing.JLabel change;
    private javax.swing.JLabel idtelp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel kode;
    private javax.swing.JLabel kodeid;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel plg;
    private javax.swing.JTable tableMember;
    private javax.swing.JTable tablePelanggan;
    private javax.swing.JLabel telpalamat;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdTelp;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTelpAlamat;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
