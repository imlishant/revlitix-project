package com.spring.revlitix.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface BooksRepository extends JpaRepository<Books, Integer> {
    
}
