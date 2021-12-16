## HTTP Request Methods

* **GET**  : Sadece data alındığı durumlarda kullanılır.
* **POST** : İstek yapılan yere bir data göndermek için kullanılır.
* **HEAD** : *GET* gibidir ama buradan payload dönmez. Yani bir data var mı  yok mu bunu kontrol etmek için kullanılabilir.
* **PUT** : İstek yapılan yerdeki bir veriyi güncellemek için kullanılır.
* **DELETE** Bir daha silmek için kullanılır.
* **CONNECT** : İstek yapılacak yere bir tünel oluşturmak için kullanılır.
* **OPTIONS**  : İletişim ayarlarını tanımlar.
* **TRACE** : Yapılan istek, istek yapılan yere ulaşana kadar hangi değişikliklere uğradı bunu belirtir. Hedef kaynağa giden yol boyunca bir mesaj geri döngü testi gerçekleştirir diyebiliriz.
* **PATCH** : *PUT* gibidir, fakat *PUT*'ta tüm kaynağı göndermeniz gerekirken *PATCH* ile sadece kaynakta değiştirmek istediğiniz kısmı göndererek güncelleme işlemi yapabilirsiniz.

Arka planda yapılan işlemleride *Console* ekranından görmek istiyorsak **Resources>application.properties** içerisinde log level'ı *debug* olarak değiştiriyoruz.

```java
logging.level.org.springframework=debug
```

RestController içerisinde metodlarımızın üzerinde her seferinde o controller'a ait olan isimi yazmak yerine bunu bir sefer direk olarak Controller Class'ımızın üzerine eklememiz yeterli olacaktır.

```java

@RestController
@RequestMapping("/api/urunler")
public class UrunController {

```

bundan sonra metodların endpoint isimlendirmesi yapılırken */api/urunler* başına otomatik olarak eklenecektir.

```java
@GetMapping("/{id}")
    public Urun findUrunById(@PathVariable Long id) {
        return urunEntityService.findById(id);
    }
```

Bu endpointe ulaşmak için gerekli adres : **localhost:8080/api/urunler/3**



Post işlemleri yapılırken kullanıcıdan alınan tip direkt olarak veri tabanı nesnemize karşılık gelmemeli. Onun yerine *DTO* kullanmalıyız.

```java
   @PostMapping
    public Urun saveUrun(@RequestBody UrunDto urunDto) {
        Urun urun = convertUrunDtoToUrun(urunDto);
        urun = urunEntityService.save(urun);
        return urun;
    }
   private Urun convertUrunDtoToUrun(UrunDto urunDto) {
        Kategori kategori = kategoriEntityService.findById(urunDto.getKategoriId());
        Urun urun = new Urun();
        urun.setAdi(urunDto.getAdi());
        urun.setFiyat(urunDto.getFiyat());
        urun.setKayitTarihi(urunDto.getKayitTarihi());
        urun.setKategori(kategori);
        return urun;
    }
```



Biz bu gönderdiğimiz veriyi nereye kaydettik, geri nasıl ulaşabiliriz bununla ilgili bilgimiz yok şuan

```java
 @PostMapping("")
    public ResponseEntity<Object> saveUrun(@RequestBody UrunDto urunDto) {
        Urun urun = convertUrunDtoToUrun(urunDto);
        urun = urunEntityService.save(urun);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}").buildAndExpand(urun.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

```

​	Post isteği atıldıktan sonra Headers içerisinde **Location** alanında kaydettiğimiz veriye ulaşacabileceğimiz adresi geri gönderir.

Delete işlemi için **@DeleteMapping** anoasyonunu kullanıyoruz

```java
   @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable Long id) {
        urunEntityService.deleteById(id);
    }
```

Şuan için doğru **id** gönderiminde sorun oluşmadı fakat bize olmayan bir **id** gönderildiği zaman runtime exception fırlatabiliriz yada kendi **Exception**'ımızı da yazabiliriz.

**exception** paketi oluşturuyoruz içerisine **UrunNotFoundException** class'ı oluşturuyoruz. Class'ımızı RuntimeException'dan extend ediyoruz ve parametre olarak aldığımız *String message*'ı base class'a gönderiyoruz.

```java
package com.asimkilic.springboot.springboottraining.exception;

public class UrunNotFoundException extends RuntimeException {
    public UrunNotFoundException(String message) {
        super(message);
    }
}

```

ve Delete metodumuzu buna göre düzenleyecek olursak ;

```java
  @DeleteMapping("{id}")
    public void deleteUrun(@PathVariable Long id) {
        Urun urun = urunEntityService.findById(id);
        if(urun == null){
    throw new UrunNotFoundException("Urun not found. id: "+ id);
        }
    }
```

