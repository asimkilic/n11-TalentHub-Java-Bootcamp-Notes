package com.asimkilic.springboot.springboottraining.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "URUN"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "kategori"})
@JsonFilter("UrunFilter")
public class Urun implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "URUN_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(length = 50, name = "ADI")
    private String adi;

    @Column(name = "FIYAT", precision = 19, scale = 2)
    private BigDecimal fiyat;

    @Column(name = "KAYIT_TARIHI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kayitTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KATEGORI",
            foreignKey = @ForeignKey(name = "FK_URUN_KATEGORI_ID")
    )
    private Kategori kategori;

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

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }
}