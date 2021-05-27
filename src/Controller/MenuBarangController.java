/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MenuBarangDAO;
import Koneksi.koneksi;
import Model.MenuBarang;
import View.FormPemesanan;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class MenuBarangController {
    FormPemesanan view;
    MenuBarang mnbrg;
    MenuBarangDAO mnbrgDAO;
    Connection con;
    koneksi k;
    
    public MenuBarangController (FormPemesanan view){
        this.view = view;
        mnbrgDAO = new MenuBarangDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(){
        mnbrg = new MenuBarang();
        mnbrg.setIdmbrg((String) view.getTxtIdNota().getText());
        mnbrg.setNama(view.getTxtJumlah().getText());
        mnbrg.setHarga(Integer.parseInt(view.getTxtSubTotal().getText()));
        try {
            mnbrgDAO.insert(mnbrg);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        mnbrg = new MenuBarang();
        mnbrg.setIdmbrg((String) view.getTxtIdNota().getText());
        mnbrg.setNama(view.getTxtJumlah().getText());
        mnbrg.setHarga(Integer.parseInt(view.getTxtSubTotal().getText()));
        try {
            mnbrgDAO.update(mnbrg);
            JOptionPane.showMessageDialog(null, "Update OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getTxtIdNota().getText();
        try {
            mnbrgDAO.delete(Id);
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
    
    public void getMenuBarang(){
        String kodeCari=(String) view.getTxtIdNota().getText();
        try{
            mnbrg = new MenuBarang();
            mnbrg = mnbrgDAO.getBarang(kodeCari);
            
            if(mnbrg != null){
                view.getTxtIdNota().setText(mnbrg.getIdmbrg());
                view.getTxtJumlah().setText(mnbrg.getNama());
                view.getTxtSubTotal().setText(String.valueOf(mnbrg.getHarga()));
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableMenuBarang() throws SQLException{
        String kode = view.getTableMenuPesan().getValueAt(view.getTableMenuPesan().getSelectedRow(), 0).toString();
        try{
            mnbrg = new MenuBarang();
            mnbrg = mnbrgDAO.getBarang(kode);
            view.getTxtIdNota().setText(mnbrg.getIdmbrg());
            view.getTxtJumlah().setText(mnbrg.getNama());
            view.getTxtSubTotal().setText(String.valueOf(mnbrg.getHarga()));
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
}
