package com.spring.revlitix.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// @CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/book")
@RestController
public class BooksController {

    @Autowired
    BooksRepository booksRepository;

    BooksService booksService = new BooksService();

    /* ////
    //// using service to manipulate


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
    
	@PutMapping("/update/{id}")
	public void updateBookbyId(@RequestBody Books book, @PathVariable int id) {
		booksService.updateBookbyId(id, book);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteBookById(@PathVariable int id) {
		booksService.deleteBookById(id);
	}

    */

    //// repository jpa code.
    
    //add by postman
    @PostMapping("/add")
    public ResponseEntity<Books> addNewBook(@RequestBody Books book) {
		try {
			Books _book = booksRepository.save(new Books(book.getId(), book.getName()));
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    //add by ID
    @PostMapping("/add/{id}/{name}")
    public ResponseEntity<Books> addBookDetails(@PathVariable String id, @PathVariable String name) {

        try {
            Books book = new Books(id, name);
			Books _book = booksRepository.save(book);
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    //get all list
    @GetMapping("/show")
    public ResponseEntity<List<Books>> getBooks(@RequestParam(required = false) String title) {
		try {
			List<Books> booksList = new ArrayList<Books>();

			if (title == null)
                booksRepository.findAll().forEach(booksList::add);

			if (booksList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(booksList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    //update book by id
    
	@PutMapping("/update/{id}")
    public ResponseEntity<Books> updateBookbyId(@PathVariable("id") int id, @RequestBody Books book) {
		Optional<Books> bookData = booksRepository.findById(id);

		if (bookData.isPresent()) {
			Books _book = bookData.get();
			_book.setName(book.getName());
			_book.setAvailable(book.isAvailable());
			return new ResponseEntity<>(booksRepository.save(_book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    //delete book by id
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") int id) {
		try {
			booksRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
