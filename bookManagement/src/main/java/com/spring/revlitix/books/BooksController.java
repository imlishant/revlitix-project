package com.spring.revlitix.books;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

    BooksService booksService = new BooksService();

    // @GetMapping("/hello")
    @RequestMapping("/hello")
    public String meriMarzi() {
        return "hi heloo";
    }

    @PostMapping("/add")
    public void addNewBook(@RequestBody Books book) {
        booksService.addBook(book);
    }

    @PostMapping("/add/{id}/{name}")
    public void addBookDetails(@PathVariable String id, @PathVariable String name) {
        Books book = new Books(id, name);
        booksService.addBook(book);
    }
    
    @GetMapping("/show")
    public List<Books> getBooks() {
        return booksService.getAllBooks();
    }

    

}
