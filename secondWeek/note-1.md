# Spring'e Genel Bir Bakış

Spring framework, Java için geliştirilmiş, açık kaynak olan bir uygulama geliştirme framework'üdür. Herhangi bir Java uygulaması tarafından kullanılabilmektedir. Eklentileri ile birlikte Java Enterprise platform üzerinde web uygulamaları geliştirmek içinde kullanılabilir. Spring framework Java toplulukları arasında *Enterprise Java Bean (EJB)* modelinin yerine geçebilecek popüler bir hale gelmiştir. Rod Johnson tarafından yazılmıştır, Haziran 2003'de Apache 2.0 lisansı altında yayınlanmıştır.

Uygulama kapsamı genişledikçe kullanılan teknolojiler, ihtiyaçlar ve beraberinde karmaşıklık artmaya başlar. Java bu ihtiyaçları karşılamak  ve karmaşıklığı en aza indirmek için içerisindeki kütüphaneleri gruplayarak SE, ME, EE gibi takma isimler vermiştir. Bazı Java kütüphaneleri:

* Servlet
* JSP (JavaServer Pages)
* EL (Expression Language)
* JSTL (JavaServer Pages Standard Tag Library)
* JSF (JavaServer Faces)
* JAX-RS (Java API for RESTful Web Services)
* WebSocket (Java API for Web Socket)
* JSON-P (Java API for JSON Processing)
* EJB (Enterprise JavaBeans)
* JPA (Java Persistance API)

Kütüphane listesi Java EE sürümüne göre farklılık gösterir.

![img](https://miro.medium.com/max/1158/1*VNLBN-Me8gjMV9Wdh_bADw.png)

## IoC (Inversion of Control)

- Bağımlılıkların tersine çevrilmesidir.
- Nesnelerin bağımlılıklarının yönetimini devralır.
- İmplementasyon ile uygulamayı birbirinden ayırır.
- İmplementasyonlar arası geçişleri kolaylaştırır.

## Dependency Injection

- IoC pattern'idir.
- Bir nesnenin bağımlı olduğu başka bir nesneyi dışarıdan "enjekte etmek"  tir.
- 3 Farklı şekilde yapılabilir
  - Constructor
  - Setter
  - Fields

## Application Context

- IoC Conteiner'dır.
- Spring framework ana özelliklerinden birisi de IoC Container‘dır. IoC Container, uygulamamızın obje yönetimini sağlamak ile sorumludur. 
- BeanFactory ve ApplicationContext interface’leri, Spring IoC Container’ı temsil eder. BeanFactory, Spring container’a erişim için kök interface’i barındırır. Bean’leri yönetmek için temel işlevleri sağlar. ApplicationContext ise BeanFactory’nin bir alt interface’idir. Dolayısıyla, BeanFactory’nin tüm işlevlerini bizlere sunar.

## Bean 

Spring Framework uygulamamızın omurgasını oluşturan ve Spring IOC container tarafından yönetilen nesnelere **BEAN** denir. Yeniden kullanılabilir objeler olarak kabul edebiliriz. Application Context çağırıldığında BEAN'ler oluşturulur. BEAN'ler Application Context bazında **default singleton** oluşur.

## BEAN SCOPE

- **Default** : singleton'dır.
- **Singleton**: Application context  bazında tek bir örnek oluşur.
- **Prototype** : Her istek yapıldığında yeni bir örneği oluşturulur.
- **Request** : Her HTTP isteğinde yeni bir örnek oluşur.
- **Session** Her HTTP oturumunda yeni bir örnek oluşur.

## @Configuration

Bean tanımlarının yapıldığı class'ları işaretleyen anotasyondur.

Birden fazla @Configuration anotasyonu kullanılabilir.

## Dönüşüm

Pom.xml  içerisine gerekli bağımlılıkları ekliyoruz.

```java
	<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.3.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.12</version>
        </dependency>
   
```

Bir adet SpringCoreApplication adında class oluşturuyoruz. İçerisinde Context oluşturacağız.

```java
package com.asimkilic;

public class SpringCoreApplication{
	public static void main(String[] args){
    	Service service = new Service();
   	 	service.test();
   }
}
```

Bir tane **Dao** 

```java
package. com.asimkilic.sgrpingbeans;
public class Dao{
    public void test(){
        System.out.pritln("Test");
    }
}
```

bir tanede **Service** oluşturuyoruz.

```java
package com.asimkilic.springbeans;

public class Service{
    private Dao dao;
    public Service(){
        this.dao= new Dao();
    }
    public void test(){
        dao.test();
    }
}
```

Biz bu Dao'muzun **Bean** olmasını istiyoruz. Bunlar için bir **Bean** tanımlamak istiyoruz, bunu da **@Bean** ile tanımlıyoruz.

```java
package com.asimkilic;

public class SpringCoreApplication{
	public static void main(String[] args){
    	Service service = new Service();
   	 	service.test();
   }
    @Bean
    public Service getService(){
        return new Service(getDao());
    }
    @Bean
    public Dao getDao(){
        return new Dao;
    } 
}
```

Bundan sonra artık **Service** sınıfımızda Dao nesnesinin örneğini üretmek yerine bunu constructor'dan parametre olarak istiyoruz.

```java
package com.asimkilic.springbeans;

public class Service{
    private Dao dao;
    public Service(Dao dao){ /* <------------- */
        this.dao= dao; /* <------------- */ 
    }
    public void test(){
        dao.test();
    }
}
```

Daha sonra SpringCoreApplication içerisinde context'imizi oluşturuyoruz. Ve sınıfımızı **@Configuration** olarak işaretliyoruz, yani bu bizim konfigürasyon sınıfımız içerisinde ***Bean*** leri içeriyor.

```java
package com.asimkilic;

@Configuration  /* <<<<---------------------*/
public class SpringCoreApplication{
	public static void main(String[] args){
 /* --->>>>>*/ ApplicationContext = new AnnotationConfigApplicationContext(SpringCoreApplication.class);
        /* Biz burada getService() demek yerine git Context'ten bir Bean al*/
  Service service=context.getBean(Service.class); /* <<<----- */
   service.test();
   }
    @Bean
    public Service getService(){
        return new Service(getDao());
    }
    @Bean
    public Dao getDao(){
        return new Dao;
    } 
}
```

Özet olarak bağımlılık duyduğumuz sınıflarımızı **@Configuration** olarak işaretlediğimiz sınıfımız içerisinde **@Bean** olarak tanımlayıp, bu örnek üretme kontrolünü Spring'e devretmiş oluyoruz. 

Gerekli olan yerde bağımlılık olan sınıfı constructor'da parametre olarak istiyoruz. Ve sınıf içerisinde (Dao) bize örneği verilecek şekilde kodluyoruz. Daha sonra bu sınıfları (Service) kullanacağımız yerde(SpringCoreApplication) bir context oluşturup buradan getBean ile bağımlılıklarını çözüyoruz. Bean'lerimizi Xml dosyası içerisinde de belirtebiliyoruz.

## XmlBasedApplication

Resources altında applicationcontext klasörü oluşturup içerisine **xml-based-config.xml** dosyası oluşturuyoruz. Application Context'imizin Bean'lerini burada tanımlayacağız.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="urunDao" class="com.asimkilic.dao.UrunDao" scope="singleton"></bean>

    <bean id="urunEntityService" class="com.asimkilic.entityservice.UrunEntityService" scope="singleton">
        <constructor-arg name="urunDao" ref="urunDao"> </constructor-arg>
        
    </bean>
</beans>
```

Burada yaptığımız işlem ; önce **UrunDao** mu oluştur, **UrunDao** oluştuktan sonra **Service**'imi oluştur, **Service**'im oluştuktan sonra git onun içerisinde **UrunDao**'yu injekte et.

**XmlBasedApplication** sınıfımızı oluşturuyoruz.

```java
package com.asimkilic;

public class XmlBasedApplication{
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext/xml-based-config.xml");
         UrunEntityservice urunEntityService = context.getBean(UrunEntityService.class);
        List<Urun> urunList = urunEntityService.findAll();
        for(Urun urun : urunList){
            System.out.println(urun);
        }
    }
}
```

​	***UrunEntityService class'ının constructor'unda UrunDao urunDao parametresi bekleniyor olması gerekiyor***

- Bir context'den aynı nesneden ne kadar örnek isterseniz isteyin aynı nesneyi geriye döndürecektir. (Default : Singleton). Eğer context'in örneğini yeniden oluşturursanız bu sefer farklı bir örnek geri döndürecektir. Örneği istediğiniz context nesnesi değişmediği sürece geriye döndürdüğü hep aynı nesneyi döndürecektir.
- Her seferinde farklı bir örnek gelsin isteniyorsa, istenilen class **@Scope(value=ConfigurableBeanFactor.SCOPE_PROTOTYPE)** olarak işaretlenmeli.( Örn UrunEntityService).

## AnnotationBasedApplication

applicationcontext klasörü içerisine *annotation-based-config.xml* dosyamızı oluşturuyoruz.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

   <context:annotation-config/>-->
    <context:component-scan base-package="com.asimkilic"/>

</beans>
```

