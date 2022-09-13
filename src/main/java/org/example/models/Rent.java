package org.example.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookworm_id")
    private Bookworm bookwormId;

    public Rent() {
    }

    public Rent(long id, Date startDate, Date endDate, Book book, Bookworm bookwormID) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.book = book;
        this.bookwormId = bookwormID;
    }

    public Rent(long id, Date startDate, Date endDate, String notes, Book book, Bookworm bookwormID) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
        this.book = book;
        this.bookwormId = bookwormID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Bookworm getBookwormId() {
        return bookwormId;
    }

    public void setBookwormId(Bookworm bookwormId) {
        this.bookwormId = bookwormId;
    }
}
