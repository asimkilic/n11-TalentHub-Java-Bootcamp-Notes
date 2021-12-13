https://start.spring.io  üzerinden Project Metadata'larını verdikten sonra, Dependencies bölümünden **Spring Web**, **Spring Data JPA, PostgreSQL Driver** dependency'lerini ekledikten sonra *Generate* diyerek dosyamızı indiriyoruz. 

İndirdiğimiz zip dosyasını uygun klasöre çıkarttıktan sonra IntellJ üzerinden File>Open dedikten sonra indirdiğimiz projenin içerisindeki *Pom.xml* 'i seçiyoruz.

Database bağlantısı olması için gerekli ayarlamaları Sources altında bulunan **application.properties** içerisinde veriyoruz.

```java
spring.datasource.url=jdbc:postgres://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=12345
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Daha sonra içerisine package'lerimizi  ve class'larımızı ekliyoruz:

entity > Kategori

```java
package com.asimkilic.springboot.springboottraining.entity;

import javax.persistence.*;

@Entity
@Table(name="KATEGORI")
public class Kategori {

    @SequenceGenerator(name="generator", sequenceName="KATEGORI_ID_SEQ")
    @Id
    @GeneratedValue(generator="generator")
    @Column(name="ID", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

```

dao > KategoriDao.

KategoriDao'umuzu artık interface olarak oluşturup, CrudRepository' i extend ediyoruz. CrudRepository'e Generic Type olarak ilgili Entity Class'ımızı ve Entity'imizin primary key tipini belirtiyoruz. Bu CrudRepository içerisinde bizim için silme, ekleme, güncelleme gibi işlemleri hazır olarak barındırıyor.

```java
package com.asimkilic.springboot.springboottraining.dao;

import com.asimkilic.springboot.springboottraining.entity.Kategori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriDao extends CrudRepository<Kategori,Long> {

}

```

entityservice > KategoriEntityService

```java
package com.asimkilic.springboot.springboottraining.entityservice;

import com.asimkilic.springboot.springboottraining.dao.KategoriDao;
import com.asimkilic.springboot.springboottraining.entity.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriEntityService {

    @Autowired
    private KategoriDao kategoriDao;

    public List<Kategori> findAll() {
        return (List<Kategori>) kategoriDao.findAll();
    }
}

```

**SpringBootTrainingApplication** class'ımız içerisinde bulunan run metodumuz bize bir tane **application context**  nesnesi verir.

```java
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

```

aldığımız *application context* nesnemizden *getBean* metodu ilede ilgili entity service nesnemizin örneğini alıyoruz.

```java
KategoriEntityService service = applicationContext.getBean(KategoriEntityService.class);
```

service nesnemiz üzerinden istediğimiz verileri veri tabanından alabiliyoruz.

```java
List<Kategori> kategoriList = service.findAll();
		for (Kategori kategori : kategoriList) {
			System.out.println(kategori.getId());
		}
```

converter paketi oluşturup içerisine **ResponseConverter** interface'i oluşturuyoruz.

```java
package com.asimkilic.springboot.springboottraining.converter;

public interface ResponseConverter {
    void convert();
}

```

Bu interface'i implemente eden sınıflar gelen istekleri convert edecek.

converter paketi içerisine **JsonResponseConverter** ve **XmlResponseConverter** class'larını oluşturuyoruz.

```java
package com.asimkilic.springboot.springboottraining.converter;

public class JsonResponseConverter implements ResponseConverter {
    @Override
    public void convert() {
        System.out.println("JSON");
    }
}

```

```java
package com.asimkilic.springboot.springboottraining.converter;

public class XmlResponseConverter implements ResponseConverter {
    @Override
    public void convert() {
        System.out.println("XML");
    }
}

```

ana paketimiz içerisine bir tanede **service** paketi oluşturuyoruz (*entityservice paketini'de bu paket içerisine alıyoruz*). Oluşan service paketi içerisine bir tane **WebService** class'ı oluşturuyoruz.



```java
package com.asimkilic.springboot.springboottraining.service;

import com.asimkilic.springboot.springboottraining.converter.ResponseConverter;

public class WebService {

    private ResponseConverter responseConverter;

    public void convertResponse(){
    responseConverter.convert();
    }
}

```

Geleneksel yöntemlerde biz ResponseConverter içerisine nesnemizi Constructor Injection ile alıyorduk.

```java
public class WebService {

    private ResponseConverter responseConverter;
public WebService(ResponseConverter responseConverter){
    this.responseConverter=responseConverter;
}
    public void convertResponse(){
    responseConverter.convert();
    }
}

```

Bunu kullanalım;

```java
WebService service = new WebService(new JsonResponseConverter());
service.convertResponse();
```

Bize çıktı olarak "JSON" verecektir. Burada *WebService* örneğini alırken içerisine hangi converter'ı verirsek sonucu ona göre alırız. Hangi converter'ın kullanılacağı kullanıcıya kalıyor. Burada Spring yok geleneksel Java, ama biz bağımlılıklarla uğraşmak istemiyoruz.

İlk olarak ResponseConverter interface'ine giderek onu **@Component** olarak işaretliyoruz. İmplementasyonlarda bizim için **@Component**'tir.  WebService'de bizim için bir **@Service**'dir. 

```java
@Component
public interface ResponseConverter 
    
@Component
public class XmlResponseConverter implements ResponseConverter 
    
@Component
public class JsonResponseConverter implements ResponseConverter
    
@Service
public class WebService
```

Bundan sonra  *WebService* içerisinde ResponseConverter' ı artık Constructor Injection ile almayacağız, bunu **@Autowired** olarak işaretliyoruz.

```java
@Service
public class WebService {

    @Autowired
    private ResponseConverter responseConverter;
    
    public void convertResponse() {
        responseConverter.convert();
    }
}

```

Daha sonra  WebServer'ın örneğini context üzerinden alıyoruz.

```java

WebService service= applicationContext.getBean(WebService.class);
service.convertResponse();
```

Bu kullanıımda eğer WebService içerisinde istemiş olduğumuz interface'i imlemente eden bir tane somut sınıf olursa Spring bizim yerimize WebServis içerisine onu injekte edecektir. Birden fazla ise *required single bean, but 2 were found* hatası verecektir. Birden fazla olması durumunu nasıl yönetiriz:

- Xml Converter sınıfımızı **@Primary** olarak işaretlersek, her seferinde XmlConverter sınıfımızı kullanacaktır.

- Elimizde "JsonResponseConverter" ve "XmlResponseConverter" sınıflarımız var eğer biz WebService içerisinde tanımladığımız field adını bunlardan birtanesi olarak verirsek otomatik eşleşme yaparak injeksiyon yapacaktır. Bu ***Autowired by name*** olarak geçer. Örn:

  ```java
    @Autowired
     private ResponseConverter jsonResponseConverter;
  ```

- Diğer bir yöntem ise Converter sınıflarımızı **@Qualifier("name")** olarak işaretlemek  ve daha sonra *Autowired* yapacağımız yerde Qualifier ile çağırmak.

  ```java
  @Component
  @Qualifier("json")
  public class JsonResponseConverter implements ResponseConverter
   ---------------------------------------
  @Component
  @Qualifier("xml")
  public class XmlResponseConverter implements ResponseConverter 
   ----------------------------------------
   @Autowired
   @Qualifier("xml")
   private ResponseConverter responseConverter;
  ```

  Öncelik sırası:   Qualifier > Primary > By Name

*Gerekli olan dto,entity,entityservice nesnelerimizi eski projeden aktarıyoruz.*

**KategoriEntityService** içerisinde CRUD metodlarını yazıyoruz. **KategoriDao** içerisinde **CrudRepository**'yi  extend ettiğimiz için CRUD operasyonları ön tanımlı olarak bize geliyor.

```java
@Service
public class KategoriEntityService {

    @Autowired
    private KategoriDao kategoriDao;

    public List<Kategori> findAll() {

        return (List<Kategori>) kategoriDao.findAll();
    }

    public Kategori findById(Long id) {
        Optional<Kategori> optionalKategori = kategoriDao.findById(id);
        Kategori kategori = null;
        if (optionalKategori.isPresent())
            kategori = optionalKategori.get();

        return kategori;
    }

    public Kategori save(Kategori kategori) {
        kategori = kategoriDao.save(kategori);
        return kategori;
    }
    public void delete(Kategori kategori){
        kategoriDao.delete(kategori);
    }
    public void deleteById(Long id){
        kategoriDao.deleteById(id);
    }
    public long count(){
       return kategoriDao.count();
    }
}

```

*Aynı işlemleri **UrunEntityService** içinde yapıyoruz*

```java
@Service
public class UrunEntityService {

    @Autowired
    private UrunDao urunDao;

    public List<Urun> findAll() {
        return (List<Urun>) urunDao.findAll();
    }
    public Urun findById(Long id) {
        Optional<Urun> optionalUrun = urunDao.findById(id);
        Urun urun = null;
        if (optionalUrun.isPresent())
            urun = optionalUrun.get();

        return urun;
    }

    public Urun save(Urun urun) {
        urun = urunDao.save(urun);
        return urun;
    }
    public void delete(Urun urun){
        urunDao.delete(urun);
    }
    public void deleteById(Long id){
        urunDao.deleteById(id);
    }
    public long count(){
        return urunDao.count();
    }
}

```

İşlemleri denemek için main metodumuzun olduğu **SpringBootTrainingApplication**' ı açıyoruz.

İlk olarak veri tabanında olan herhangi bir üst kategori nesnesini çekiyoruz bunu değişkende tutuyoruz, daha sonra yeni bir *Kategori* nesnesi oluşturup içerisine bilgileri verdikten sonra, *service* üzerinden *save* işlemi yapıyoruz. 

İlk veri tabanını kontrol ettiğimizde 9 adet verimiz var.

```powershell
postgres=# select id, adi, kirilim, id_ust_kategori from public.kategori;
 id  |        adi         | kirilim | id_ust_kategori
-----+--------------------+---------+-----------------
   1 | Moda               |       1 |
   2 | Elektronik         |       1 |
   3 | Ev & Yaşam         |       1 |
   4 | Elbise             |       2 |               1
   5 | Gömlek             |       2 |               1
   6 | Bilgisayar         |       2 |               2
   7 | Dizüstü Bilgisayar |       3 |               6
   8 | Mobilya            |       2 |               3
   9 | Sehpa              |       3 |               8
(9 satır)
```

Save işlemini gerçekleştiriyoruz.

```java
@SpringBootApplication
public class SpringBootTrainingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

        KategoriEntityService service = applicationContext.getBean(KategoriEntityService.class);

        Kategori ustKategori = service.findById(2L);
        System.out.println(ustKategori);

        Kategori kategori = new Kategori();
        kategori.setAdi("Telefon");
        kategori.setKirilim(2L);
        kategori.setUstKategori(ustKategori);
        kategori = service.save(kategori);
        System.out.println(kategori);

    }
```

Veri tabanını tekrar sorguladığımızda ilgili kaydın eklendiğini görüyoruz.

```powershell
postgres=# select id, adi, kirilim, id_ust_kategori from public.kategori;
 id  |        adi         | kirilim | id_ust_kategori
-----+--------------------+---------+-----------------
   1 | Moda               |       1 |
   2 | Elektronik         |       1 |
   3 | Ev & Yaşam         |       1 |
   4 | Elbise             |       2 |               1
   5 | Gömlek             |       2 |               1
   6 | Bilgisayar         |       2 |               2
   7 | Dizüstü Bilgisayar |       3 |               6
   8 | Mobilya            |       2 |               3
   9 | Sehpa              |       3 |               8
 952 | Telefon            |       2 |               2
(10 satır)
```

Aynı şekilde bir **Urun** ekliyoruz. Ürünü eklemeden önce veri tabanındaki kayıtları kontrol edecek olursak;

```powershell
postgres=# select id, adi, fiyat, id_kategori from public.urun;
 id  |          adi          |  fiyat   | id_kategori
-----+-----------------------+----------+-------------
   1 | Mavi Elbise           |   200.00 |           4
   2 | Kırmızı Elbise        |   300.00 |           4
   3 | Mavi Gömlek           |   500.00 |           5
   4 | Sarı Gömlek           |   450.00 |           5
   5 | HP                    | 15000.00 |           7
   6 | MSI                   | 20000.00 |           7
   7 | Orta Sehpa            |   600.00 |           9
```

```java
@SpringBootApplication
public class SpringBootTrainingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

