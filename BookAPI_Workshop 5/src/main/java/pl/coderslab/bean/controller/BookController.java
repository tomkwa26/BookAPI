package pl.coderslab.bean.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

private MockBookService bookService;

    public BookController(MockBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseBody
    public List<Book> getList() {
        return bookService.getBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Książka o ID " + id + " nie została znaleziona.");
        });
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("")
    @ResponseBody
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }
}

// PUNKT DOSTĘPOWY - DODAWANIE KSIĄŻKI (WYWOŁANIE W TERMINALU)
//curl -X POST -i -H "Content-Type: application/json"
//        -d '{"isbn":"34321","title":"Thinking in Java", "publisher":"Helion","type":"programming", "author":"Bruce Eckel"}'
//        http://localhost:8080/books

// PUNKT DOSTĘPOWY - USUWANIE KSIĄŻKI (WYWOŁANIE W TERMINALU)
//curl -X DELETE -i  http://localhost:8080/books/1

// PUNKT DOSTĘPOWY - EDYCJA KSIĄŻKI (WYWOŁANIE W TERMINALU)
//curl -X PUT -i -H "Content-Type: application/json" -d
//        '{"id":2,"isbn":"32222","title":"Thinking in C#",  "publisher":"IT Books","type":"programming", "author":"Bruce Eckel"}'
//        http://localhost:8080/books

