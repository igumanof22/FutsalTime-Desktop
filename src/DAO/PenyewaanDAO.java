/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Penyewaan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class PenyewaanDAO {
    public Penyewaan getSewa(String kodeCari) throws SQLException {
        String sql = "select *from penyewaan where kdpemakaian = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Penyewaan sewa = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            sewa = new Penyewaan();
            sewa.setKdpemakaian(rs.getString(1));
            sewa.setKdlapangan(rs.getString(2));
            sewa.setKdpelanggan(rs.getString(3));
            sewa.setTglmain(rs.getString(4));
            sewa.setJamawal(rs.getString(5));
            sewa.setJamakhir(rs.getString(6));
            sewa.setUangmuka(rs.getInt(7));
            sewa.setBayasewa(rs.getInt(8));
        }
        return sewa;
    }
    
    Connection con;
    
    public PenyewaanDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Penyewaan sewa) throws SQLException {
        String sql = "insert into penyewaan values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sewa.getKdpemakaian());
        ps.setString(2, sewa.getKdlapangan());
        ps.setString(3, sewa.getKdpelanggan());
        ps.setString(4, sewa.getTglmain());
        ps.setString(5, sewa.getJamawal());
        ps.setString(6, sewa.getJamakhir());
        ps.setInt(7, sewa.getUangmuka());
        ps.setInt(8, sewa.getBayasewa());
        ps.setInt(9, sewa.getTotalsewa());
        ps.setInt(10, sewa.getPemasukkan());
        ps.executeUpdate();
    }
    
    public void update(Penyewaan sewa) throws SQLException {
        String sql = "update penyewaan set kdlapangan = ?, kdpelanggan = ?, tglmain = ?, jamawal = ?, jamakhir = ?, uangmuka = ?, bayarsewa = ?, totalsewa = ?, pemasukkan = ? where kdpemakaian = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sewa.getKdlapangan());
        ps.setString(2, sewa.getKdpelanggan());
        ps.setString(3, sewa.getTglmain());
        ps.setString(4, sewa.getJamawal());
        ps.setString(5, sewa.getJamakhir());
        ps.setInt(6, sewa.getUangmuka());
        ps.setInt(7, sewa.getBayasewa());
        ps.setInt(8, sewa.getTotalsewa());
        ps.setInt(9, sewa.getPemasukkan());
        ps.setString(10, sewa.getKdpemakaian());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from penyewaan where kdpemakaian = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
