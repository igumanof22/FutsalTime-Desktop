/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Akun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class AkunDAO {
    public Akun getAkun(String kodeCari, String pass) throws SQLException {
        String sql = "select *from akun where user = ? and pass = md5(?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        ps.setString(2, pass);
        Akun akun = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            akun = new Akun();
            akun.setUser(rs.getString(1));
            akun.setPass(rs.getString(2));
            akun.setStatus(rs.getString(3));
        }
        return akun;
    }
    
    Connection con;
    
    public AkunDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Akun akun) throws SQLException {
        String sql = "insert into akun values (?,md5(?),?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, akun.getUser());
        ps.setString(2, akun.getPass());
        ps.setString(3, akun.getStatus());
        ps.executeUpdate();
    }
    
    public void update(Akun akun) throws SQLException {
        String sql = "update akun set pass = md5(?) where user = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, akun.getPass());
        ps.setString(2, akun.getUser());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from akun where user = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
