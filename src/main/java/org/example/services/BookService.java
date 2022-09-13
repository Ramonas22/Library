package org.example.services;

import org.example.models.Author;
import org.example.models.Book;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BookService {

    public List<Book> getBookList(Session session){
        return session.createQuery("from Book").list();
    }

    public void createBook(Session session) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        int temp;
        List<Author> authorList = session.createQuery("from Author").list();

        System.out.println("Enter book name");
        book.setName(scanner.nextLine());

        System.out.println("Enter book genre");
        book.setGenre(scanner.nextLine());

        System.out.println("Enter book release date YYYY-MM-DD");
        book.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

        System.out.println("Enter book isbn");
        book.setIsbn(scanner.nextLine());

        do{
            System.out.println("Pick available author: ");
            for (int i = 0; i < authorList.size(); i++) {
                System.out.println((i+1) + " " + authorList.get(i).getName() + " " + authorList.get(i).getSurname());
            }
            System.out.println("To exit press " + (authorList.size()+1 ));
            temp = scanner.nextInt()-1;

            if(temp >= authorList.size())
            {
                break;
            }
            if(checkIfAuthorInList(authorList,authorList.get(temp))){
               book.getAuthorList().add(authorList.get(temp));
            }else {
                System.out.println("Author already is in list");
            }
        }while (temp < authorList.size());


        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    private boolean checkIfAuthorInList(List<Author> authorList, Author temp) {
        for (Author author: authorList
             ) {
            if(author.equals(temp)){
                return true;
            }
        }
        return false;
    }


    public void printBook(Book book){
        AuthorService authorService = new AuthorService();
        System.out.printf("Book name: %s, genre %s, release date: %s authors:\n",book.getName(),book.getGenre(),book.getDate());
        authorService.printAuthorList(book.getAuthorList());
    }

    public void printBookList(List<Book> books){
        for (Book book: books
             ) {
            System.out.println("\n");
            printBook(book);
        }

    }
}
