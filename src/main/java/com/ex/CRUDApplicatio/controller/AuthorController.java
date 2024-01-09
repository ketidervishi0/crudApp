package com.ex.CRUDApplicatio.controller;


import com.ex.CRUDApplicatio.mappers.RestResponseMapper;
import com.ex.CRUDApplicatio.model.Author;
import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.request.AuthorRequest;
import com.ex.CRUDApplicatio.request.BookRequest;
import com.ex.CRUDApplicatio.service.AuthorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.ex.CRUDApplicatio.constants.Messages.*;
import static com.ex.CRUDApplicatio.constants.Messages.SERVER_ERROR;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getAllAuthors")
    public ResponseEntity<Object> getAllAuthors() {
        try {
            List<Author> authorList = this.authorService.findAll();
            if (authorList.isEmpty()) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, authorList, RECORDS_RECEIVED);
        } catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @GetMapping("/getAuthorById/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable @NotNull Long id) {

        try {
            Author author = authorService.get(id);
            if (author == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, author, RECORDS_RECEIVED);
        } catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, RECORDS_RECEIVED);
        }
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Object> addAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        try {
            Author author = modelMapper.map(authorRequest, Author.class);
            author = this.authorService.save(author);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, author, RECORD_CREATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Object> updateAuthorById(@PathVariable @NotNull Long id, @Valid @RequestBody AuthorRequest authorRequest) {
        try {
            Author author = this.authorService.get(id);
            if (author == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }

            author = modelMapper.map(authorRequest, Author.class);
            author = authorService.update(author, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, author, RECORD_UPDATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteAuthorById(@PathVariable @NotNull Long id) {
        try {
            Author author = authorService.get(id);
            if (author == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }
            authorService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);

        }
    }

}
