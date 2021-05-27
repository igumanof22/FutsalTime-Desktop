/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.NotaDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class NotaDetailDAO {
    public NotaDetail getNotaDetail(String kodeCari, String kode) throws SQLException {
        String sql = "select *from notadetail where idnota = ? && idmbrg = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        ps.setString(2, kode);
        NotaDetail nota = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            nota = new NotaDetail();
            nota.setIdnota(rs.getString(1));
            nota.setIdmbrg(rs.getString(2));
            nota.setJumlah(rs.getInt(3));
            nota.setTotalharga(rs.getInt(4));
        }
        return nota;
    }
    
    Connection con;
    
    public NotaDetailDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(NotaDetail nota) throws SQLException {
        String sql = "insert into notadetail values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nota.getIdnota());
        ps.setString(2, nota.getIdmbrg());
        ps.setInt(3, nota.getJumlah());
        ps.setInt(4, nota.getTotalharga());
        ps.executeUpdate();
    }
    
    public void update(NotaDetail nota) throws SQLException {
        String sql = "update notadetail set jumlah = ?, totalharga = ? where idnota = ? && idmbrg = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, nota.getJumlah());
        ps.setInt(2, nota.getTotalharga());
        ps.setString(3, nota.getIdnota());
        ps.setString(4, nota.getIdmbrg());
        ps.executeUpdate();
    }
    
    public void delete(String kodenota, String kodembrg) throws SQLException {
        String sql = "delete from notadetail where idnota = ? && idmbrg = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodenota);
        ps.setString(2, kodembrg);
        ps.executeUpdate();
    }
}
