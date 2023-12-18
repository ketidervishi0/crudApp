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

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
//        List<Book> bookList = new ArrayList<>();
        return bookRepo.findAll();
    }
}
