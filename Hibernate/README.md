## POJO 

Plain Old Java Object 

Sade eski bir java nesnesidir. Java Class'larının en basit halidir.Başka bir sınıftan extend yada implemente olmamış ve constructor'ı oluşturulmamış en basit halidir. Private değişkenlere sahiptir. Ve bu değişkenlere erişimi getter ve setter'lar ile sağlanmıştır yani enkapsüle edilmiştir. 

Her entityde bir PK yani ID değişkeni olmalıdır.

POJO'muzu Entity' e dönüştürmek için bazı ayarlar yapmalıyız. Bu ayarlamaları anotasyonlar ile yapıyoruz. Anotasyonlar, Entity özelliklerini Java kodunda belirlemeye yarar.

En sık kullanılan anotasyonlar:

- @Entity
- @Table
- @Id
- @GeneratedValue
- @SequenceGenerator
- @TableGenerator
- @Column
- @Version
- @Temporal
- @Transient
- @Lob

### @Entity

En temel anotasyon @Entity anotasyonudur.Pojo'nun veri tabanında bir karşılığı olduğunu belirtir. Sınıfların üzerinde tanımlanır.

### @Table 

Table anotasyonu entity anotasyonu ile birlikte kullanılır. Oluşacak tablonun ilgili özelliklerini belirtmek için **@Table** anotasyonu kullanıyoruz.

```java
@Entity
@Table(name="POJO")
public class Pojo{
    
}
```

- ***name*** ile tablo ismini değiştirebiliriz. Kullanılmaz ise Class ismini tabloya verir.
- ***catalog*** ile tablonun kataloğunu
- ***schema*** ile tablonun şemasını
- ***uniqueConstraints*** ile constraintlerini
- ***indexes*** ile index'lerini belirleriz.

### @Id

Her Entity'de mutlaka bulunmalıdır. İlgili Entity'in PK'sının hangisi olduğunu belirler.

### @GeneratedValue

PK'nın değerinin nasıl arttığıını belirlediğimiz anotasyondur.  strategy ve geneator olmak üzere iki özellik sunar.

Dört farklı strateji tipi vardır;

- **AUTO**:  Default olarak *AUTO* ayarlıdır.Strateji belirtilmezse AUTO yazılmış gibi davranır. Bu ayarlama ile arttırma görevi hibernate'e bırakılmış olur.
- **IDENTITY** : Kullanımı en kolaydır ama performans olarak **en iyisi değildir**.  Çünkü her bir *Insert* işlemi için yeni bir değer oluşturur.
- **SEQUENCE** : **En performanslı** tipimizdir. Değer arttırımı bir *sequence* üzerinden yapılır. Eğer belirtilmezse *hibernate* varsayılan sequence üzerinden arttırma yapar. Özelleştirmek istersek SequenceGenerator anotasyonu kullanabiliriz.
- **Table** : Çok fazla kullanımı yoktur. Sıradaki değeri belirtilen tablodan depolar, günceller  ve lazım olduğunda yeni değeri alır. Bu uygulamayı **yavaşlatır.**  Özelleştirilmek istenirse *TableGenerator* anotasyonu kullanılabilir.

### @SequenceGenerator

Kullanılacak sequence'lerin özelliklerini özelleştirmek için kullanılır. Altı farklı özellik sunar;

- **name** : Generator'ın ismi belirtilir.
- **squenceName**: Sequence'in adını belirtir.
- **allocationSize**: Arttırım sayısını belirtir. **Default 50**'dir.
- **initialValue** : Başlangıç sayısını belirtir. **Default 1**'dir.
- **catalog** : Katalogu belirtir.

```java
@Entity
@Table(name="POJO")
public class Pojo{
    @SequenceGenerator(
    	name="generator",
        sequenceName="POJO_ID_SEQ",
        allocationSize=1
    )
    @Id
    @GeneratedValue(
    	strategy=GenerationType.SEQUENCE,
        generator="generator"
    )
    private Long id;
}
```

