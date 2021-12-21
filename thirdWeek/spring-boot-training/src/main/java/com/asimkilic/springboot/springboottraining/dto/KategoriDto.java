package com.asimkilic.springboot.springboottraining.dto;

import com.asimkilic.springboot.springboottraining.entity.Kategori;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class KategoriDto {
    private Long id;

    private String adi;

    private Long kirilim;

    private Long ustKategoriId;

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

    public Long getKirilim() {
        return kirilim;
    }

    public void setKirilim(Long kirilim) {
        this.kirilim = kirilim;
    }

    public Long getUstKategoriId() {
        return ustKategoriId;
    }

    public void setUstKategoriId(Long ustKategoriId) {
        this.ustKategoriId = ustKategoriId;
    }
}
