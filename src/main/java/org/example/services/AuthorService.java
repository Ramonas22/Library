package org.example.services;

import org.example.models.Author;
import org.example.models.Book;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AuthorService {

    public void createAuthor(Session session) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Author author = new Author();


        System.out.println("Enter author name");
        author.setName(scanner.nextLine());

        System.out.println("Enter author surname");
        author.setSurname(scanner.nextLine());

        System.out.println("Enter author date of birth YYYY-MM-DD");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
        author.setDateOfBirth(date);

        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
    }
    public List<Author> getAuthorList(Session session){
        return session.createQuery("from Author").list();
    }
    public void printAuthor(Author author){
        System.out.printf("Author name: %s, surname: %s, date of birth: %s\n",author.getName(), author.getSurname(), author.getDateOfBirth());
    }

    public void printAuthorList(List<Author> authorsList){
        for (Author author:authorsList
             ) {
            printAuthor(author);
        }
    }
    public void assignBookToAuthor(Book book, Author author){
        book.getAuthorList().add(author);
        author.getAuthorBooks().add(book);
    }

}
