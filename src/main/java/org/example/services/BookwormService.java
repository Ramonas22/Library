package org.example.services;

import org.example.models.Bookworm;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class BookwormService {

    public List<Bookworm> getBookwormList(Session session){
        return session.createQuery(" from Bookworm").list();
    }

    public void createBookworm(Session session){
        Scanner scanner = new Scanner(System.in);
        Bookworm bookworm = new Bookworm();

        System.out.println("Enter bookworm personal code");
        bookworm.setPersonalCode(scanner.nextLine());

        System.out.println("Enter bookworm name");
        bookworm.setName(scanner.nextLine());

        System.out.println("Enter bookworm surname");
        bookworm.setSurname(scanner.nextLine());

        System.out.println("Enter bookworm age");
        bookworm.setAge(scanner.nextInt());

        session.beginTransaction();
        session.save(bookworm);
        session.getTransaction().commit();

    }

    public void printBookworm(Bookworm bookworm){
        System.out.printf("Bookworm personal code: %s, name: %s, surname %s, age %s\n",
                bookworm.getPersonalCode(),bookworm.getName(),bookworm.getSurname(),bookworm.getAge());
    }
    public void printBookwormList(List<Bookworm> bookworms){
        for (Bookworm bookworm:bookworms
             ) {
            printBookworm(bookworm);
        }
    }
}
