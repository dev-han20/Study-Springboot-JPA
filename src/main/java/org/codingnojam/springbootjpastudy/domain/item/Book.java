package org.codingnojam.springbootjpastudy.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.codingnojam.springbootjpastudy.controller.BookForm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item {

    private String isbn;
    private String author;

    public Book change(BookForm bookForm) {
        this.setAuthor(bookForm.getAuthor());
        this.setName(bookForm.getName());
        this.setPrice(bookForm.getPrice());
        this.setStockQuantity(bookForm.getStockQuantity());
        this.setId(bookForm.getId());
        this.setIsbn(bookForm.getIsbn());
        return this;
    }

}
