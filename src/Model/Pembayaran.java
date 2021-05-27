/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Pembayaran {
    public String idtransaksi;
    public String kdpemakaian;
    public String idnota;
    public int total;
    public int masukkan;

    public String getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(String idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public String getKdpemakaian() {
        return kdpemakaian;
    }

    public void setKdpemakaian(String kdpemakaian) {
        this.kdpemakaian = kdpemakaian;
    }

    public String getIdnota() {
        return idnota;
    }

    public void setIdnota(String idnota) {
        this.idnota = idnota;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMasukkan() {
        return masukkan;
    }

    public void setMasukkan(int masukkan) {
        this.masukkan = masukkan;
    }    
}
