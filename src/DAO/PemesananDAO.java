/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Pemesanan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class PemesananDAO {
    public Pemesanan getPesan(String kodeCari) throws SQLException {
        String sql = "select *from pemesanan where idnota = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Pemesanan pesan = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            pesan = new Pemesanan();
            pesan.setIdnota(rs.getString(1));
            pesan.setKdpelanggan(rs.getString(2));
            pesan.setSubtotalmesan(rs.getInt(3));
        }
        return pesan;
    }
    
    Connection con;
    
    public PemesananDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Pemesanan pesan) throws SQLException {
        String sql = "insert into pemesanan values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pesan.getIdnota());
        ps.setString(2, pesan.getKdpelanggan());
        ps.setInt(3, pesan.getSubtotalmesan());
        ps.executeUpdate();
    }
    
    public void update(Pemesanan pesan) throws SQLException {
        String sql = "update pemesanan set kdpelanggan = ?, subtotalmesan = ? where idnota = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pesan.getKdpelanggan());
        ps.setInt(2, pesan.getSubtotalmesan());
        ps.setString(3, pesan.getIdnota());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from pemesanan where idnota = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