        KategoriEntityService kategoriEntityService = applicationContext.getBean(KategoriEntityService.class);
        UrunEntityService urunEntityService = applicationContext.getBean(UrunEntityService.class);


        Kategori kategori = kategoriEntityService.findById(952L);
        Urun urun = new Urun();
        urun.setAdi("Samsung Galaxy 10 ");
        urun.setFiyat(new BigDecimal("13000"));
        urun.setKayitTarihi(new Date());
        urun.setKategori(kategori);
        urun = urunEntityService.save(urun);
        System.out.println(urun);
    }

```

İlk olarak **Urun** nesnemiz için kategori bilgisini çekiyoruz.

```java
 Kategori kategori = kategoriEntityService.findById(952L);
```

Burada hata alıyoruz, Kategori nesnemiz içinde üst kategori alanını LAZY işaretlediğimiz için Kategori içerisinde bulunan üst kategori bilgisi henüz gelmemiş oluyor, ister Kategori entity'si  içerisinde Kategori alanını EAGER olarak işaretlersiniz, yada bu ToString'i override ettiğimiz yerde sadece id bilgisini döndürürüz.

```java
  @Override
    public String toString() {
        return id == null ? "" : id.toString();
    }
```

Kayıt işlemini tekrar denedikten sonra veri tabanı tablomuz;

```powershell
postgres=# select id, adi, fiyat, id_kategori from public.urun;
 id  |          adi          |  fiyat   | id_kategori
