Java, söz dizimi açısından C++'a çok benzeyen ve temelde Smaltalk ve C++ kültürünü benimseyen bir dildir.
Java, dinamik tipli (dnamically-typed) değildir, aksine **statik** tipli (statically-typed) bir dildir. Java'nın dinamik olmasıyla kastedilen, tipleri çalışma zamanın (runtime) yükleyebilmesidir. Dolayısıyla Java'da bir programın kullanacağı tipler, uygulamanın derlenmesi ya da ayağa kalkmasında hazır olması gerekmez.
**Mimari olarak tarafsız **(architectural-neutral) olması Java'nın herhangi bir platforma(donanım ve işletim sistemi) bağımlı **olmaması** demektir. Java standartlar üzerine kurulmuştur, örneğin int her platformda 32-bittir.
Java'nın platformlardan bağımsız olması ve yorumlanan (interpreted) tabiatı, onu aynı zamanda **taşınabilir** (portable) kılmaktadır. Derlenmiş (compiled) Java kodu, .class dosyasında bytecode olarak saklanır. Bytecode, platfformdan bağımsızdır. JVM'in olduğu her ortamda çalıştırılabilir. **Write Once, Run Anywhere, Write Once Test Anywhere**

Yazılımda her tip nesne için, verisi ile davranışını bir paket haline getirip sarmalayan ya da kapsülleyen (encapsulation) ve adına genelde sınıf (class) denen kalıplar oluşturulur.
**Sınıf = Veri + Daveranış**
Nesneler, sınıflardan üretilmiş **çalışma zamanı** yapılarıdır.

**Java hem derlenir (compiled) hemde yorumlanır (interpreted)**. Java kaynak kodları (java uzantılı dosyalar) doğrudan makina koduna derlenmez, **bytecode**lardan oluşan *class* uzantılı dosyalara derlenir. Bytecode'lar ise çalışma zamanında JVM tarafından yorumlanır.

![image-20220201183341740](C:\Users\ofn2nvu\AppData\Roaming\Typora\typora-user-images\image-20220201183341740.png)

Bytecodelardan oluşan *class* dosyası, platform bağımsızdır ve JVM'in olduğu herhangi bir platformda JVM tarafından yorumlanır, çalıştırılır.

![image-20220201183408485](C:\Users\ofn2nvu\AppData\Roaming\Typora\typora-user-images\image-20220201183408485.png)

Bytecodelar JVM'in komutlarıdır. Bir byte uzunluğunda oldukları için böyle adlandırılmıştır.

**JVM, Java Virtual Machine**

- Sanal bir makinadır.
- Derlenmiş Java kodlarını, bytecode, çalıştıran sitemdir.
- Genelde içinde Just-in-Time Compiler barındırır.
- JRE'nin (Java Runtime Environment) ana parçasıdır.
- Genel olarak C/C++ ve Java ile yazılır.

**Java platform**

JVM'i, geliştiriciye bakan yüzü standart olan ama platforma bakan yüzü, onunla konuşabilecek şekilde, geliştiriciyi platformdan soyutlayan bir ara katman olarak düşünebiliriz.

Java'da kaynak kodu (source code) derlemek (compile) için, Java derleyicisi (Japa compiler) kullanılır.
Java derleyicisi **javac** komutu ile çalıştırılır.
Java derleyicisi *java* uzantılı kaynak kodundan *class* uzantılı derlenmiş bytecode üretir.

```powershell
javac MyFirstApp.java
```

Java'da ancak içinde main metot olan sınıflar çalıştırılabilirdir (executable,runnable).Dolayısıyla komut satırınca *java* komutuyla JVM'i ayağa kaldırıp ona MyFirstApp'i geçeceğiz.

```powershell
java MyFirstApp
```

Eğer **javac** ile derlerken yada **java** ile çalıştırırken **Error: Could not find or load class XXX** vb. bir hata alıyorsak, bu classpath problemidir.

```powershell
>javac -classpath . MyFirstApp.java
>java -classpath . MyFirstApp
```

**Bytecode**

**javap**, JDK ile birlikte gelen ve *class* dosyasının içeriğini, bytecodelarını gösteren bir araçtır.

```powershell
>javap -c SelamTest.class
```



![image-20220202112516384](C:\Users\ofn2nvu\AppData\Roaming\Typora\typora-user-images\image-20220202112516384.png)

**Java'nın tipleri**

Java'nın 3 farklı  tür uygulamaya yönelik 3 farklı paketi vardır:

- **Standart Java (Standar Edition,SE)**, çekirdek dildir.
- **Mikro Java (Micro Edition, ME)**, gömülü ve mobil ortamlar içindir.
- **Kurumsal Java (Enterprise Edition, EE)** kurumsal uygulamalar içindir.

![image-20220202113102067](C:\Users\ofn2nvu\AppData\Roaming\Typora\typora-user-images\image-20220202113102067.png)

***goto*** ve ***const*** dilde kullanılmamaktadır, bir anlamları yoktur ama en başından bu yana ayrılmış (reserved) kelimelerdir.

Java'da isimler "sadece harf ile başlayabilir" kuralının iki istisnası vardır:
İsimler $ € ₺ gibi para birimi sembolleri ile de başlayabilir.
İsimler _ (alt çizgi) ile de başlayabilir.

"$", Java tarafından otomatik olarak üretilen isimlerin başında kullanıldığından, kaçınılmasında fayda vardır.

**Public** sınıf, kendi ismini taşıyan bir dosyada yer almak zorundadır. Yani her kaynak kodu dosyasında en fazla bir tane **public** sınıf olabilir.

Java'da değişkenler, ilkel olsun referans olsun, fonksiyonellik ya da rol açısından üçe ayrılırlar:

- **Nesne değişkenleri (instance** yada **object variables**): Nesnenin durumunu (state) oluşturan değişkenlerdir.
- **Sınıf değişkenleri(class variable)**: Nesnelerin ortak durumunu ifade eden değişkenlerdir.
- **Yerel değişkenler (local variables**): Geçici değişkenlerdir.

**Abstact Data Types**

ADT,  bir veri yapısının modeli olarak çağırılabilir.
Interface'ler ADT'dir.
ADT'ler tamamen implementasyonu olmayan yapılardır. Fonksiyonların gövdesi olmaz. 
Son kullanıcı sadece metodları bilir. 

**Data Structure**

Asıl implementasyonlara veri yapıları diyoruz. 

ADT								 Data Type
Stack						  	 Array,Linked List
Queue						 	 Array,Linked List
Priority Queue 				Heap
Dictionary / Hashmap	Array