### @TableGenerator

Değer arttırım yöntemi olarak table kullanır. On bir farklı özellik sunar.

- **name**: Generator'un ismi belirtilir.
- **table** : Tablo adı belirtilir.
- **pkColumnName**: PK kolonun adını belirtir. Değer olarak tablo  adı tutulur.
- **valueColumnName** Kolon adı belirtilir. Değer olarak en son verilen id tutulur.
- **allocationSize**: Arttırım sayısı belirtilir. **Default 50**'dir.
- **initialValue**: Başlangıç numarasıdır. **Default 0**'dır.

### @Column

İlgili değişkenin veri tabanında karşılığı olacak olan kolon ile alakalı ayarları yapabileceğimiz anotasyondur.  

- **Length** ile database'de ayırdığımız alan, **precision** ile tam kısmın **scale** ilede ondalık kısmın basamak sayıları ifade edilir.**name** ile kolon adını ifade ederiz. **updatable** ile veritabanındaki güncellenebilirliğinin ayarı yapılır. **insertable** ile de veritabanına insert edilebilirliğini açıp kapatır.
- ***insertable*** ile ilk atılan kayıtlarda o alanın boş olmasını ve sadece update işlemlerinde kaydedilmesini sağlarız.
- ***updatable*** ile atılan ilk kayıttan sonra o alanın güncellemeye kapatılmasını sağlarız.

### @Version

İlgili tablonun versiyonunu tutar.O anotasyon ile  farklı transition'larda aynı veri üzerinde aynı anda değişiklik yapılmaya çalışıldığında bir kullanıcıya izin verip diğerine optimistik lock hatası verdirir. Bir yerde "row was updated or deleted  another transition " gibi bir hata görülürse, başka birisi aynı veri üzerinde değişiklik yapmıştır.

### @Temporal

Tarih verilerini veri tabanına yazarken hangi formatta yazılacağını belirttiğimiz anotasyondur. Üç tipi vardır:

- **DATE** gün-ay-yıl tutar. (dd.mm.yyyy)
- **TIME**: saat-dakika-saniye tutar.(hh:mm:ss)
- **DATETIME**: Her ikisini de tutar.(dd:mm:yyyy hh:mm:ss)

### @Transient

Pojo'nun içerisinde olmasını istediğimiz fakat veri tabanında karşılığı olmasını istemediğimiz durumlarda kullanılır. Sadece nesnenin yaşam döngüsü süresince ömrü vardır.

### @Lob

- İlgili verinin büyük veri olduğunu belirtiriz.
- *String*, *Blob*, ya da *Clob* alanlar ile kullanılabilir.
- Anlamı -> Large Object - Büyük Nesne

### @OneToOne

*Entity* içinde *Entity* kullanılıyor ve aralarında da bire bir ilişki var ise kullanılır. Örn: ***insan*** ve ***parmak izi*** 

Altı farklı özellik sunar *Fetch*, *cascade*, *mappedBy*, *optional*, *orphanRemoval*, *targetEntity* 

Fetch ile sorgu çekildiğinde join yapılan kolonunda tamamının getirilip getirilmeyeceği belirlenir. Default olarak *Eager* gelir, sürekli birlikte kullanılacak tablolar olmadığı sürece *Eager* yapılmamalıdır.

Altı *cascade* tipi vardır. Bu tipler *ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH*. 

```java
@OneToOne(
	cascade= CascadeType.REMOVE // bu nesne silindiğinde diğer nesne de silinir.
    cascade= CascadeType.MERGE // merge ediliğinde diğer nesne de merge edilir.
    // Tüm işlemlerin diğer nesneyede yapılması istenise ALL seçilir ve silinirse silinir.
    cascade= CascadeType.ALL
    
)
```

