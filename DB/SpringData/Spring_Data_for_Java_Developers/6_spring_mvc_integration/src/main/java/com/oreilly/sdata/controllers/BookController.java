package com.oreilly.sdata.controllers;

import com.oreilly.sdata.data.entities.Book;
import com.oreilly.sdata.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/books")
    public String showBooks(Model model) {
        final List<Book> books = repository.findAll();
        model.addAttribute("page", books);
        return "books";
    }

    @RequestMapping("/books/pageable")
    public String showBooksPageable(Model model, Pageable pageable) {
        final Page<Book> books = repository.findAll(pageable);
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * Book передается в аргументе метода благодаря бину
     *
     *      <bean class="org.springframework.data.repository.support.DomainClassConverter">
     *         <constructor-arg name="conversionService" ref="conversionService"/>
     *      </bean>
     */
    @RequestMapping("/books/{bookId}")
    public String showBook(@PathVariable("bookId") Book book, Model model) {
        model.addAttribute("book", book);
        return "book";
    }
}
