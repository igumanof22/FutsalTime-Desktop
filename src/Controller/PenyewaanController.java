/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PenyewaanDAO;
import Koneksi.koneksi;
import Model.Penyewaan;
import View.FormBooking;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class PenyewaanController {
    FormBooking view;
    Penyewaan penyewaan;
    PenyewaanDAO penyewaanDAO;
    Connection con;
    koneksi k;
    
    public PenyewaanController (FormBooking view){
        this.view = view;
        penyewaanDAO = new PenyewaanDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    private int uang() throws SQLException{
        int uang = 0;
        String sql = "Select *from penyewaan";
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(sql);
            
        if(rs.last()){
            uang = rs.getInt(10);
        }        
        return uang;
    }
    
    public void insert() throws SQLException{
        penyewaan = new Penyewaan();
        penyewaan.setKdpemakaian(view.getTxtKdPemakaian().getText());
        penyewaan.setKdlapangan((String) view.getCbKdLapangan().getSelectedItem());
        penyewaan.setKdpelanggan((String) view.getCbKdPelanggan().getSelectedItem());
        penyewaan.setTglmain(String.valueOf(view.getTahun().getValue()+"-"+view.getBulan().getValue()+"-"+view.getHari().getValue()));
        penyewaan.setJamawal(view.getJamawal().getText()+":"+view.getMenitawal().getText());
        penyewaan.setJamakhir(view.getJamakhir().getText()+":"+view.getMenitakhir().getText());
        penyewaan.setUangmuka(Integer.valueOf(view.getTxtDP().getText()));
        penyewaan.setBayasewa(Integer.valueOf(view.getTxtSewa().getText()));
        penyewaan.setTotalsewa(Integer.valueOf(view.getTxtSewa().getText())+Integer.valueOf(view.getTxtDP().getText()));
        penyewaan.setPemasukkan(uang()+Integer.valueOf(view.getTxtSewa().getText())+Integer.valueOf(view.getTxtDP().getText()));
        try {
            penyewaanDAO.insert(penyewaan);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update() throws SQLException{
        penyewaan = new Penyewaan();
        penyewaan.setKdpemakaian(view.getTxtKdPemakaian().getText());
        penyewaan.setKdlapangan((String) view.getCbKdLapangan().getSelectedItem());
        penyewaan.setKdpelanggan((String) view.getCbKdPelanggan().getSelectedItem());
        penyewaan.setTglmain(String.valueOf(view.getTahun().getValue()+"-"+view.getBulan().getValue()+"-"+view.getHari().getValue()));
        penyewaan.setJamawal(view.getJamawal().getText()+":"+view.getMenitawal().getText());
        penyewaan.setJamakhir(view.getJamakhir().getText()+":"+view.getMenitakhir().getText());
        penyewaan.setUangmuka(Integer.valueOf(view.getTxtDP().getText()));
        penyewaan.setBayasewa(Integer.valueOf(view.getTxtSewa().getText()));
        penyewaan.setTotalsewa(Integer.valueOf(view.getTxtSewa().getText())+Integer.valueOf(view.getTxtDP().getText()));
        penyewaan.setPemasukkan(uang()+Integer.valueOf(view.getTxtSewa().getText())+Integer.valueOf(view.getTxtDP().getText()));
        try {
            penyewaanDAO.update(penyewaan);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getTxtKdPemakaian().getText();
        try {
            penyewaanDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void clear(){
        view.getTxtKdPemakaian().setText("");
        view.getCbKdLapangan().setSelectedIndex(0);
        view.getCbKdPelanggan().setSelectedIndex(0);
        view.getTanggal().setDateFormatString("");
        view.getTxtDP().setText("");
        view.getTxtSewa().setText("");
        view.getJamawal().setText("");
        view.getJamakhir().setText("");
        view.getMenitawal().setText("");
        view.getMenitakhir().setText("");
    }
    
    public void getSewa(){
        String kodeCari=view.getTxtKdPemakaian().getText();
        try{
            penyewaan = new Penyewaan();
            penyewaan = penyewaanDAO.getSewa(kodeCari);
            
            if(penyewaan != null){
                view.getCbKdLapangan().setSelectedItem(penyewaan.getKdlapangan());
                view.getCbKdPelanggan().setSelectedItem(penyewaan.getKdpelanggan());
                view.getTanggal().setDate(Date.valueOf(penyewaan.getTglmain()));
                view.getWaktuAwal().setText(penyewaan.getJamawal());
                view.getWaktuAkhir().setText(penyewaan.getJamakhir());
                view.getTxtDP().setText(String.valueOf(penyewaan.getUangmuka()));
                view.getTxtSewa().setText(String.valueOf(penyewaan.getBayasewa()));
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableBooking() throws SQLException{
        String kode = view.getTableSewa().getValueAt(view.getTableSewa().getSelectedRow(), 0).toString();
        try{
            penyewaan = new Penyewaan();
            penyewaan = penyewaanDAO.getSewa(kode);
            view.getTxtKdPemakaian().setText(penyewaan.getKdpemakaian());
            view.getCbKdLapangan().setSelectedItem(penyewaan.getKdlapangan());
            view.getCbKdPelanggan().setSelectedItem(penyewaan.getKdpelanggan());
            view.getTanggal().setDate(Date.valueOf(penyewaan.getTglmain()));
            view.getWaktuAwal().setText(penyewaan.getJamawal());
            view.getWaktuAkhir().setText(penyewaan.getJamakhir());
            view.getTxtDP().setText(String.valueOf(penyewaan.getUangmuka()));
            view.getTxtSewa().setText(String.valueOf(penyewaan.getBayasewa()));
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
    
    public void previewReportBulanSewa(String bulan, String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("pbulan", bulan);
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Sewa/PerBulanSewa.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void previewReportTahunSewa(String tahun){
        try {
            HashMap parameter = new HashMap();
            parameter.put("ptahun", tahun);
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport("src/Report/Sewa/PerTahunSewa.jasper", parameter, con);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            Logger.getLogger(PenyewaanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
