package com.spring.revlitix.members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface MembersRepository extends JpaRepository<Members, Integer> {
    
}
