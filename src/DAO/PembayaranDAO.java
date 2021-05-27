/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Pembayaran;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class PembayaranDAO {
    public Pembayaran getBayar(String kodeCari) throws SQLException {
        String sql = "select *from pembayaran where idtransaksi = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Pembayaran bayar = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            bayar = new Pembayaran();
            bayar.setIdtransaksi(rs.getString(1));
            bayar.setKdpemakaian(rs.getString(2));
            bayar.setIdnota(rs.getString(3));
            bayar.setTotal(rs.getInt(4));
        }
        return bayar;
    }
    
    Connection con;
    
    public PembayaranDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Pembayaran bayar) throws SQLException {
        String sql = "insert into pembayaran values (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, bayar.getIdtransaksi());
        ps.setString(2, bayar.getKdpemakaian());
        ps.setString(3, bayar.getIdnota());
        ps.setInt(4, bayar.getTotal());
        ps.setInt(5, bayar.getMasukkan());
        ps.executeUpdate();
    }
    
    public void update(Pembayaran bayar) throws SQLException {
        String sql = "update pembayaran set kdpemakaian = ?, idnota = ?, total = ?, pemasukkan = ? where idtransaksi = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, bayar.getKdpemakaian());
        ps.setString(2, bayar.getIdnota());
        ps.setInt(3, bayar.getTotal());
        ps.setInt(4, bayar.getMasukkan());
        ps.setString(5, bayar.getIdtransaksi());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from pembayaran where idtransaksi = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
