## Transaction

Birbiri ile ilişkili işlemlerde transaction'ları kullanırız.

4 tane özelliği var  **ACID** olarakda bilinir;

- **Atomicity**: Her bir işlemin başarılı olduğu durumda commit, aksi halde rollback("all-or-nothing")
-  **Consistency ** : Tutarlı demektir. Verilerin tutarlı olması demektir.
- **Isolation**: İzole etmek demektir. Aynı anda birden fazla transaction tarafından güncelleme yapılmamalı.
- **Durability**: Dayanıklı, kalıcı demektir. Transaction sonlandığında tüm işlemler kalıcı hale gelmelidir. Eğer bir hata olduysa da tüm güncellemeler iptal edilip, birebir eski haline dönmelidir.

## Programmatic Transaction

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

 

------

## MongoDb

Uygulamayı kurduğumuz yerden **mongo.exe** aracılığı ile yönetebiliyoruz.

```powershell
C:\Program Files\MongoDB\Server\5.0\bin\mongo.exe
```

Table'lar burada *Collection* olarak geçiyor. 
Her bir satır *Document* olarak geçiyor.
Column'lar burada *field* olarak geçiyor.

**use n11** diyerek veritabanı oluşturabiliriz.

**db** diyerek hangi veritabanında olduğumuzu görebiliriz.

**show dbs** veya **show databases** diyerek varolan veritabanlarını görebiliriz. Bu komutta oluşturduğumuz *n11* gözükmeyecektir çünkü içerisinde henüz bir  *collection* yok.

```powershell
> use n11
switched to db n11
> db
n11
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB
>
```

n11 içerisine herhangi bir *Collection* eklediğimiz zaman veritabanı görünür olacak.



eğer yanlışlıkla bir db oluşturmuşsak onu ***db.dropDatabase()*** yazarak silebiliriz.

```powershell
> use n112
switched to db n112
> db
n112
> db.dropDatabase()
{ "ok" : 1 }
>
```

**db.CATEGORY.drop()** *CATEGORY* collection'ını siler.

```powershell
> show collections
CATEGORIES
category
> db.CATEGORIES.drop()
true
> show collections
category
>

```

Bir collection' a veri eklemek için **db.category.insert()** komutundan yararlanıyoruz. İçerisine  *JSON* türünde verilerimizi ekliyoruz.

```powershell
> db.category.insert(
... {
...   "id":1,
...   "name":"Moda",
...   "level":1,
...   "superCategoryId":null
... })
WriteResult({ "nInserted" : 1 })
```

Verilere erişmek için **db.category.find()** dememiz yeterli olacaktır verilerin düzenli biçimde gelmesi için sonuna **.pretty()** ekleyebiliriz.

```powershell
> db.category.find().pretty()
{
        "_id" : ObjectId("61c3108167f4e2ec8cdf1ae7"),
        "id" : 1,
        "name" : "Moda",
        "level" : 1,
        "superCategoryId" : null
}
> db.category.find()
{ "_id" : ObjectId("61c3108167f4e2ec8cdf1ae7"), "id" : 1, "name" : "Moda", "level" : 1, "superCategoryId" : null }
>

```

Ekstra olarak bizim verdiğimiz id'den başka kendiside bir *_id* alanı ekleyip atamasını gerçekleştirmiş.

```json
db.category.update({id:1},{name:"Modaa"})
// id'si 1 olan kayıt alanını komple günceller, yani sadece name:modaa olarak kalır, geri kalan değerleri gider.
db.category.remove({name:"Modaa"})
// "name":"Modaa" olan kayıtları siler.

db.category.update({id:9},{$set:{name:"Seehpa"}})
```

## Projemize MongoDb  ekleme

İlk olarak **Pom.xml** içerisine *dependency*' mizi ekliyoruz.

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

Projemiz içerisinde **mongodb** adında bir *package* oluşturalım işlemlerimizi burada yapalım.
*mongodb* package içerisine *controller, entity, service,entityservice(service içine) ve repository* packagelerimizi oluşturalım. Yapımız bu şekilde olacaktır;

```powershell
-mongodb
   |__ controller
   |__ entity
   |__ repository
   |__ service
            |__ entityservice
```