içeride yine konfigürasyon xml'imiz var ama bunun üzerinde diyoruz ki; bunun context'i annotation-config'dir. component-scan'ide vereceğim base-package'dir.

Burada belirttiğimiz base-package içerisindeki component'leri tarayacak. Context'in içerisinde olmasını istediğimiz class'ları **@Component** anotasyonu ile işaretliyoruz.

```java
@Component /* Bu artık benim için context'imde yer alacak Bean*/
public class UrunEntityService{

    @Autowired
    private UrunDao urunDao;
  .....
       
}
```

Bundan sonra örneğini istediğimiz field'ımızı ***@Autowired*** olarak işaretliyoruz. UrunDao class'ımızıda Bean'lerde yer almasını istediğimiz için **@Component** olarak işaretliyoruz.

Ve oluşturacağımız AnnotationBasedApplication class'ımızın içerisinde context'imizi oluşturuyoruz.

```java
package com.asimkilic;
public class AnnotationBasedApplication{
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext/annotation-based-config.xml");
        UrunEntityService urunEntityService = context.getBean(UrunEntityService.class);
       ...
    }
}
```

Eğer context oluşuracağımız sınıfımızı **@ComponentScan** olarak işaretlersek, o sınfımıızın bulunduğu package'ı base-package olarak alır, xml içerisinde ayrıca base-package belirtmemize gerek kalmaz.  

**@ComponentScan("<packagename>")** olarak da belirtebiliyoruz.

EntityService'lerimizi **@Service** olarak işaretleyebiliyoruz. @Service zaten @Component'den türemiştir. Veya repository classlarımızı **@Repository** olarak işareletleyebiliriz. 



# Resources

- https://tr.wikipedia.org/wiki/Spring_Framework
- https://www.yusufsezer.com.tr/java-ee-nedir/
- https://codingbytime.com/spring-applicationcontext-nedir/