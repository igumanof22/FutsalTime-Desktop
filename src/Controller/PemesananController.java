/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotaDetailDAO;
import DAO.PemesananDAO;
import Koneksi.koneksi;
import Model.NotaDetail;
import Model.Pemesanan;
import View.FormPemesanan;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class PemesananController {
    FormPemesanan view;
    Pemesanan pemesanan;
    NotaDetail detail;
    PemesananDAO pemesananDAO;
    NotaDetailDAO detailDAO;
    Connection con;
    koneksi k;
    
    
    public PemesananController (FormPemesanan view){
        this.view = view;
        pemesananDAO = new PemesananDAO();
        detailDAO = new NotaDetailDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert() throws SQLException{
        pemesanan = new Pemesanan();
        detail = new NotaDetail();
        pemesanan.setIdnota(view.getCbIdNota().getSelectedItem().toString());
        pemesanan.setKdpelanggan((String) view.getCbKdPelanggan().getSelectedItem()); 
        pemesanan.setSubtotalmesan(Integer.valueOf(view.getTxtSubTotalPesan().getText()));
        try {
            pemesananDAO.insert(pemesanan);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        pemesanan = new Pemesanan();
        pemesanan.setIdnota(view.getCbIdNota().getSelectedItem().toString());
        pemesanan.setKdpelanggan((String) view.getCbKdPelanggan().getSelectedItem());
        pemesanan.setSubtotalmesan(Integer.valueOf(view.getTxtSubTotalPesan().getText()));
        try {
            pemesananDAO.update(pemesanan);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getCbIdNota().getSelectedItem().toString();
        try {
            pemesananDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void clear(){
        view.getTxtIdNota().setText("");
        view.getCbIdNota().setSelectedItem(0);
        view.getCbKdMenu().setSelectedItem(0);
        view.getCbKdPelanggan().setSelectedItem(0);
        view.getTxtJumlah().setText("");
        view.getTxtSubTotal().setText("");
    }
    
    public void getPesan(){
        String kodeCari=(String) view.getCbIdNota().getSelectedItem();
        try{
            pemesanan = new Pemesanan();
            pemesanan = pemesananDAO.getPesan(kodeCari);
            
            if(pemesanan != null){
                view.getCbKdPelanggan().setSelectedItem(pemesanan.getKdpelanggan());
                view.getTxtSubTotalPesan().setText(String.valueOf(pemesanan.getSubtotalmesan()));
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableMenuPesan() throws SQLException{
        String kode = view.getTableMenuPesan().getValueAt(view.getTableMenuPesan().getSelectedRow(), 0).toString();
        try{
            pemesanan = new Pemesanan();
            pemesanan = pemesananDAO.getPesan(kode);
            view.getCbIdNota().setSelectedItem(pemesanan.getIdnota());
            view.getCbKdPelanggan().setSelectedItem(pemesanan.getKdpelanggan());
            view.getTxtSubTotalPesan().setText(String.valueOf(pemesanan.getSubtotalmesan()));
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
    
    public void previewReportBulanPesan(String bulan, String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("pbulan", bulan);
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Pesan/PerBulanPesan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewReportTahunPesan(String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Pesan/PerTahunPesan.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
