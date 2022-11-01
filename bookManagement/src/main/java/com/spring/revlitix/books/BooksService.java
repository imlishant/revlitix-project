package com.spring.revlitix.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    List<Books> list = new ArrayList<>();

    public List<Books> getAllTopics(){
		List<Books> booksList = new ArrayList<>();
		booksRepository.findAll().forEach(booksList::add);
		return booksList;
	}

    public void addBook(Books book) {
        list.add(book);
    }

    public List<Books> getAllBooks() {
        return list;
    }

    /// doing all things in jpa connected h2 database;
	public void updateBookbyId(int id, Books book) {
		booksRepository.save(book);
	}

	public void deleteBookById(int id) {
		booksRepository.deleteById(id);
	}


}
