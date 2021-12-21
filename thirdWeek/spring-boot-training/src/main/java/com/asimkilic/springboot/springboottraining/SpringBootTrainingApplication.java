package com.asimkilic.springboot.springboottraining;

import com.asimkilic.springboot.springboottraining.converter.JsonResponseConverter;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import com.asimkilic.springboot.springboottraining.entity.Urun;
import com.asimkilic.springboot.springboottraining.service.WebService;
import com.asimkilic.springboot.springboottraining.service.entityservice.KategoriEntityService;
import com.asimkilic.springboot.springboottraining.service.entityservice.UrunEntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootTrainingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

        KategoriEntityService kategoriEntityService = applicationContext.getBean(KategoriEntityService.class);
        UrunEntityService urunEntityService = applicationContext.getBean(UrunEntityService.class);

      /*  kategoriEkleme(kategoriEntityService);

        urunEkleme(kategoriEntityService, urunEntityService);
        urunEkleme(kategoriEntityService, urunEntityService);
        urunEkleme(kategoriEntityService, urunEntityService);
        urunEkleme(kategoriEntityService, urunEntityService);
        urunEkleme(kategoriEntityService, urunEntityService);
        urunEkleme(kategoriEntityService, urunEntityService);

        List<Long> silinecekUrunIdList = Arrays.asList(153L, 154L, 155L, 156L, 157L);
        for (Long urunId : silinecekUrunIdList) {
            urunEntityService.deleteById(urunId);
        }


        List<Urun> urunList = urunEntityService.findAll();
        for (Urun urun : urunList) {
            System.out.println(urun.getAdi());
        } */
        List<Kategori> kategoriList = kategoriEntityService.findByAdiEndsWith("r");
        for (Kategori kategori : kategoriList) {
            System.out.println(kategori.getAdi());
        }
    }

    private static void urunEkleme(KategoriEntityService kategoriEntityService, UrunEntityService urunEntityService) {
        Kategori kategori = kategoriEntityService.findById(952L);
        Urun urun = new Urun();
        urun.setAdi("Samsung Galaxy 10 ");
        urun.setFiyat(new BigDecimal("13000"));
        urun.setKayitTarihi(new Date());
        urun.setKategori(kategori);
        urun = urunEntityService.save(urun);
        System.out.println(urun);
    }

    private static void kategoriEkleme(KategoriEntityService service) {
        Kategori ustKategori = service.findById(2L);
        System.out.println(ustKategori);

        Kategori kategori = new Kategori();
        kategori.setAdi("Telefon");
        kategori.setKirilim(2L);
        kategori.setUstKategori(ustKategori);
        kategori = service.save(kategori);
        System.out.println(kategori);

    }


}
