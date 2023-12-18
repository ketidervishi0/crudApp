package com.ex.CRUDApplicatio.controller;

import com.ex.CRUDApplicatio.mappers.RestResponseMapper;
import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.repo.BookRepo;
import com.ex.CRUDApplicatio.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ex.CRUDApplicatio.constants.Messages.*;

// TODO: Use try-catch in every method [E.g. getAllBooks()]
@RestController
@RequiredArgsConstructor
public class BookController {

    // TODO: replace all usages with bookService
    @Autowired
    private BookRepo bookRepo;

    private final BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Object> getAllBooks() {
        try {
            List<Book> bookList = this.bookService.findAll();
            if (bookList.isEmpty()) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, bookList, RECORDS_RECEIVED);
        } catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookData = bookRepo.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book bookObj = bookRepo.save(book);

        return new ResponseEntity<>(bookObj, HttpStatus.OK);

    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData) {
        Optional<Book> oldBookData = bookRepo.findById(id);

        if (oldBookData.isPresent()) {
            Book updateBookData = oldBookData.get();
            updateBookData.setTitle(newBookData.getTitle());
            updateBookData.setAuthor(newBookData.getAuthor());

            Book bookObj = bookRepo.save(updateBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