Bu şekilde istek attığımızda bize 500 hata kodu döndürüyor. Biz **Not Found** döndürmesini istiyoruz bunun içinde *Exception* class'ımıza gidip **@ResponseStatus** anotasyonu ile döndürmesini istediğimiz kodun enum karşılığını seçiyoruz.

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UrunNotFoundException extends RuntimeException {
    public UrunNotFoundException(String message) {
        super(message);
    }
}

```

 

Peki biz her hata için sürekli sürekli exception mı döndüreceğiz? Bunu bir yerde yakalayamaz mıyız ?  Evet yakayabiliriz. *Exception* paketimizin içerisine **ExceptionResponse** class'ı ekliyoruz.

Biz artık kendi istediğimiz formatta hata döndüreceğiz, ne hatası olursa olsun. ExceptionResponse class'ını kendi istediğimiz formata göre yazıyoruz. 

```java
package com.asimkilic.springboot.springboottraining.exception;

import java.util.Date;

public class ExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date errorDate, String message, String detail) {
        this.errorDate = errorDate;
        this.message = message;
        this.detail = detail;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

```

Biz her zaman bu tipte bir Exception döndüreceğiz. *Exception* paketinin içerisine **CustomizedResponseEntityExceptionHandler** adında bir class daha ekliyoruz ve bunu **ResponseEntityExceptionHandler** 'dan extend ediyoruz.

Bu class'ımızı **@ControllerAdvice** ve **@RestController** olarak işaretliyoruz.

İçerisinde bir tane metodumuz olacak bu herşeyi handle etsin. Metodumuzu **@ExceptionHandler**  olarak işaretliyoruz.

```java
 
    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


```

içerisinde kendi yazdığımız **ExceptionResponse** nesnesi oluşturup içerisini dolduruyoruz. Ve **ResponseEntity**'den içerisine  oluşturuduğumuz nesneyi  ve durum kodunu vererek bir örnek oluşturup *return* ediyoruz.

Bu haliyle hatalı bir istek attığımızda bize HTTP-500 durum koduyla geri dönen değer:

```json
{
    "errorDate": "2021-12-15T14:26:08.042+00:00",
    "message": "Urun not found. id: 2521",
    "detail": "uri=/api/urunler/2521"
}
```

Bizim **UrunNotFoundException**'ımızıda yakalaması için **CustomizedResponseEntityExceptionHandler** class'ımızda **handleAllExceptions** metodumuzu *overload* ediyoruz.

```java
   @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(UrunNotFoundException ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
```

## HATEOAS

Uygulamamızda yapılan işlemler ile ilgili yardımcı linkler gönderebiliriz. Bunu Hateoas ile yapabiliyoruz, ilk olarak *POM.xml* içerisine gerekli dependency mizi ekliyoruz.

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

Diyelim bir ürünün detayına ulaşan birisine tüm ürünlerede nereden ulaşacağının bilgisini geçelim.

İlk olarak WebMvcLinkBuilder ile bir link oluşturacağız, linkTo medodu içerisine seçtiğim metoda giden link bana oluştur diyeceğiz, bunun için metodumuzun bulunduğu class'ı ve hangi metodu istediğimizi belirtiyoruz.  Yaptığımız bu işlemleri bir yerel değişkene atıyoruz elimizde artık o metoda nasıl erişileceğinin linki var fakat biz bunu return ile birlikte göndermek istiyoruz tam bu sırada da devreye EntityModel giriyor. Bir örnek oluşturup *of* metodu içerisine normal geriye döndüreceğimiz nesnemizi veriyoruz, ve ardından onun üzerine **.add** ile oluşturduğumuz linki bir title ile veriyoruz, geriyede bu modelimizi döndürüyoruz.

```java
@GetMapping("/{id}")
public EntityModel<Urun> findUrunById(@PathVariable Long id) {
    Urun urun = urunEntityService.findById(id);
    if (urun == null) {
        throw new UrunNotFoundException("Urun not found. id: " + id);
    }
    WebMvcLinkBuilder linkToUrun = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass())
                    .findAllUrunList()
    );
    EntityModel entityModel = EntityModel.of(urun);
    entityModel.add(linkToUrun.withRel("tum-urunler"));
    return entityModel;
}
```

Postman ile istek attığımız da dönen sonuç:

```json
{
    "id": 1,
    "adi": "Mavi Elbise",
    "fiyat": 200.00,
    "kayitTarihi": "2021-12-12T07:14:06.361+00:00",
    "_links": {
        "tum-urunler": {
            "href": "http://localhost:8080/api/urunler/"
        }
    }
}
```

## Swagger

OpenAPI'nın oluşturmuş olduğu bir dökümantasyon aracıdır.

```xml
	<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.5.13</version>
	 </dependency>
