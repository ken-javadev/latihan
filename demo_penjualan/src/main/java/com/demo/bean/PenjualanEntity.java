package com.demo.bean;

import com.demo.common.CustomddMMMyyyyDeserializer;
import com.demo.common.CustomddMMMyyyySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "penjualan")
public class PenjualanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_penjualan", nullable = false)
    private Integer idPenjualan;

    @Column(name = "no_faktur", nullable = false, length = 45)
    private String noFaktur;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tgl_penjualan", nullable = false)
    @JsonSerialize(using = CustomddMMMyyyySerializer.class)
    @JsonDeserialize(using = CustomddMMMyyyyDeserializer.class)
    private Date tglPenjualan;

    @Column(name = "nama_pelanggan", nullable = false, length = 45)
    private String namaPelanggan;

    @JsonIgnore
    @OneToMany(mappedBy = "penjualan", targetEntity = PenjualanDetailEntity.class)
    private List<PenjualanDetailEntity> listOfPenjualanDetail;

    @Transient
    private List<PenjualanDetailEntity> penjualanDetail;

    public PenjualanEntity() {
        super();
    }

    public void setIdPenjualan(Integer idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Integer getIdPenjualan() {
        return this.idPenjualan;
    }

    public void setNoFaktur(String noFaktur) {
        this.noFaktur = noFaktur;
    }

    public String getNoFaktur() {
        return this.noFaktur;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public Date getTglPenjualan() {
        return this.tglPenjualan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getNamaPelanggan() {
        return this.namaPelanggan;
    }


    public void setListOfPenjualanDetail(List<PenjualanDetailEntity> listOfPenjualanDetail) {
        this.listOfPenjualanDetail = listOfPenjualanDetail;
    }

    public List<PenjualanDetailEntity> getListOfPenjualanDetail() {
        return this.listOfPenjualanDetail;
    }

    public List<PenjualanDetailEntity> getPenjualanDetail() {
        return penjualanDetail;
    }

    public void setPenjualanDetail(List<PenjualanDetailEntity> penjualanDetail) {
        this.penjualanDetail = penjualanDetail;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(idPenjualan);
        sb.append("]:");
        sb.append(noFaktur);
        sb.append("|");
        sb.append(tglPenjualan);
        sb.append("|");
        sb.append(namaPelanggan);
        return sb.toString();
    }

}
