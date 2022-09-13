package org.example;

import org.example.models.Author;
import org.example.models.Book;
import org.example.models.Bookworm;
import org.example.models.Rent;
import org.example.services.AuthorService;
import org.example.services.BookService;
import org.example.services.BookwormService;
import org.example.services.RentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        AuthorService authorService = new AuthorService();
        BookService bookService = new BookService();
        BookwormService bookwormService = new BookwormService();
        RentService rentService = new RentService();

        List<Author> authorList =  authorService.getAuthorList(session);
        List<Book> bookList = bookService.getBookList(session);
        List<Bookworm> bookwormList = bookwormService.getBookwormList(session);
        List<Rent> rentList = rentService.getRentList(session);

        //authorService.createAuthor(session);
        //bookService.createBook(session);
        //bookwormService.createBookworm(session);
        //rentService.createRent(session);
        rentService.returnBook(session);


        //authorService.printAuthorList(authorList);
        //bookService.printBookList(bookList);
        //bookwormService.printBookwormList(bookwormList);
        //rentService.printRentList(rentList);


    }
}