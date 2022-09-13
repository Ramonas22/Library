package org.example.services;

import org.example.models.Book;
import org.example.models.Bookworm;
import org.example.models.Rent;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        System.out.println("Enter bookworm id");
        for (int i = 0 ; i < bookworms.size();i++){
            System.out.print("\n [" + (i+1) + "] ");
            bookwormService.printBookworm(bookworms.get(i));
        }
        rent.setBookwormId(
                bookworms.get(scanner.nextInt()-1));

        do {
            System.out.println("Enter book id");
            for (int i = 0; i < books.size(); i++) {
                System.out.print("\n [" + (i + 1) + "] ");
                bookService.printBook(books.get(i));
            }
            temp = scanner.nextInt() - 1;
            if (checkIfBookRented(books.get(temp),getRentList(session))) {
                rent.setBook(books.get(temp));
                temp = 1;
            } else {
                System.out.println("Book is not available since it is rented");
                temp = 0;
            }
        }while (temp==0);

        session.beginTransaction();
        session.save(rent);
        session.getTransaction().commit();

    }

    private boolean checkIfBookRented(Book book,List<Rent> rentList) throws ParseException {
        Date todayDate= Date.from(java.time.ZonedDateTime.now().toInstant());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatedDate = formatter.format(todayDate);
        Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(formatedDate);

        for (Rent rent: rentList
             ) {
            if(rent.getBook().equals(book) && rent.getEndDate().after(date1)){
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

    public void returnBook(Session session) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        RentService rentService = new RentService();

        Date todayDate= Date.from(java.time.ZonedDateTime.now().toInstant());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatedDate = formatter.format(todayDate);
        Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(formatedDate);

        int temp;
        List<Rent> rentList = rentService.getRentList(session);

        do {
            System.out.println("Pick rent that u want to end and return the book");
            for (int i = 0; i < rentList.size(); i++) {
                System.out.print("[ " + (i + 1) + " ]  ");
                rentService.printRent(rentList.get(i));
            }
            temp = scanner.nextInt()-1;

            if(temp>=0 && temp < rentList.size()){
                System.out.println("I am doing smthg");
                System.out.println(date1);
                session.beginTransaction().begin();
                session.createQuery("update Rent set end_date =:today_date where id =:id_to_correct")
                        .setParameter("today_date", date1)
                        .setParameter("id_to_correct", rentList.get(temp).getId()).executeUpdate();
                session.getTransaction().commit();
                temp =1;
            }else {
                temp = 0;
                System.out.println("Index out of bounds try again");
            }

        }while (temp ==0);

    }
}
