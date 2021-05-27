/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PelangganDAO;
import Koneksi.koneksi;
import Model.Pelanggan;
import View.FormMember;
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
public class PelangganController {
    FormMember view;
    Pelanggan pelanggan;
    PelangganDAO pelangganDAO;
    Connection con;
    koneksi k;
    
    public PelangganController (FormMember view){
        this.view = view;
        pelangganDAO = new PelangganDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(){
        pelanggan = new Pelanggan();
        pelanggan.setKdpelanggan(view.getTxtId().getText());
        pelanggan.setNamaplg(view.getTxtNama().getText());
        pelanggan.setIdmember(view.getCbIdMember().getSelectedItem().toString());
        pelanggan.setTelp(view.getTxtTelpAlamat().getText());
        try {
            pelangganDAO.insert(pelanggan);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        pelanggan = new Pelanggan();
        pelanggan.setKdpelanggan(view.getTxtId().getText());
        pelanggan.setNamaplg(view.getTxtNama().getText());
        pelanggan.setIdmember(view.getCbIdMember().getSelectedItem().toString());
        pelanggan.setTelp(view.getTxtTelpAlamat().getText());
        try {
            pelangganDAO.update(pelanggan);
            JOptionPane.showMessageDialog(null, "Update OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getTxtId().getText();
        try {
            pelangganDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void clear(){
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtIdTelp().setText("");
        view.getTxtTelpAlamat().setText("");
        view.getCbIdMember().setSelectedIndex(0);
    }
    
    public void getPelanggan(){
        String kodeCari=view.getTxtId().getText();
        try{
            pelanggan = new Pelanggan();
            pelanggan = pelangganDAO.getPelanggan(kodeCari);
            
            if(pelanggan != null){
                view.getTxtNama().setText(pelanggan.getNamaplg());
                view.getCbIdMember().setSelectedItem(pelanggan.getIdmember());
                view.getTxtTelpAlamat().setText(pelanggan.getTelp());
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTablePelanggan() throws SQLException{
        String kode = view.getTablePelanggan().getValueAt(view.getTablePelanggan().getSelectedRow(), 0).toString();
        try{
            pelanggan = new Pelanggan();
            pelanggan = pelangganDAO.getPelanggan(kode);
            view.getTxtId().setText(pelanggan.getKdpelanggan());
            view.getTxtNama().setText(pelanggan.getNamaplg());
            view.getCbIdMember().setSelectedItem(pelanggan.getIdmember());
            view.getTxtTelpAlamat().setText(pelanggan.getTelp());
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
}
