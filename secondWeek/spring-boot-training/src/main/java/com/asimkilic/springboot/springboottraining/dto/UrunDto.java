package com.asimkilic.springboot.springboottraining.dto;

import com.asimkilic.springboot.springboottraining.entity.Kategori;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class UrunDto {

    private Long id;
    private String adi;
    private BigDecimal fiyat;
    private Date kayitTarihi;
    private Long kategoriId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public BigDecimal getFiyat() {
        return fiyat;
    }

    public void setFiyat(BigDecimal fiyat) {
        this.fiyat = fiyat;
    }

    public Date getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(Date kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public Long getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(Long kategoriId) {
        this.kategoriId = kategoriId;
    }
}
