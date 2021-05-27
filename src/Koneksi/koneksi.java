/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class koneksi {
    private String url = "jdbc:mysql://localhost/futsaltime";
    private String username = "root";
    private String password = "";
    public Connection getConnection(){
	try {
            Class.forName("com.mysql.jdbc.Driver");
            return java.sql.DriverManager.getConnection(url, username, password);
	} catch (Exception ex){
		ex.printStackTrace();
	}
	return null;
    }

    public static void main(String[] args) {
        koneksi k = new koneksi();
        if(k.getConnection()!=null){
            JOptionPane.showMessageDialog(null, "Koneksi OK");
        } else {
            JOptionPane.showMessageDialog(null, "Koneksi Error");
        }
    }
}
