# Class

- Bir veri tipidir.

- Class'lar ile nesnelerin şablonunu çıkartırız.

- Class'lar yaşamaz bir ömrü yoktur,bellekte yer tutmazlar.

  ## Object 

  - Class'tan bir örneğin (instance) oluşturulmuş halidir.

Java default olarak bizim yerimize parametresiz constructor'ı tanımlıyor.  Fakat biz parametreli bir constructor tanımladığımız zaman, bunun parametresiz halinide kendimizin  tanımlaması gerekiyor.

Instance'ı oluşturulmuş her class bir object'tir. Default olarak Object'i extend eder.

Shallow Copy: Yüzeysel kopyalama olarak da adlandırılır. Bu yöntemde ilk nesne üzerinden **farklı** bir nesne oluşturulur fakat ilk nesne içerisindeki değerlerin bellek referansı aynı olacak şekilde kopyalanır. ***Clone*** metodu varsayılan olarak yüzeysel kopya oluşturmamızı sağlar.

Primitive tipler direk **stack** üzerinde tutulurken nesne tipindeki veriler **Heap** üzerinde tutulur ve bunun referansı **stack** üzerinde tutulur. Kopya işleminde primitive tipller referans olarak kopyalanmazlar direk değerleri ile kopyalanırlar. Bu nedenle kopya üzerinde yapılan değişiklikler ilk nesne üzerine yansımaz.

Deep Copy: Detay kopya olarak da adlandırılır. Nesnenin değerlerinin ve tamamının içerikleri ile kopyalanması ve yeni bellek referansı oluşturulmasını ifade eder. Kopya üzerindeki nesneler farklı bellek referanslarını işaret edeceği için kopya nesne üzerinde yapılan değişiklikler ilk nesneyi etkilemeyecektir.

(https://sezertanriverdi.medium.com/shallow-copy-vs-deep-copy-k%C4%B1sa-bak%C4%B1%C5%9F-9955720db1c8)

Alt + insert => quick actions menu



## Object

- clone()  : Bu nesnenin aynısını kopyalar
- equals(Object obj) : obj nesnesi, bu nesneye eşit mi kontrolü yapar.
- finalize(): İlgili nesne bellekten silinmeden hemen önce çağırılan yordam.
- getClass() : Bu nesnenin çalışma anında sınıf bilgilerini geri döner.
- hashCode(): Bu nesnenin has kodunu geri döner.
- notify(): Bu nesnenin bekleme havuzunda olan tek iş parçacığını(thread) uyandırır.
- notifyAll(): Bu nesnenin bekleme havuzundaki tüm iş parçacıklarını uyandırır.
- toString(): Bu nesnenin String tipinden ifadesini geri döner.
- wait(): O andaki iş parçacığının (thread) beklemesini sağlar, bu bekleme notify() veya notifyAll() yordamları sayesinde sona erer.
- wait(long timeout): O andaki iş parçacığının belirtilen süre kadar beklemesini sağlar, bu bekleme notify() veya notifyAll() yordamları sayesinde de sona erebilir.
- wait(long timeout, int nanos): O andaki iş parçacığının belirtilen süre kadar beklemesini sağlar, bu bekleme notify() veya notifyAll() yordamları sayesinde de sona erebilir. 

**setter** gönderdiğimiz değeri direkt olarak **set** etmemiz için kullandığımız metot eğer -1 gibi bir değer girdiğimizde kabul etmeyecekse bunu ona göre isimlendirmeliyiz. Örn; ***controlAndSetCountOfRoom*** gibi.

-equals(Object obj), hashCode() metotlarını override ederken dikkatli olunması gereklidir. hashCode listelerdeki sıralamalara kadar etki eder.

Elimizdeki bir örneğin spesifik bir Class'tan oluşturulup oluşturulmadığını anlamak için

```java
if(ev instanceOf Ev){
    /* karar yapısını kullanabiliriz.*/
}
```

## Inheritance (Kalıtım)

- **extends** anahtar sözcüğü kullanılarak yapılır.

```java
public class Good extends Product{
    
}
```

- Interface'ler davranışları belirler, sözleşmeler diye de adlandırılır.
- Bir class sadece bir class'tan kalıtım alabilir.
- interface'ler interface'leri extend eder.
- class'lar interface'leri implement eder.
- class'lar class'ı extend eder.

> When to use Long vs long in java?
>
> long is a primitive, which must have a value. Simple.
>
> Long is an object, so:
>
> it can be null (meaning whatever you like, but "unknown" is a common interpretation)
> it can be passed to a method that accepts an Object, Number, Long or long parameter (the last one thanks to auto-unboxing)
> it can be used an a generic parameter type, ie List<Long> is OK, but List<long> is not OK
> it can be serialized/deserialized via the java serialization mechanism
> Always use the simplest thing that works, so if you need any of the features of Long, use Long otherwise use long. The overhead of a Long is surprisingly small, but it is there.
> https://stackoverflow.com/questions/21034955/when-to-use-long-vs-long-in-java

 ## Composition (Bileşim)

- belongs-to & has-a 'de denilebilir.
- Ev <> Oda
- Bağ çok güçlü,biri olmadığında diğeri de olmaz
- Oda belongs to Ev
- Ev has an Oda
- Bir nesnenin içerisinde başka bir nesnenin kullanılması. ( Bağ olmak zorunda)

## Aggragation (Bir araya gelme, Toplanma)

- Composition'a benzer
- Has-a
- Sahip olma durumu yoktur.
- EvSahibi <> Kiracı
- Bağ güçlü fakat ayrıldıklarında hala anlam taşırlar.

## Association (Bağlantı, Birlik, Ortaklık)

- Aggregation ile aynı yapıdadır.
- En zayıf ilişkidir.
- is-a ya da has-a değildir.
- İki nesne yalnızca birbirinden haberdardır.
- Ev <> EvSahibi
- Sadece ihtiyaç durumlarında bir araya gelirler.

