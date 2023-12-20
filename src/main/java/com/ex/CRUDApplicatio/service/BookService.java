package com.ex.CRUDApplicatio.service;

import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // todo: check MVC design pattern
    // todo: krijo nje file per te shenuar shortcuts
    //  ctrl + alt + l = format code
    // todo: autowired vs constructor smth...
    // todo: What is a Bean?

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book get(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepo.save(book);
    }

    public Book update(Book book, Long id) {
        book.setId(id);
        return bookRepo.save(book);
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

}
