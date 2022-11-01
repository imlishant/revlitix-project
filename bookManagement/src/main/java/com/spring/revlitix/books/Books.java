package com.spring.revlitix.books;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Books {
    @Id
    private String id;
    private String name;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Books() {
        this.isAvailable = true;
    }

    public Books(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
