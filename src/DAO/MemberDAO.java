/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Koneksi.koneksi;
import Model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class MemberDAO {
    public Member getMember(String kodeCari) throws SQLException {
        String sql = "select *from member where idmember = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeCari);
        Member mem = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            mem = new Member();
            mem.setIdmember(rs.getString(1));
            mem.setNamamember(rs.getString(2));
            mem.setTelp(rs.getString(3));
            mem.setAlamat(rs.getString(4));
        }
        return mem;
    }
    
    Connection con;
    
    public MemberDAO(){
        koneksi k = new koneksi();
        con = k.getConnection();
    }
    
    public void insert(Member mem) throws SQLException {
        String sql = "insert into member values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mem.getIdmember());
        ps.setString(2, mem.getNamamember());
        ps.setString(3, mem.getTelp());
        ps.setString(4, mem.getAlamat());
        ps.executeUpdate();
    }
    
    public void update(Member mem) throws SQLException {
        String sql = "update member set namamember = ?, telp = ?, alamat = ? where idmember = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(4, mem.getIdmember());
        ps.setString(1, mem.getNamamember());
        ps.setString(2, mem.getTelp());
        ps.setString(3, mem.getAlamat());
        ps.executeUpdate();
    }
    
    public void delete(String kode) throws SQLException {
        String sql = "delete from member where idmember = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kode);
        ps.executeUpdate();
    }
}
