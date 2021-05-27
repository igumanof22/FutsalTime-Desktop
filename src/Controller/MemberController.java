/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MemberDAO;
import Koneksi.koneksi;
import Model.Member;
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
public class MemberController {
    FormMember view;
    Member member;
    MemberDAO memberDAO;
    Connection con;
    koneksi k;
    
    public MemberController (FormMember view){
        this.view = view;
        memberDAO = new MemberDAO();
        k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(){
        member = new Member();
        member.setIdmember(view.getTxtId().getText());
        member.setNamamember(view.getTxtNama().getText());
        member.setTelp(view.getTxtIdTelp().getText());
        member.setAlamat(view.getTxtTelpAlamat().getText());
        try {
            memberDAO.insert(member);
            JOptionPane.showMessageDialog(null, "Entry OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : "+ex);
        }
        this.clear();
    }
    
    public void update(){
        member = new Member();
        member.setIdmember(view.getTxtId().getText());
        member.setNamamember(view.getTxtNama().getText());
        member.setTelp(view.getTxtIdTelp().getText());
        member.setAlamat(view.getTxtTelpAlamat().getText());
        try {
            memberDAO.update(member);
            JOptionPane.showMessageDialog(null, "Update OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
        view.getTableMember();
        view.getTablePelanggan();
    }
    
    public void delete(){
        String Id = view.getTxtId().getText();
        try {
            memberDAO.delete(Id);
            JOptionPane.showMessageDialog(null, "Delete OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        this.clear();
        view.getTableMember();
        view.getTablePelanggan();
    }
    
    public void clear(){
        view.getTxtId().setText("");
        view.getTxtNama().setText("");
        view.getTxtIdTelp().setText("");
        view.getTxtTelpAlamat().setText("");
        
    }
    
    public void getMember(){
        String kodeCari=view.getTxtId().getText();
        try{
            member = new Member();
            member = memberDAO.getMember(kodeCari);
            
            if(member != null){
                view.getTxtNama().setText(member.getNamamember());
                view.getTxtIdTelp().setText(member.getTelp());
                view.getTxtTelpAlamat().setText(member.getAlamat());
            }
            else{
                JOptionPane.showMessageDialog(view, "Data Tidak ditemukan");
            }
        }
        catch(SQLException | HeadlessException e){
        }
    }
    
    public void onMouseClickTableMember() throws SQLException{
        String kode = view.getTableMember().getValueAt(view.getTableMember().getSelectedRow(), 0).toString();
        try{
            member = new Member();
            member = memberDAO.getMember(kode);
            view.getTxtId().setText(member.getIdmember());
            view.getTxtNama().setText(member.getNamamember());
            view.getTxtIdTelp().setText(member.getTelp());
            view.getTxtTelpAlamat().setText(member.getAlamat());
        }catch(SQLException e){
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, "Eror");
        }
    }
}
