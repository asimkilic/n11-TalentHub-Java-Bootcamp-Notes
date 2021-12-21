## Transaction

Birbiri ile ilişkili işlemlerde transaction'ları kullanırız.

4 tane özelliği var  **ACID** olarakda bilinir;

- **Atomicity**: Her bir işlemin başarılı olduğu durumda commit, aksi halde rollback("all-or-nothing")
-  **Consistency **: Tutarlı demektir. Verilerin tutarlı olması demektir.
- **Isolation**: İzole etmek demektir. Aynı anda birden fazla transaction tarafından güncelleme yapılmamalı.
- **Durability**: Dayanıklı, kalıcı demektir. Transaction sonlandığında tüm işlemler kalıcı hale gelmelidir. Eğer bir hata olduysa da tüm güncellemeler iptal edilip, birebir eski haline dönmelidir.

## Programmatic Tramsaction

Transaction işleminin başlatlıp sonlandırılması ve açık kalan bağlantıların kapatılması kodlarını içinde barındıran bir yöntemdir.

## Declarative Transaciton

Spring altyapısında bazı kurallar çerçevesinde Spring Container tarafından gerçekleştirilir.

## @TRANSACTIONAL Propagation

- **REQUIRED** : Aktif bir transaction varsa ona bağlanır. Yoksa yeni açar
- **SUPPORTS** : Aktif bir transaction var mı bakar. Varsa ondan devam eder, yoksa non transaction olarak devam eder.
- **MANDATORY** : Aktif bir transaction var mı  bakar. Varsa ondan devam, eder yoksa hata verir.
- **REQUIRES_NEW** : Aktif bir transaction var mı bakar. Varsa onu askıya alır ve yeni bir transaction oluşturur.
- **NOT_SUPPORTED** : Aktif bir transaction var mı bakar. Varsa onu askıya alır ve non transactional olarak yürütülür.
- **NEVER** : Aktif bir transaction varsa spring hata fırlatır.
- **NESTED** : Halihazırdaki transactiona ait olan iç içe bir transaction başlatır. Bu iç içe çağrılar arasında savepointler koyar. Yani içteki transactionlar dıştakinden bağımsız olarak geri alınabilirler. JPA dialect, Hibernate vs. bunu desteklemez. (NestedTransactioNotSupportedException JpaDialect does not support savepoints - check your JPA provider's capabilities) 

 

--------

Spring validation

Pom.xml'e gerekli bağımlılığı ekliyoruz.

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
<artitactId>spring-boot-starter-validation</artifactId>
</dependency>
```

Kullanıcıdan aldığımız DTO'larımızın validasyonunu kontrol edebiliyoruz. Parametre olarak aldığımız nesnemizin başına **@Valid** 'i ekliyoruz. 

```java
public void saveProduct(@Valid @RequestBody ProductDto p)
```

Class'ımızda doğrulanması istediğimiz field'ın üzerine istenilen doğrulama ile ilgili olan işaretlemeyi yapıyoruz.

```java
import javax.validation.constraints.Size;
...
    @Size(min=2)
    private String name;

 	@Past
	private Date createDate;
// geçmiş tarih olmak zorunda.
```

 Daha sonra global exception handling yaptığımız class'ımızda **handleMethodArgumentNotValid**  metodunu *override* ediyoruz.

```java
@Overide
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
    Date errorDate = new Date();
    String message = "Validation failed!";
    String description = ex.getBindingResult().toString();
    ExceptionResponse exceptionResponse= new ExceptionResponse(errorDate, message, description);
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
}
```

 

