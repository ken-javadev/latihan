package com.demo.bean.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mahasiswa" )
@NamedQueries ( {
  @NamedQuery ( name="MahasiswaEntity.countAll", query="SELECT COUNT(x) FROM MahasiswaEntity x" )
} )
public class MahasiswaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name="id", nullable=false)
    private Integer    id           ;

    @NotNull
    @Column(name="nim", nullable=false, length=50)
    private String     nim          ;

    @NotNull
    @Column(name="nama", nullable=false, length=50)
    private String     nama         ;

    @NotNull
    @Column(name="alamat", nullable=false)
    private String     alamat       ;

    @NotNull
    @Column(name="jenis_kelamin", nullable=false, length=15)
    private String     jenisKelamin ;

    @NotNull
    @Column(name="tempat_lahir", nullable=false, length=50)
    private String     tempatLahir  ;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_lahir", nullable=false)
    private Date       tanggalLahir ;

    @NotNull
    @Column(name="agama", nullable=false, length=10)
    private String     agama        ;

    @NotNull
    @Column(name="status_perkawinan", nullable=false, length=10)
    private String     statusPerkawinan ;

    public MahasiswaEntity() {
		super();
    }

    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setNim( String nim ) {
        this.nim = nim;
    }
    public String getNim() {
        return this.nim;
    }

    public void setNama( String nama ) {
        this.nama = nama;
    }
    public String getNama() {
        return this.nama;
    }

    public void setAlamat( String alamat ) {
        this.alamat = alamat;
    }
    public String getAlamat() {
        return this.alamat;
    }

    public void setJenisKelamin( String jenisKelamin ) {
        this.jenisKelamin = jenisKelamin;
    }
    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setTempatLahir( String tempatLahir ) {
        this.tempatLahir = tempatLahir;
    }
    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTanggalLahir( Date tanggalLahir ) {
        this.tanggalLahir = tanggalLahir;
    }
    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setAgama( String agama ) {
        this.agama = agama;
    }
    public String getAgama() {
        return this.agama;
    }

    public void setStatusPerkawinan( String statusPerkawinan ) {
        this.statusPerkawinan = statusPerkawinan;
    }
    public String getStatusPerkawinan() {
        return this.statusPerkawinan;
    }


    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(nim);
        sb.append("|");
        sb.append(nama);
        sb.append("|");
        sb.append(jenisKelamin);
        sb.append("|");
        sb.append(tempatLahir);
        sb.append("|");
        sb.append(tanggalLahir);
        sb.append("|");
        sb.append(agama);
        sb.append("|");
        sb.append(statusPerkawinan);
        return sb.toString(); 
    } 

}
