package com.spring.revlitix.borrow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
    List<Borrow> findByBorrowerId(int borrowerId);
	List<Borrow> findByBookId(int bookId);
}
