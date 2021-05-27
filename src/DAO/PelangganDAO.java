/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class PelangganDAO {
    public Pelanggan getPelanggan(String kodeCari) throws SQLException {
        String sql = "select *from pelanggan where kdpelanggan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Pelanggan plg = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            plg = new Pelanggan();
            plg.setKdpelanggan(rs.getString(1));
            plg.setNamaplg(rs.getString(2));
            plg.setIdmember(rs.getString(3));
            plg.setTelp(rs.getString(4));
        }
        return plg;
    }
    
    Connection con;
    
    public PelangganDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Pelanggan plg) throws SQLException {
        String sql = "insert into pelanggan values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, plg.getKdpelanggan());
        ps.setString(2, plg.getNamaplg());
        ps.setString(3, plg.getIdmember());
        ps.setString(4, plg.getTelp());
        ps.executeUpdate();
    }
    
    public void update(Pelanggan plg) throws SQLException {
        String sql = "update pelanggan set namaplg = ?, idmember = ?, telp = ? where kdpelanggan = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, plg.getNamaplg());
        ps.setString(2, plg.getIdmember());
        ps.setString(3, plg.getTelp());
        ps.setString(4, plg.getKdpelanggan());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from pelanggan where kdpelanggan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
