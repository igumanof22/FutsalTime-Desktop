/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.MenuBarang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class MenuBarangDAO {
    public MenuBarang getBarang(String kodeCari) throws SQLException {
        String sql = "select *from menubarang where idmbrg = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        MenuBarang barang = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            barang = new MenuBarang();
            barang.setIdmbrg(rs.getString(1));
            barang.setNama(rs.getString(2));
            barang.setHarga(rs.getInt(3));
        }
        return barang;
    }
    
    Connection con;
    
    public MenuBarangDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(MenuBarang barang) throws SQLException {
        String sql = "insert into menubarang values (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, barang.getIdmbrg());
        ps.setString(2, barang.getNama());
        ps.setInt(3, barang.getHarga());
        ps.executeUpdate();
    }
    
    public void update(MenuBarang barang) throws SQLException {
        String sql = "update menubarang set nama = ?, harga = ? where idmbrg = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, barang.getNama());
        ps.setInt(2, barang.getHarga());
        ps.setString(3, barang.getIdmbrg());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from menubarang where idmbrg = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
