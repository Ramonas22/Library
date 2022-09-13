package org.example.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    /*@Cascade({
                org.hibernate.annotations.CascadeType.SAVE_UPDATE,
                org.hibernate.annotations.CascadeType.MERGE,
                org.hibernate.annotations.CascadeType.PERSIST
        })*/
    /*@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "authorBooks" , joinColumns = {
            @JoinColumn(referencedColumnName = "author_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "book_id")})
    private List<Book> authorBooks = new ArrayList<>();*/



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;

    @ManyToMany(mappedBy = "authorList",cascade = CascadeType.ALL)
    private List<Book> authorBooks = new ArrayList<>();

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    public Author() {
    }

    public Author(long id, String name, String surname, Date date_of_birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = date_of_birth;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public List<Book> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<Book> authorBooks) {
        this.authorBooks = authorBooks;
    }
}
