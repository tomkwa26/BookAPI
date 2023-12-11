package pl.coderslab.bean.controller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService {

    private List<Book> list;
    private static Long nextId = 4L;

    // tworzenie w konstruktorze obiektów klasy Book i dodawanie ich do listy
    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz głową Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay	Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    // metoda pobierająca konkretną książkę z listy
    @Override
    public Optional<Book> get(Long id) {
        return list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // metoda dodająca nową książkę do listy
    @Override
    public void add(Book book) {
        book.setId(nextId++);
        list.add(book);
    }

    // metoda usuwająca książkę z listy
    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            list.remove(this.get(id).get());
        }
    }

    // metoda edytująca książkę
    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = list.indexOf(this.get(book.getId()).get());
            list.set(indexOf, book);
        }
    }
}