-----+-----------------------+----------+-------------
   1 | Mavi Elbise           |   200.00 |           4
   2 | Kırmızı Elbise        |   300.00 |           4
   3 | Mavi Gömlek           |   500.00 |           5
   4 | Sarı Gömlek           |   450.00 |           5
   5 | HP                    | 15000.00 |           7
   6 | MSI                   | 20000.00 |           7
   7 | Orta Sehpa            |   600.00 |           9
 102 | Samsung Galaxy 10     | 13000.00 |         952
```

Daha sonra aynı şekilde ürün ekleme ve silme işlemlerini de yapıyoruz.

```java
@SpringBootApplication
public class SpringBootTrainingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTrainingApplication.class, args);

        KategoriEntityService kategoriEntityService = applicationContext.getBean(KategoriEntityService.class);
        UrunEntityService urunEntityService = applicationContext.getBean(UrunEntityService.class);

        kategoriEkleme(kategoriEntityService);
      
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
            System.out.println(urun);
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
        return kategori;
    }

```



Tomcat'in default çalıştığı port'u değiştirmek için **application.properties** içerisine 

```xml
server.port=8181
```

yazmamız yeterli olacaktır.

Spring'i start ettiğimzide gelen SPRING banner'ını değiştirmek istersek

https://devops.datenkollektiv.de/banner.txt/index.html

adresinden istediğimiz yazıyı yazıp verdiği sonucu projemizde

**Resources** klasörü içerisine **banner.txt** içinde ekliyoruz.

Eğer  *title* ve *sürüm*ü de değiştirmek istersek **aplication.properties** içerisinde değerleri verebiliyoruz.

```xml
application.title=n11 bootcamp
application.version=1.0.0
```

-------

Projemize  **controller** adında bir paket ekliyoruz.İçerisine **UrunController** class'ımızı ekleyip bunu **@RestController** olarak işaretliyoruz. İçerisinde kullanacağımız **UrunEntityService** class'ımızı *@Autowired* ile enjekte ediyoruz. Daha sonra bütün ürünlerimizi geri döndüren bir metod yazıyoruz. Endpoint isimlendirmelerinde fiili kullanmıyoruz. Entity Class'larımızın *Serializable* interface'ini implemente etmiş olması gerekiyor.

```java
public class Kategori implements Serializable 
public class Urun implements Serializable
```



```java
package com.asimkilic.springboot.springboottraining.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrunController {
 @Autowired
  private UrunEntityService urunEntityService;
    
  @GetMapping("/urunler")
  public List<Urun> findAllUrunList() {
        return urunEntityService.findAll();
    }
}

