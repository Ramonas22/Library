package org.example.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Rent> rentList;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "author_id"))
    private List<Author> authorList = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "release_date")
    private Date date;

    @Column(name = "isbn")
    private String isbn;

    public Book() {
    }

    public Book(long id, String name, String genre, Date date, String isbn) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.date = date;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
