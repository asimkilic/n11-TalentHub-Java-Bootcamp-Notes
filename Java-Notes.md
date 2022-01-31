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