mappedBy kullanılırsa, kullanıldığı Entity'deki alan için veri tabanında alan oluşmaz ama Entity üzerinde tutulur.  optional ile aradaki ilişki zorunlu mu değil mi o bu belirlenir, default *true* 'dur.  Yani istenirse boş geçilebilir.Fakat *false* yapılırsa bu alanın doldurulması şart olur. *orphanRemoval* true olursa  ve join yaptığımız alanı değiştirip kaydedersek kaldırılan alan veri tabanından silinir.  targetEntity ilgili join'e ilişkin referans sınıfının belirtilmesine yarar, normalde gerekli değildir, çünkü referans sınıf alanın tipinden, get metodundan ya da collection'ın generic tipinden alınabilir fakat bazı durumlarda bunların hiç birisine ulaşılamaz 

### @ManyToMany

Entity içinde Entity kullanılıyor ve aralarında da çoka çok bir ilişki varsa kullanılır. Kitap ve Yazar diye iki nesnemiz olduğunu varsayarsak bir kitabın birden fazla yazarı olabilir, aynı şekilde bir yazarın birden fazla kitabı olabilir. Dört farklı özellik sunar; *fetch*, *cascade*, *mappedBy* ve *targetEntity*. 
Fetch ile sorgu çekildiğinde Join yapılan kolonunda tamamının getirilip getirilmemesi belirlenir. Default olarak *Eager* gelir. Sürekli birlikte kullanlacak tablolar olmadığı sürece **FetchType.LAZY** yapılmalıdır. Altı farklı cascade tipi vardır. Bu tipler *ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH*.  **OneToOne** ilişkisinde ki işlevleri ile aynıdır. *mappedBy* çift yönlü ilişkilerde kullanılır. Kitap-Yazar örneğinde Kitap nesnesinde Yazar, Yazar nesnesinde de Kitap var. mappedBy kullanılırsa, kullanıldığı alan için veri tabanında alan oluşmaz ama Entity üzerinde bu alan tutulur. targetEntity ilgili ilişkin referans sınıfının belirtilmesine yarar normalde gerekli değildir, çünkü referans sınıf alanın tipinden, get metodundan ya da collection'ın generic tipinden alınabilir fakat bazı durumlarda bunların hiç birisine ulaşılamaz.

### @ManyToOne

Yine Entity içerisinde Entity kullanılıyor ise ve aralarında da çoka bir ilişki var ise kullanılır.**En sık kullanılan** ilişki biçimidir. *Şehir* ve *Ülke* diye iki nesnemizin olduğunu düşünelim. Bir ülkenin birden fazla şehiri olur fakat bir şehirin bir tane ülkesi olur.  Dört farklı özellik sunar. *fetch*, *cascade*, *optinal* ve *targetEntity*.  
fetch ve cascade kullanımı önceki anotasyonlarda bulunan kullanım ile aynıdır.  optional ile aradaki ilişki zorunlu mu değil mi o belirlenir, default true'dur. targetEntity ilgili ilişkin referans sınıfının belirtilmesine yarar, önceki anotasyonlarda bulunan kullanım ile aynıdır.

### @OneToMany

Entiy içerisinde Entity kullanılıyor ise ve aralarında bire çok ilişki var ise kullanılır. Ülke ve Şehir nesnelerimiz olduğunu varsayarsak bir ülkenin birden fazla şehiri olur fakat bir şehir bir ülkeye aittir. Burada şehir ve ülke arasında **OneToMany** ilişkisi kurarız. Beş farklı özellik sunar  *fetch*, *cascade*, *mappedBy*, *orphanRemoval* ve *targetEntity*.  mappedBy kullanılmazsa veritabanında UlkeSehir adında bir ara tablo oluşturulacak. orphanRemoval true olursa ve join yaptığımız alanı değiştirip kaydedersek, kaldırılan alanda veritabanından silinir. Geriye kalan özellikler ise *ManyToOne* anotasyonunda bulunan kullanım ile aynıdır.

