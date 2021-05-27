/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Akun;
import DAO.AkunDAO;
import Koneksi.koneksi;
import View.FormLogin;
import View.FormUtama;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class AkunController {
    FormLogin view;
    Akun akun;
    AkunDAO akunDAO;
    FormUtama v;
    Connection con;
    koneksi k;
    
    public AkunController (FormLogin view, FormUtama v){
        this.view = view;
        this.v = v;
        akunDAO = new AkunDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(){
        akun = new Akun();
        akun.setUser(v.getUser().getText());
        akun.setPass(v.getPass().getText());
        akun.setStatus(v.getStatus().getSelectedItem().toString());
        try {
            akunDAO.insert(akun);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        String user = v.getUser().getText();
        String pass = v.getPass().getText();
        try {
            akun = new Akun();
            akun = akunDAO.getAkun(user, pass);
            if (akun != null) {
                String passnew = v.getPassnew().getText();
                String passkon = v.getPasskon().getText();
                if (passnew.equals(passkon)) {
                    akun = new Akun();
                    akun.setUser(user);
                    akun.setPass(passnew);
                    akunDAO.update(akun);
                    JOptionPane.showMessageDialog(v, "Update OK");
                } else{
                    JOptionPane.showMessageDialog(v, "Password baru tidak sama");
                }
            } else{
                JOptionPane.showMessageDialog(null, "Password Salah");
                v.getUser().setText("");
                v.getPass().setText("");
                v.getPassnew().setText("");
                v.getPasskon().setText("");
                v.getUser().requestFocus();
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error : "+e);
        }
    }
    
    public void delete(){
        String Id = view.getTxtUser().getText();
        try {
            akunDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
    }
    
    public void login(){
        String user = view.getTxtUser().getText();
        String pass = view.getTxtPass().getText();
        try {
            akun = new Akun();
            akun = akunDAO.getAkun(user, pass);
            if (akun != null) {
                FormUtama views = new FormUtama();
                if (akun.getStatus().equals("operator")) {
                    views.getjLabel18().setVisible(false);
                    views.getjLabel4().setText("Edit Akun");
                    views.getKet().setText("Operator");
                    views.getUser().setText(akun.getUser());
                    views.getUser().setEnabled(false);
                } else{
                    views.getKet().setText("Administrator");
                }
                views.getA().setText(akun.getStatus());
                views.setVisible(true);
                view.dispose();
            } else{
                JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                this.clear();
                view.getTxtUser().requestFocus();
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error : "+e);
        }
    }
    
    public void clear(){
        view.getTxtUser().setText("");
        view.getTxtPass().setText("");
    }
    
    public void cek(){
        String user = v.getUser().getText();
        String pass = v.getPass().getText();
        try {
            akun = new Akun();
            akun = akunDAO.getAkun(user, pass);
            if (akun != null) {
                v.getPassnew().requestFocus();
            } else{
                JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                v.getUser().setText("");
                v.getPass().setText("");
                v.getUser().requestFocus();
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error : "+e);
        }
    }
}