```

Kurulumdan sonra projemizi çalıştırıp

http://localhost:8080/swagger-ui.html   adresine girdiğimiz de bize uygulamamızın dökümantasyonunu çıkatır, burada bütün controller ve içlerinde metodlar'ı görebiliriz, ayrıca bu metodlara istek gönderip  deneyebiliriz.

Bize hazırladığı JSON formatındak dökümantasyonu formatlamadan alıp, https://editor.swagger.io adresinde yazdığımızda bize metodlarımızın vs. bir simülasyonunu çıkaracaktır.



##  @JsonFilter

**@JsonIgnoreProperties** kullandığımızda istediğimiz zaman değil her zaman  ilgili field'ın gelmesini engelliyorduk. Fakat JsonFilter ekleyerek gelmesini istemediğimiz alanları filteleyerek daha esnek bir yapıya sahip oluruz.

Kulllanmak istediğimiz Entity Class'ımıza, bir isim vererek ekliyoruz. 

```
@JsonFilter("UrunFilter")
public class Urun implements Serializable
```

Kullanımı ise; 

```java
@GetMapping("")
public MappingJacksonValue findAllUrunList() {
    List<Urun> urunList = urunEntityService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "adi", "fiyat");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("UrunFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(urunList);
        mapping.setFilters(filters);
     
    return mapping;
}
```

*filterOutAllExcept*: Gelmesini istediğimiz alanların isimlerini yazıyoruz.

*.addFilter* : içerisine Entity Class'ımızda filtremize verdiğimiz isim ve filterOutExcept 'i atadığımız değişkeni veriyoruz.

Daha sonra **MappingJacksonValue** 'dan bir örnek oluşturup, constructor'una filtrelemek istediğimiz listemizi veriyoruz, daha sonra listemizi verdiğimiz değişkene **.setFilters** ile yazmış olduğumuz filtremizi uyguluyoruz ve return ediyoruz.

## @JsonIgnore

Eğer bir field'ımızın  hiç bir zaman gelmesini istemiyorsak, doğrudan onu **@JsonIgrore** olarak işaretlememiz yeterli olacaktır.

## @Transient 

Veri tabanında tutulmasını istemediğimiz ama entity nesnemiz içerisinde yer almasını istediğimiz field'larımızı **@Transient** olarak işraretliyoruz.

## Security 

```xml
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>	</dependency>
```

Paketini kurup projemizi çalıştırdığımızda bize 
<b>Using generated security password: 03d4c9c2-a92f-4565-99b1-0cfac84d9b0b</b> 
adında bir şifre oluşturuyor otomatik olarak.

Postman'e gelip istek attığımızda bize  <span style="color:red">401 Unauthorized</span>  sonucunu döndürüyor.

Postman'de **Authorization** tab'ına gelip **Type: Basic Auth** seçiyoruz. 

**Username**  : user  (default user olarak atıyor.)
**Password**: 03d4c9c2-a92f-4565-99b1-0cfac84d9b0b ( Her programı re-run ettiğimizde yeni bir değer üretir.)

tekrar *GET* isteği yaptığımızda değerlerimiz artık geliyor. Eğer her seferinde şifrenin değişmesini istemiyorsak **application.properties** içerisinde default kullanıcı adı ve şifre verebiliyoruz.

```xml
spring.security.user.name=asimkilic
spring.security.user.password=12345
```

## Mapstruct

Properties içerisine version bilgisini ekliyoruz.

```xml
	<properties>
	<java.version>11</java.version>
	<org.mapstruct.version>1.4.2.Final</org.mapstruct.version>
	</properties>
```

Daha sonra Dependency'leri ekliyoruz.

```xml
<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>${org.mapstruct.version}</version>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct-processor</artifactId>
			<version>${org.mapstruct.version}</version>
</dependency>
```

Şuan sadece projemizde kullanılabilir hale geldik.

**UrunConverter** adında bir tane *interface* oluşturuyoruz. Bu bizim mapper'ımız olacak o yüzden bunu **@Mapper** anostasyonu ile işaretliyoruz ve içerisine parametre olarak **unmappedTargetPolicy** veriyoruz bu parametre, eğer bir alanı bir nesneden diğerine map edemezse ne yapılması gerektiğini belirtir.

```java
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrunConverter {
    // Herhangi bir implementasyon olmadan direk kullanılabilsin diye INSTANCE'ı oluşturuyoruz.
 UrunConverter INSTANCE = Mappers.getMapper(UrunConverter.class);

    @Mapping(source = "kategoriId", target = "Kategori.id")
     Urun convertUrunDtoToUrun(UrunDto urunDto);

}

