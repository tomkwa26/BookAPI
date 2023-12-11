# BookAPI (Workshop-5)
Celem warsztatu jest wytworzenie **API** służącego do zarządzania książkami.

## Wstęp do warsztatu BookAPI
Rozpocznij od utworzenia klasy **Book** zawierającej pola:

* id,
* isbn,
* title,
* author,
* publisher,
* type.

**Uzupełnij bezargumentowy konstruktor-będzie on wykorzystywany przez bibliotekę Jackson**

Następnie utwórz interfejs **BookService**:

public interface BookService {
    List<Book> getBooks();

    Optional<Book> get(Long id);

    void add(Book book);

    void delete(Long id);

    void update(Book book);
}

Kolejny krok to utworzenie implementacji interfejsu — klasy: **MockBookService**.

Dane do zasilenia naszego programu będziemy przechowywać bezpośrednio w kodzie.

W tym celu w utworzymy pole:

private List<Book> list;

do którego w konstruktorze dodamy przykładowe książki. Jeżeli będziesz mieć problem z wykonaniem tego zadania, zajrzyj do wskazówek, jest tam przykład.

Pierwszym zadaniem przed rozpoczęciem głównych prac programistycznych jest utworzenie projektu **Maven**. W pliku **pom.xml** uzupełniamy właściwości:

<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <spring-framework.version>5.1.9.RELEASE</spring-framework.version>
</properties>
<packaging>war</packaging>

Następnie dodajemy zależności niezbędne do prawidłowego działania naszego projektu:

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>${spring-framework.version}</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
</dependencies>

W naszym projekcie moglibyśmy samodzielnie przekształcać obiekty na format JSON. Nie jest to jednak zbyt wygodne. Posłużymy się w tym celu biblioteką Jackson, należy uzupełnić zależności w pliku **pom.xml**:

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.0.pr2</version>
</dependency>

Spring wykryje załączoną bibliotekę i sam zadba o odpowiednie przekształcenia obiektu na JSON. Definiujemy klasę konfiguracji. Na tym etapie nasz projekt nie różni się od tworzonych wcześniej aplikacji z wykorzystaniem Spring MVC.

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")
public class AppConfig implements WebMvcConfigurer {


}

Dodajemy inicjalizator aplikacji, korzystamy z innej nieco uproszczonej jego implementacji w porównaniu do tej z której korzystaliśmy wcześniej. Rejestrujemy również filtr ustawiający odpowiednie kodowanie.

public class AppInitializer extends
  AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() { return null; }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};  }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};   }
}

Dodaj kontroler **BookControler** a w nim testową akcję dzięki, której zweryfikujesz czy wszystko działa poprawnie:

@RestController
@RequestMapping("/books")
public class BookController {
 
    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }
}

### Czego nauczysz się podczas tego warsztatu?
Warsztat w formie wykonania jednego dużego zadania jakim jest przygotowanie jednego większego programu na pewno daje duży zastrzyk praktycznej wiedzy i pozwala na szybsze i bardziej pewne poruszanie się w kodzie Javy, czy programie IntelliJ.

W projekcie tym użyto praktycznie wszystkie rzeczy, o których mówiliśmy podczas tego modułu takie jak:

* Spring,
* Spring MVC,
* wstrzykiwanie zależności
* import oraz wykorzystanie zewnętrznych bibliotek.
  
Wszystko to będzie możliwe do zastosowania w tym projekcie! To na pewno ugruntuje Twoją wiedzę.

### Testuj na bieżąco
Dodając każdą z nowych funkcjonalności testuj działanie programu. Nie staraj się zrobić całego warsztatu od razu. Jeżeli masz z czymś problem używaj debugera aby krok po kroku śledzić działanie programu.

#### Powodzenia!
