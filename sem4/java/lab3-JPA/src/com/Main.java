package com;

import dao.BookDao;
import schemas.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;
import java.util.stream.Collectors;

import static com.console.ConsoleElements.hr;
import static com.console.ConsolePrompt.*;

public class Main {
    private BookDao bookDao;

    private int limitToShow = 15;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        init();
        runCli();
    }

    private void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        bookDao = new BookDao(em);
    }

    private void runCli() {
        cliPrintHelp();
        hr();

        while (true) {
            String command = promptLine("Enter command: ").trim().toLowerCase();

            if (command.equals("exit")) break;
            else useCommand(command);

            hr();
        }

    }

    private void useCommand(String command) {
        switch (command) {
            case "showall":
                cliShowAllBooks(); break;
            case "add":
                cliAddBook(); break;
            case "remove":
                cliRemoveBook(); break;
            case "filter":
                cliShowBooksByFilter(); break;
            case "limit":
                cliSetLimit(); break;
            case "help":
                cliPrintHelp(); break;
            default:
                System.out.println("Command not found. Try again: ");
                break;
        }
    }

    private void cliAddBook() {
        Book book = createNewBook();
        bookDao.add(book);
        System.out.println("Done!");
    }

    private void cliRemoveBook() {
        int bookId = promptInt("Enter the book id");
        bookDao.delete(bookId);
        System.out.println("Done!");
    }

    private void cliShowAllBooks() {
        var books = bookDao.findAll(limitToShow);
        printBooks(books);
    }

    private void cliShowBooksByFilter() {
        printFiltersHelp();
        String filter = promptLine("Select Filter:").toLowerCase();

        switch (filter) {
            case "a": {
                String author = promptLine("Author:");
                var books = bookDao.findAllByAuthor(author, limitToShow);
                printBooks(books);

                break;
            }
            case "b": {
                String publisher = promptLine("Publisher:");
                var books = bookDao.findAllByPublisher(publisher, limitToShow);
                printBooks(books);

                break;
            }
            case "c": {
                LocalDate date = promptDate("Publish Date:");
                var books = bookDao.findAllByDate(date, limitToShow);
                printBooks(books);

                break;
            }
            case "d":
                var authors = bookDao.findAllAuthors(limitToShow);
                authors.forEach(System.out::println);

                break;
            case "e": {
                var publisher = bookDao.findAllPublishers(limitToShow);
                publisher.forEach(System.out::println);

                break;
            }
            case "f": {
                var books = bookDao.findAll(limitToShow);

                Map<String, HashSet<Book>> map = books.stream()
                        .collect(Collectors.groupingBy(Book::getPublisher, Collectors.toCollection(HashSet::new)));

                map.forEach((publisher, publishersBook) -> {
                    System.out.println(" - " + publisher);
                    printBooks(publishersBook);
                });

                break;
            }
            default:
                System.out.println("Can't find the filter");

                break;
        }

    }

    private void cliSetLimit() {
        System.out.println("Current limit: " + limitToShow);
        limitToShow = promptInt("Enter new limit: ");
    }

    private Book createNewBook() {
        Book book = new Book();

        book.setName(promptLine("Name:"));
        book.setAuthor(promptLine("Author:"));
        book.setPublisher(promptLine("Publisher"));
        book.setPublishDate(promptDate("Publish Date:"));
        book.setPages(promptInt("Pages:"));
        book.setPrice(promptDouble("Price:"));

        return book;
    }

    private void printBooks(Collection<Book> books) {
        if (books.isEmpty()) {
            System.out.println("There are no users");
            return;
        }

        books.forEach((item) -> System.out.println("\t" + item));
    }

    private void cliPrintHelp() {
        System.out.println("Commands:");
        System.out.println("> showAll #Show all books");
        System.out.println("> add #Add new book");
        System.out.println("> remove #Remove the book by ID");
        System.out.println("> filter #Show books by filter");
        System.out.println();
        System.out.println("> limit #Set Limit to show");
        System.out.println("> help #Print Help");
        System.out.println("> exit #Exit");
    }

    private void printFiltersHelp() {
        System.out.println("Filters:");
        System.out.println("> a #Список книг заданого автора в порядку зростання року видання");
        System.out.println("> b #Список книг, що видані заданим видавництвом");
        System.out.println("> c #Список книг, що випущені після заданої дати");
        System.out.println("> d #Список авторів в алфавітному порядку");
        System.out.println("> e #Список видавництв, книги яких зареєстровані в системі без повторів");
        System.out.println("> f #Для кожного видавництва визначити список книг, виданих ним");
    }

}
