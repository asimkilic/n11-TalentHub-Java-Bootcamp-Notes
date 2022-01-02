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





**Polymorphism**, çok çeşitlilik demektir. Çok şekilli olan referanslardır. bir referansın, zamanın farklı anlarındai kendi yada alt tipinden olan nesneleri gösterebilmesine denir. Polymorphism bir yaklaşımdır, upcasting ise onu gerçekleştiren mekanizmadır.
**Abstraction**, Soyut sınıflardan somut sınıflar oluşturulmasıdır.
**Heap ve Stack**, Stack static yer tahsisi için kullanılırken, heap dinamik yer tahsisi için kullanılır. Stack'eki veri hemen silinirken, heap'deki verinin silinmesi GC' ye bağlıdır.
**Veri Yapıları**

- **ArrayList** Verileri saklamak için dinamik bir array kullanır. Array kullandığı için yavaştır. Eğer bir elemanı silinirse hepsi birer kayar. Sadece list gibi davranabilir çünk sadece List interface'ini implemente ediyor. Verilere ulaşma açısından iyidir.
- **Linked List** birbirine bağlı doğrusal bir dizi öğeden oluşan sıralı bir yapıdır. Liked list verileri saklamak için doubly linked list kullanır. ArrayList'ten daha hızlıdır çünkü doubly list kullanır. Bir eleman silindiğinde kaydırma durumu yoktur. Hem bir list hem de queue gibi davranabilir çünkü list ve Deque interface'lerini implemente eder. Veriyi işleme açısından iyidir.