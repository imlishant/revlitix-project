package com.spring.revlitix.members;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.spring.revlitix.books.Books;

@Entity
public class Members {
    @Id
    private String memberId;
    private String memberName;
    private String bookTaken;

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getBookTaken() {
        return bookTaken;
    }
    public void setBookTaken(String bookTaken) {
        this.bookTaken = bookTaken;
    }

    public Members() {}

    public Members(String id, String name, String bookList) {
        this.memberId = id;
        this.memberName = name;
        this.bookTaken = bookList;
    }
}
