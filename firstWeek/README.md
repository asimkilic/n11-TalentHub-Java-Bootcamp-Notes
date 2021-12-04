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