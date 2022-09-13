package org.example.services;

import org.example.models.Book;
import org.example.models.Bookworm;
import org.example.models.Rent;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentService {

    public List<Rent> getRentList(Session session){
        return session.createQuery(" from Rent").list();
    }

    public void createRent(Session session) throws ParseException {
        BookService bookService = new BookService();
        BookwormService bookwormService = new BookwormService();
        List<Book> books = bookService.getBookList(session);
        List<Bookworm> bookworms = bookwormService.getBookwormList(session);
        int temp;

        Scanner scanner = new Scanner(System.in);
        Rent rent = new Rent();

        System.out.println("Enter start date YYYY-MM-DD");
        rent.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

        System.out.println("Enter end date YYYY-MM-DD");
        rent.setEndDate( new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

        System.out.println("Enter notes");
        rent.setNotes(scanner.nextLine());

        do {
            System.out.println("Enter book id");
            for (int i = 0; i < books.size(); i++) {
                System.out.print("\n [" + (i + 1) + "] ");
                bookService.printBook(books.get(i));
            }
            temp = scanner.nextInt() - 1;
            if (checkIfBookRented(books.get(temp),getRentList(session))) {
                rent.setBook(books.get(temp));
            } else {
                temp = 0;
            }
        }while (temp!=0);


        System.out.println("Enter bookworm id");
        for (int i = 0 ; i < bookworms.size();i++){
            System.out.print("\n [" + (i+1) + "] ");
            bookwormService.printBookworm(bookworms.get(i));
            rent.setBookwormId(
                    bookworms.get(scanner.nextInt()-1));
        }

        session.beginTransaction();
        session.save(rent);
        session.getTransaction().commit();
    }

    private boolean checkIfBookRented(Book book,List<Rent> rentList) throws ParseException {
       //Date now = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(LocalDate.now()));
        //Date now = new Date();
        String now1 = String.valueOf(LocalDate.now());
        //Date now = new SimpleDateFormat("yyyy-MM-dd").parse(now1);
        Calendar callendar = Calendar.getInstance();
        String now = callendar.get(Calendar.YEAR) + "-" + callendar.get(Calendar.MONTH) + "-" + callendar.get(Calendar.DATE);

        for (Rent rent: rentList
             ) {
            if(rent.getBook().equals(book) && rent.getEndDate().before(new SimpleDateFormat("yyyy-MM-dd 00:00:00.0").parse(now))){
                return false;
            }
        }
        return true;
    }
    public void printRent(Rent rent){
        BookService bookService = new BookService();
        BookwormService bookwormService = new BookwormService();

        System.out.printf("Start of rent: %s, end of rent: %s, notes: %s\n",rent.getStartDate(),rent.getEndDate(),rent.getNotes());
        bookService.printBook(rent.getBook());
        bookwormService.printBookworm(rent.getBookwormId());

    }

    public void printRentList(List<Rent> rentList) {
        for (Rent rent: rentList
             ) {
            printRent(rent);
        }
    }
}
