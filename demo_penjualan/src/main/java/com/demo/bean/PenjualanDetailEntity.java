package com.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "penjualan_detail")
public class PenjualanDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_penjualan_detail", nullable = false)
    private Integer idPenjualanDetail;


    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_penjualan", referencedColumnName = "id_penjualan")
    private PenjualanEntity penjualan;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_barang", referencedColumnName = "id_barang")
    private BarangEntity barang;

    @Transient
    private String kodeBarang;

    @Transient
    private String namaBarang;

    @Transient
    private BigDecimal harga;

    @Transient
    private BigDecimal totalHarga;

    public PenjualanDetailEntity() {
        super();
    }

    public void setIdPenjualanDetail(Integer idPenjualanDetail) {
        this.idPenjualanDetail = idPenjualanDetail;
    }

    public Integer getIdPenjualanDetail() {
        return this.idPenjualanDetail;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getJumlah() {
        return this.jumlah;
    }

    public void setBarang(BarangEntity barang) {
        this.barang = barang;
    }

    public BarangEntity getBarang() {
        return this.barang;
    }

    public void setPenjualan(PenjualanEntity penjualan) {
        this.penjualan = penjualan;
    }

    public PenjualanEntity getPenjualan() {
        return this.penjualan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public BigDecimal getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(BigDecimal totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idPenjualanDetail);
        sb.append("]:");
        sb.append(jumlah);
        return sb.toString();
    }

}
