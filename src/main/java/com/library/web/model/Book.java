package com.library.web.model;


//import javax.validation.constraints.Size;
public class Book {
    private int id;
    private String name;
    private String isbn;
    private int quantity;
    private int available;


    public Book() {
    		super();
    }
    
    public Book(int id, String name, String isbn, int quantity, int available) {
        super();
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.quantity = quantity;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "Book [id=%s, name=%s, isbn=%s, quantity=%s, available=%s]", id,
                name, isbn, quantity,available);
    }

}