entity package'imizin içerisine **Category** class'ımızı oluşturalım. Class'ımızı **@Document** olarak işaretliyoruz ve içerisine parametre olarak veritabanımızda Collection olarak verdiğimiz ismi giriyoruz. Field'ları oluşturdukdan sonra getter ve setter'larıda ekliyoruz.

```java
@Document(collection="category")
public class Category {

    @Id
    private String id;
    private String name;
    private Long level;
    private String superCategoryId;
 //getters and setters 
}
```

repository package içerisine **CategoryRepository**  ***Interface***'imizi oluşturuyoruz. MongoRepository'den extend edip entity class'ımızı ve PK tipini belirtiyoruz. Ve *@Repository* olarak işaretliyoruz

```java
@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

}

```

entityservice paketimiz içerisinede **CategoryEntityService**'imizi oluşturuyoruz.

```java

@Service
@Transactional
public class CategoryEntityService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = null;
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        }
        return category;
    }

    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
```

service paketimizin içerisine **CategoryService** class'ımızı oluşturalım.

```java
@Service
public class CategoryService {
    @Autowired
    private CategoryEntityService categoryEntityService;

    public List<Category> findAll() {
        List<Category> categoryList = categoryEntityService.findAll();
        return categoryList;
    }

    public Category findById(String id) {
        Category categoryById = categoryEntityService.findById(id);
        return categoryById;
    }

    public Category save(Category category) {
        return categoryEntityService.save(category);
    }

    public void delete(String id) {
        categoryEntityService.deleteById(id);
    }
}
```

service>entityservice içerisine **CategoryEntityService** 'imizi oluşturuyoruz.

```java
@Service
@Transactional
public class CategoryEntityService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = null;
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        }
        return category;
    }

    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}

```

son olarakda *controller* paketimizin içerisine **CategoryController** 'u oluşturup içerisini dolduruyoruz.

```java

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable String id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Category category) {
         category = categoryService.save(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") 
    public void delete(@PathVariable  String id) {
       categoryService.delete(id);
    }
}

```

Ama CategoryService Class'ımızı interface olarak da kullandırabiliriz. Bunun için CategoryService class'ımızın içerisindeki dolu metodları CategoryServiceImpl adına bir Class açıp içerisine taşıyoruz ve CategoryService class'ımızı artık interface'e çekiyoruz daha sonra CategoryServiceImpl class'ımıza implements CategoryService ekliyoruz. Controller'da direk CategoryService çağırdığımız için sorun yok. Son olarak interface'imiz içerisindeki dolu metodları sadece imzaları kalacak şekilde düzenliyoruz.

CategoryService

```java


public interface CategoryService {
    List<Category> findAll() ;

    Category findById(String id) ;

    Category save(Category category) ;

    void delete(String id) ;
}


```

CategoryServiceImpl

```java
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryEntityService categoryEntityService;

    public List<Category> findAll() {
        List<Category> categoryList = categoryEntityService.findAll();
        return categoryList;
    }

    public Category findById(String id) {
        Category categoryById = categoryEntityService.findById(id);
        return categoryById;
    }

    public Category save(Category category) {
        return categoryEntityService.save(category);
    }

    public void delete(String id) {
        categoryEntityService.deleteById(id);
    }
}
```

Daha sonra **application.properties** içerisinde veritabanı bağlantımızı yapalım

```xml

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=n11
```



----

8080 portu zaten kullanımda hatası verdiğinde önceki projeden sonlanmayan işlemler vardır bunun için **CMD** açıyoruz ve oradan önce port 8080 'de çalışan işlemin Process ID'sini alıyoruz sonra kill ediyoruz.

**netstat -ano | findstr 8080**  (8080 portundaki işlemleri listeler)

```powershell
C:\Users\ofn2nvu>netstat -ano | findstr 8080
  TCP    0.0.0.0:8080           0.0.0.0:0              LISTENING       15656
  TCP    [::]:8080              [::]:0                 LISTENING       15656

C:\Users\ofn2nvu>taskkill /F /PID 15656
SUCCESS: The process with PID 15656 has been terminated.
```

**taskkill /F /PID 15656**  ProcessID'si 15656 olan işlemi sonlandırır.