```

kaynak ve hedef nesnelerde alan isimleri aynı ise bunun için ekstra bir parametre vermemize gerek yok ama farklı isimde iki alan varsa bunu metodumuzun üzerinde **@Mapping**  anotasyonunda belirtiyoruz.

```java
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrunConverter {
 UrunConverter INSTANCE = Mappers.getMapper(UrunConverter.class);
   
    @Mapping(source = "kategoriId", target = "kategori.id")
    Urun convertUrunDtoToUrun(UrunDto urunDto);

    @Mapping(source="fiyat",target="urunFiyati")
    @Mapping(source="adi",target="urunAdi ")
    @Mapping(source="kategori.adi",target="kategoriAdi")
    UrunDetayDto convertUrunToUrunDetayDto(Urun urun);
}

```



Kullanmak istediğimiz zaman *INSTANCE*'ı nı alıyoruz ve ilgili metodu çağırıp dönüştüreceğimiz nesnemizi parametre olarak veriyoruz, sonucuda  hedef tipte olan nesnemize aktarıp kullanıyoruz.

```java
@GetMapping("/dto/{id}")
public UrunDetayDto findUrunDtoById(@PathVariable Long id) {
    Urun urun = urunEntityService.findById(id);
    UrunDetayDto urunDetayDto = UrunConverter.INSTANCE.convertUrunToUrunDetayDto(urun);
    return urunDetayDto;
}
```

*CrudRepository* *Repository*'den extend olan bir interface.

**PagingAndSortingRepository** 'de  *CrudRepository*'den extend oluyor.

- Iterable< T > findAll(Sort sort);
- Page< T > findAll(Pageable pageable);

metodlarına sahip.

**JpaRepository** ise hem *PagingAndSortingRepository*'den hemde *QueryByExampleExecutor*'dan extend oluyor, ikisinin yaptığı herşeyi yapıyor ekstradan findAll, findAllById, saveAndFlush, flush gibi metodları barındırıyor.

*KategoriDao'da metodumuzu tanımlıyoruz*

```java

@Repository
public interface KategoriDao extends JpaRepository<Kategori,Long> {
    
    List<Kategori> findAllByUstKategoriIsNull();
}

```

*KategoriEntityService'de de metodumuzu  yazıyoruz*

```java
public List<Kategori> findAllByUstKategoriIsNull(){
    return kategoriDao.findAllByUstKategoriIsNull();
}
```

*En son SpringBootTrainingApplication class'ımızda verileri ekrana yazdırıyoruz*

```java
List<Kategori> kategoriList = kategoriEntityService.findAllByUstKategoriIsNull();
for (Kategori kategori : kategoriList) {
    System.out.println(kategori.getAdi());
}
```

*Bu metodumuz bize üst kategorisi null olanları getirecek, eğer veritabanından üst kategorisi null olanlara bakacak olursak ;*

```powershell
Şu anda "postgres" veritabanına "postgres" kullanıcısı ile bağlısınız.
postgres=# select * from kategori where id_ust_kategori is null;
 id |    adi     | kirilim | id_ust_kategori
----+------------+---------+-----------------
  1 | Moda       |       1 |
  2 | Elektronik |       1 |
  3 | Ev & Yaşam |       1 |
(3 satır)


postgres=#
```

*Java çıktımız ise;*

```java
Hibernate: select kategori0_.id as id1_0_, kategori0_.adi as adi2_0_, kategori0_.kirilim as kirilim3_0_, kategori0_.id_ust_kategori as id_ust_k4_0_ from kategori kategori0_ where kategori0_.id_ust_kategori is null
Moda
Elektronik
Ev & Yaşam

```

*Query anotasyonu ile özelleştirilmiş sorgularda yazabiliriz*

```java
    @Query("select kategori from Kategori kategori where kategori.ustKategori.id=2")
    List<Kategori> findAllByUstKategoriIsTwo();
```

*Entity içerisinde Entity sorguları için*

**Dao**

```java

public interface UrunDao extends JpaRepository<Urun, Long> {
    List<Urun> findAllByKategori_IdOrderByIdDesc(Long kategoriId);
}

```

**Service**

```java
 public List<Urun> findAllByKategori_IdOrderByIdDesc(Long id) {
        return urunDao.findAllByKategori_IdOrderByIdDesc(id);
    }
```

**Controller**

```java
    @GetMapping("kategoriler/{kategoriId}")
    public List<UrunDetayDto> findAllUrunByKategoriId(@PathVariable Long kategoriId){
        List<Urun> urunList = urunEntityService.findAllByKategori_IdOrderByIdDesc(kategoriId);
        
        List<UrunDetayDto> urunDetayDtoList = UrunConverter.INSTANCE.convertAllUrunListToUrunDetayDtoList(urunList);
        return urunDetayDtoList;

    }
```



