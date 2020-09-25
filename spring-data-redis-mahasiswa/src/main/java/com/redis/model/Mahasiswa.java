package com.redis.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redis.common.CustomddMMMyyyyDeserializer;
import com.redis.common.CustomddMMMyyyySerializer;

import java.io.Serializable;
import java.util.Date;

public class Mahasiswa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nim;

    private String nama;

    private String alamat;

    private String jenisKelamin;

    private String tempatLahir;

    @JsonSerialize(using = CustomddMMMyyyySerializer.class)
    @JsonDeserialize(using = CustomddMMMyyyyDeserializer.class)
    private Date tanggalLahir;

    private String agama;

    private String statusPerkawinan;

    public Mahasiswa() {
        super();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return this.nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getAgama() {
        return this.agama;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
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
