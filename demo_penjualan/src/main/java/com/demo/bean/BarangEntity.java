package com.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "barang")
public class BarangEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_barang", nullable = false)
    private Integer idBarang;

    @Column(name = "kode_barang", nullable = false, length = 10)
    private String kodeBarang;

    @Column(name = "nama_barang", nullable = false, length = 45)
    private String namaBarang;

    @Column(name = "harga_jual", nullable = false)
    private BigDecimal hargaJual;

    @Column(name = "harga_beli", nullable = false)
    private BigDecimal hargaBeli;

    @Column(name = "stok", nullable = false)
    private Integer stok;

    @JsonIgnore
    @OneToMany(mappedBy = "barang", targetEntity = PenjualanDetailEntity.class)
    private List<PenjualanDetailEntity> listOfPenjualanDetail;

    public BarangEntity() {
        super();
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public Integer getIdBarang() {
        return this.idBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getKodeBarang() {
        return this.kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaBarang() {
        return this.namaBarang;
    }

    public void setHargaJual(BigDecimal hargaJual) {
        this.hargaJual = hargaJual;
    }

    public BigDecimal getHargaJual() {
        return this.hargaJual;
    }

    public void setHargaBeli(BigDecimal hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public BigDecimal getHargaBeli() {
        return this.hargaBeli;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getStok() {
        return this.stok;
    }

    public void setListOfPenjualanDetail(List<PenjualanDetailEntity> listOfPenjualanDetail) {
        this.listOfPenjualanDetail = listOfPenjualanDetail;
    }

    public List<PenjualanDetailEntity> getListOfPenjualanDetail() {
        return this.listOfPenjualanDetail;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idBarang);
        sb.append("]:");
        sb.append(kodeBarang);
        sb.append("|");
        sb.append(namaBarang);
        sb.append("|");
        sb.append(hargaJual);
        sb.append("|");
        sb.append(hargaBeli);
        sb.append("|");
        sb.append(stok);
        return sb.toString();
    }

}
