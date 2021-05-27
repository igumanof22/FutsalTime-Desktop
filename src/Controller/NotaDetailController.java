/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotaDetailDAO;
import Koneksi.koneksi;
import Model.NotaDetail;
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
public class NotaDetailController {
    FormPemesanan view;
    NotaDetail detail;
    NotaDetailDAO detailDAO;
    Connection con;
    koneksi k;
    
    public NotaDetailController (FormPemesanan view){
        this.view = view;
        detailDAO = new NotaDetailDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(){
        detail = new NotaDetail();
        detail.setIdnota((String) view.getTxtIdNota().getText());
        detail.setIdmbrg((String) view.getCbKdMenu().getSelectedItem());
        detail.setJumlah(Integer.parseInt(view.getTxtJumlah().getText()));
        detail.setTotalharga(Integer.parseInt(view.getTxtSubTotal().getText()));
        try {
            detailDAO.insert(detail);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        detail = new NotaDetail();
        detail.setIdnota((String) view.getTxtIdNota().getText());
        detail.setIdmbrg((String) view.getCbKdMenu().getSelectedItem());
        detail.setJumlah(Integer.parseInt(view.getTxtJumlah().getText()));
        detail.setTotalharga(Integer.parseInt(view.getTxtSubTotal().getText()));
        try {
            detailDAO.update(detail);
            JOptionPane.showMessageDialog(null, "Update OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void delete(){
        String Id = view.getTxtIdNota().getText();
        String IdM = view.getCbKdMenu().getSelectedItem().toString();
        try {
            detailDAO.delete(Id, IdM);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
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
    
    public void getDetail(){
        String kodeCari=(String) view.getTxtIdNota().getText();
        String kode=(String) view.getCbKdMenu().getSelectedItem();
        try{
            detail = new NotaDetail();
            detail = detailDAO.getNotaDetail(kodeCari, kode);
            
            if(detail != null){
                view.getTxtIdNota().setText(detail.getIdnota());
                view.getCbKdMenu().setSelectedItem(detail.getIdmbrg());
                view.getTxtJumlah().setText(String.valueOf(detail.getJumlah()));
                view.getTxtSubTotal().setText(String.valueOf(detail.getTotalharga()));
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableDetail() throws SQLException{
        String kode = view.getTableDetail().getValueAt(view.getTableDetail().getSelectedRow(), 0).toString();
        String kd = view.getTableDetail().getValueAt(view.getTableDetail().getSelectedRow(), 1).toString();
        try{
            detail = new NotaDetail();
            detail = detailDAO.getNotaDetail(kode, kd);
            view.getTxtIdNota().setText(detail.getIdnota());
            view.getCbKdMenu().setSelectedItem(detail.getIdmbrg());
            view.getTxtJumlah().setText(String.valueOf(detail.getJumlah()));
            view.getTxtSubTotal().setText(String.valueOf(detail.getTotalharga()));
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
}
