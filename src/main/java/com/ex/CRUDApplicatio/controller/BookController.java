package com.ex.CRUDApplicatio.controller;

import com.ex.CRUDApplicatio.mappers.RestResponseMapper;
import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.request.BookRequest;
import com.ex.CRUDApplicatio.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ex.CRUDApplicatio.constants.Messages.*;

//Todo: Check Swagger, Git/GitHub

@RestController
@RequestMapping(value = "/book")
//@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    /**
     * Returns all books
     *
     * @return data : List<Book>
     */
    @GetMapping("/getAll")
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable @NotNull Long id) {

        try {
            Book book = bookService.get(id);
            if (book == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, book, RECORDS_RECEIVED);
        } catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, RECORDS_RECEIVED);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody @Valid BookRequest bookRequest) {
        try {
            //book.setAuthor(bookRequest.getAuthor());
            //book.setTitle(bookRequest.getTitle());
            Book book = modelMapper.map(bookRequest, Book.class);
            book = this.bookService.save(book);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, book, RECORD_CREATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Object> updateBookById(@PathVariable @NotNull Long id, @Valid @RequestBody BookRequest bookRequest) {
        try {
            Book book = this.bookService.get(id);
            if (book == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }

            book = modelMapper.map(bookRequest, Book.class);
            book = bookService.update(book, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, book, RECORD_UPDATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable @NotNull Long id) {
        try {
            Book book = bookService.get(id);
            if (book == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }
            bookService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);

        }
    }
}
