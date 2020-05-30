package com.va181.mahardika;

import java.util.Date;

public class Negara {

    private int idNegara;
    private String nama;
    private Date tanggal;
    private String gambar;
    private String rank;
    private String presiden;
    private String benua;
    private String profile;

    public Negara(int idNegara, String nama, Date tanggal, String gambar, String rank, String presiden, String benua, String profile) {
        this.idNegara = idNegara;
        this.nama = nama;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.rank = rank;
        this.presiden = presiden;
        this.benua = benua;
        this.profile = profile;
    }

    public int getIdNegara() {
        return idNegara;
    }

    public void setIdNegara(int idNegara) {
        this.idNegara = idNegara;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() { return tanggal; }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) { this.gambar = gambar; }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPresiden() {
        return presiden;
    }

    public void setPresiden(String presiden) {
        this.presiden = presiden;
    }

    public String getBenua() {
        return benua;
    }

    public void setBenua(String benua) {
        this.benua = benua;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
