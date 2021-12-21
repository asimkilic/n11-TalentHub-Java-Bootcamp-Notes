package com.asimkilic.springboot.springboottraining.dto;

import java.math.BigDecimal;

public class UrunDetayDto {

    private String urunAdi;
    private String kategoriAdi;
    private BigDecimal urunFiyati;

    public UrunDetayDto() {
    }

    public UrunDetayDto(String urunAdi, String kategoriAdi, BigDecimal urunFiyati) {
        this.urunAdi = urunAdi;
        this.kategoriAdi = kategoriAdi;
        this.urunFiyati = urunFiyati;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getKategoriAdi() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi = kategoriAdi;
    }

    public BigDecimal getUrunFiyati() {
        return urunFiyati;
    }

    public void setUrunFiyati(BigDecimal urunFiyati) {
        this.urunFiyati = urunFiyati;
    }

    @Override
    public String toString() {
        return "UrunDetayDto{" +
                "urunAdi='" + urunAdi + '\'' +
                ", kategoriAdi='" + kategoriAdi + '\'' +
                ", urunFiyati=" + urunFiyati +
                '}';
    }
}