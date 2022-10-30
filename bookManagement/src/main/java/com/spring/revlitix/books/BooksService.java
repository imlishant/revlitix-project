package com.spring.revlitix.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BooksService {
    List<Books> list = new ArrayList<>();

    public void addBook(Books book) {
        list.add(book);
    }

    public List<Books> getAllBooks() {
        return list;
    }
}
