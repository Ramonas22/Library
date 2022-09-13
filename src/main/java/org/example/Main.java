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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
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
        rentService.createRent(session);



        /*Date now = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString());

        System.out.println(now);
        System.out.println(rentList.get(4).getEndDate());
        Calendar callendar = Calendar.getInstance();
        System.out.println(callendar.get(Calendar.YEAR) + "-" + callendar.get(Calendar.MONTH) + "-" + callendar.get(Calendar.DATE));*/


        //authorService.printAuthorList(authorList);
        //bookService.printBookList(bookList);
        //bookwormService.printBookwormList(bookwormList);
        //rentService.printRentList(rentList);


    }
}