### @JoinColumn

Entity içerisinde Entity kullanılması durumunda kullanılır. Join yapacağımız tablo ile ilişki belirtmeye yarar. Dokuz farklı özellik sunar.

- name : Oluşacak kolonun adını  belirtiriz.
- referenceColumnName : Id dışında başka bir kolon ile join yapılacaksa o kolonu belirtiriz.
- unique : O kolonun  unique olup olmadığını belirtiriz.
- nullable : Boş olup olamayacağını belirtiriz.
- updatable :Update edilip edilemeyeceğini belirtiriz.
- insertable : Insert edilip edilemeyeceğini belirtiriz.
- columnDefinition : Kolon için oluşturulan DDL'e eklenecek SQL'i.
- table : tablosunu 
- foreignKey : foreignKey'ini belirtiriz.  

Eskiden *foreignKey* anotasyonu tek başına kullanılabiliyordu. Fakat bu anotasyon **deprecated** olmuştur ve artık tek başına kullanmamak gerekir. Bunun yerine bazı anotasyonların içinde *foreignKey* anotasyonu kullanılabilir. Örneğin *JoinColumn* anotasyonu içerisinde ilgili kolona ait *foreignKey*'i özelleştirmek için kullanılabilir. *foreignKey*  constraint'leri *hibernate* tarafından oluşturulurken, oldukça okunaksız isim ile oluşturulurlar, bu anotasyon sayesinde kendi özel isimlerimizi verebiliriz. Üç farklı özellik sunar name, foreignKeyDefinition, value. 

- **name** ile isim özelleştirilebilir

- **foreignKeyDefinition** ile ilgili *foreignKey* için tanımlamalar yazılabilir.

- **value** ile constraint mod belirlenebilir. Constraint mod'un 3 değeri vadır

  - **constraint** : constraint'leri uygular.
  - **noConstraint** : constraint'leri uygulamaz.  
  - **providerDefault** : provider'da tanımlı, varsayılan davranışı benimser.

   

### @Index

Eskiden tek başına bu anotasyon kullanılabilirdi fakat  **deprecated** olmuştur ve artık tek başına kullanmamak gerekir. Bunun yerine ***@table*** anotasyonu içerisinde kullanılabilir. Üç farklı özellik sunar:

- **name** : İsim özelleştirmeye yarar.
- **columnList** : Eklenen *index*'in hangi kolonlara ait olacağı belirtilir. Kolon adlarını virgüllerle ayırarak yazabiliriz.
- **unique** :  *Index*'i eşsiz yapabiliriz, varsayılan olarak 'false' dur. True yapılırsa *index*'leme için seçilen verileri bir daha kullandırmaz. Veriye erişim hızını arttırır.

```java
@Entity
@Table(name = "SEHIR", indexes={
    @Index(name = "IX_SEHIR_ULKE", columnList = "ID_ULKE"),
    @Index(name = "IX_SEHIR_ADI_ID", columnList = "ADI, ID", unique = true)
})
public class Sehir{
    //......
}
```



## Powershell üzerinden Postgresql'e bağlanmak için

```powershell
PS C:\Users\ofn2nvu> psql -U postgres
postgres=# \c N11_BOOTCAMP
N11_BOOTCAMP=# select * from urun;
```

\c  <veritabanı adı >   (psql'e giriş yaptıktan sonra veritabanı seçme) 
\connect <veritabanı adı>   (psql'e giriş yaptıktan sonra veritabanı seçme)   
psql -d <veritabanı adı> -U <kullanıcı adı> -W  (belirtilen veri tabanına belirtilen kullanıcı ile bağlanır)
\l (varolan dbleri listeler)
\dt (tabloları listeler)
\d+  <tablo adı>  (tablodaki kolonları ve detayları listeler (tipleri unique'ler vs.)
\q  çıkış