```

Bu haliyle istek attığımızda tekrar bir hata alıyoruz, bunun için *Urun* class'i içerisinde bulunan *Kategori* entity'sini ignore etmemiz gerekiyor. *Urun* class'ının üstüne  JsonIgnoreProperties anotasyonunu ekliyoruz.

```java

@Entity
@Table(
        name = "URUN"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "kategori"})
public class Urun implements Serializable
```

Tek bir ürünü getirmek için metod yazıyoruz. Ürün id sini dışarıdan parametre olacak alacağız, bunun için metodumuzda parametre olarak yazdığımız **Long id** başına **@PathVariable** ekliyoruz, bu link üzerinden gelen **{id}** parametresini içerisine atacaktır.

```java
   @GetMapping("/urunler/{id}")
    public Urun findUrunById(@PathVariable Long id) {
        return urunEntityService.findById(id);
    }
```

Normalde elimizdeki entity'leri doğrudan kullanıcılara göndermemeliyiz, bunları DTO'lar ile ihtiyacı olunan verileri paketleyerek göndermeliyiz. Gönderdiğimiz ürünü *UrunDetayDto*'ya  çeviren bir metod yazalım sonrasında dto döndüren bir endpoint tanımlayalım. *KategoriEntityService* 'i field olarak tanımlayıp *@Autowired* olarak işaretliyoruz.

```java
private UrunDetayDto convertUrunToUrunDetayDto(Urun urun) {

        Kategori kategori = kategoriEntityService.findById(urun.getKategori().getId());
        UrunDetayDto urunDetayDto = new UrunDetayDto();
        urunDetayDto.setUrunAdi(urun.getAdi());
        urunDetayDto.setUrunFiyati(urun.getFiyat());
        urunDetayDto.setKategoriAdi(kategori.getAdi());

        return urunDetayDto;
    }
```

Endpoint'imizi tanımlıyoruz:

```java
 @GetMapping("/urunler/dto/{id}")
    public UrunDetayDto findUrunDtoById(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        UrunDetayDto urunDetayDto = convertUrunToUrunDetayDto(urun);
        return urunDetayDto;
    }
```

http://localhost:8080/urunler/dto/3  isteğini attığımızda sonucu bize döndürmesi gerekiyor.

```json
{
    "urunAdi":"Mavi Gömlek",
    "kategoriAdi":"Gömlek",
    "urunFiyati":500.00
}
```

