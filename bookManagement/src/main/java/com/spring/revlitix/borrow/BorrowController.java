package com.spring.revlitix.borrow;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.revlitix.books.*;
import com.spring.revlitix.members.*;

@RequestMapping("/api/borrow")
@RestController
public class BorrowController {
    
	@Autowired
	BorrowRepository borrowRepository;
	@Autowired
	BooksRepository bookRepository;
	@Autowired
	MembersRepository memberRepository;

	@GetMapping("/viewall")
	public List<Borrow> getAllBorrow() {
		return borrowRepository.findAll();
	}
	
	@GetMapping("/findByBorrowerId")
	public List<Borrow> getByBorrowerId(@RequestParam(value="borrowerId")int borrowerId){
		return borrowRepository.findByBorrowerId(borrowerId);
	}
	
	@GetMapping("/findByBookId")
	public List<Borrow> getByBookId(@RequestParam(value="bookId")int bookId){
		return borrowRepository.findByBookId(bookId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Borrow>> getBorrowById(@PathVariable(value = "id") int id) {
		Optional<Borrow> borrow = borrowRepository.findById(id);
		return ResponseEntity.ok().body(borrow);
	}
	
	@PostMapping("/take")
	public String addBorrow(@RequestBody Borrow borrow) {
		Members member = memberRepository.findById(borrow.getBorrowerId()).get();
		Books book = bookRepository.findById(borrow.getBookId()).get();
		
		if (book.isAvailable() == false) {
			return "Your requested book \"" + book.getName() + "\" is out of stock!";
		}
		
		book.bookBorrowOne();
		bookRepository.save(book);
		
		borrowRepository.save(borrow);
		return member.getMemberName() + " has borrowed one copy of \"" + book.getName() + "\"!";
	}
	
	@PutMapping("/return")
	public Borrow returnBorrow(@RequestBody Borrow borrowBody) {
		int borrowId = borrowBody.getBorrowId();
		Borrow borrow = borrowRepository.findById(borrowId).get();
		Books book = bookRepository.findById(borrow.getBookId()).get();
		
		book.bookReturnOne();
		bookRepository.save(book);
		Date currentDate = new Date();
		borrow.setReturnedDate(currentDate);
		return borrowRepository.save(borrow);
	}
	


}
