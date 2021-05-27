/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Lapangan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class LapanganDAO {
    public Lapangan getLapangan(String kodeCari) throws SQLException {
        String sql = "select *from menubarang where idmbrg = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Lapangan lp = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            lp = new Lapangan();
            lp.setKdlapangan(rs.getString(1));
            lp.setNamalpg(rs.getString(2));
            lp.setHarga(rs.getInt(3));
        }
        return lp;
    }
    
    Connection con;
    
    public LapanganDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Lapangan lp) throws SQLException {
        String sql = "insert into lapangan values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, lp.getKdlapangan());
        ps.setString(2, lp.getNamalpg());
        ps.setInt(3, lp.getHarga());
        ps.executeUpdate();
    }
    
    public void update(Lapangan lp) throws SQLException {
        String sql = "update lapangan set namaplg = ?, harga = ? where kdlapangan = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, lp.getNamalpg());
        ps.setInt(2, lp.getHarga());
        ps.setString(3, lp.getKdlapangan());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from lapangan where kdlapangan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
