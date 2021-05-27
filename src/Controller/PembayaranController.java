/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PembayaranDAO;
import Koneksi.koneksi;
import Model.Pembayaran;
import View.FormPembayaran;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class PembayaranController {
    FormPembayaran view;
    Pembayaran bayar;
    PembayaranDAO bayarDAO;
    Connection con;
    koneksi k;
    
    public PembayaranController (FormPembayaran view){
        this.view = view;
        bayarDAO = new PembayaranDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    private int uang() throws SQLException{
        int uang = 0;
        String sql = "Select *from pembayaran";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
            
        if(rs.last()){
            uang = rs.getInt(5);
        }        
        return uang;
    }
    
    public void insert() throws SQLException{
        bayar = new Pembayaran();
        bayar.setIdtransaksi(view.getTxtIdTrans().getText());
        bayar.setIdnota((String) view.getCbIdNota().getSelectedItem());
        bayar.setKdpemakaian((String) view.getCbKdPemakaian().getSelectedItem());
        bayar.setTotal(Integer.valueOf(view.getTxtTotalBayar().getText()));
        bayar.setMasukkan(uang()+Integer.valueOf(view.getTxtTotalBayar().getText()));
        try {
            bayarDAO.insert(bayar);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void update() throws SQLException{
        bayar = new Pembayaran();
        bayar.setIdtransaksi(view.getTxtIdTrans().getText());
        bayar.setIdnota((String) view.getCbIdNota().getSelectedItem());
        bayar.setKdpemakaian((String) view.getCbKdPemakaian().getSelectedItem());
        bayar.setTotal(Integer.valueOf(view.getTxtTotalBayar().getText()));
        bayar.setMasukkan(uang()+Integer.valueOf(view.getTxtTotalBayar().getText()));
        try {
            bayarDAO.update(bayar);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getTxtIdTrans().getText();
        try {
            bayarDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void clear(){
        view.getTxtIdTrans().setText("");
        view.getCbIdNota().setSelectedItem(0);
        view.getCbKdPemakaian().setSelectedItem(0);
        view.getTxtTotalBayar().setText("");
        view.getTxtSubTotalPesan().setText("");
        view.getTxtSubTotalSewa().setText("");
    }
    
    public void getBayar(){
        String kodeCari=(String) view.getTxtIdTrans().getText();
        try{
            bayar = new Pembayaran();
            bayar = bayarDAO.getBayar(kodeCari);
            
            if(bayar != null){
                view.getCbIdNota().setSelectedItem(bayar.getIdnota());
                view.getCbKdPemakaian().setSelectedItem(bayar.getKdpemakaian());
                view.getTxtTotalBayar().setText(String.valueOf(bayar.getTotal()));
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableBayar() throws SQLException{
        String kode = view.getTableBayar().getValueAt(view.getTableBayar().getSelectedRow(), 0).toString();
        try{
            bayar = new Pembayaran();
            bayar = bayarDAO.getBayar(kode);
            view.getTxtIdTrans().setText(bayar.getIdtransaksi());
            view.getCbIdNota().setSelectedItem(bayar.getIdnota());
            view.getCbKdPemakaian().setSelectedItem(bayar.getKdpemakaian());
            view.getTxtTotalBayar().setText(String.valueOf(bayar.getTotal()));
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
    
    public void previewReportBulanBayar(String bulan, String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("pbulan", bulan);
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Bayar/PerBulanBayar.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewReportTahunBayar(String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Bayar/PerTahunBayar.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
