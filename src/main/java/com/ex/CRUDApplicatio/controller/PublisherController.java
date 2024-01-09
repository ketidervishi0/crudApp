package com.ex.CRUDApplicatio.controller;

import com.ex.CRUDApplicatio.mappers.RestResponseMapper;
import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.model.Publisher;
import com.ex.CRUDApplicatio.request.BookRequest;
import com.ex.CRUDApplicatio.request.PublisherRequest;
import com.ex.CRUDApplicatio.service.BookService;
import com.ex.CRUDApplicatio.service.PublisherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ex.CRUDApplicatio.constants.Messages.*;
import static com.ex.CRUDApplicatio.constants.Messages.SERVER_ERROR;

@RestController
@RequestMapping(value = "/publisher")

public class PublisherController {

    private final PublisherService publisherService;
    private final ModelMapper modelMapper;

    public PublisherController(PublisherService publisherService, ModelMapper modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addPublisher")
    public ResponseEntity<Object> addPublisher(@RequestBody @Valid PublisherRequest publisherRequest) {
        try {
            Publisher publisher = modelMapper.map(publisherRequest, Publisher.class);
            publisher = this.publisherService.addPublisher(publisher);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, publisher, RECORD_CREATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @GetMapping("/getPublisherById/{publisherId}")
    public ResponseEntity<Object> getPublisherById(@PathVariable @NotNull Long publisherId) {

        try {
            Publisher publisher = publisherService.get(publisherId);
            if (publisher == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, publisher, RECORDS_RECEIVED);
        } catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, RECORDS_RECEIVED);
        }
    }

    @DeleteMapping("/deletePublisherById/{publisherId}")
    public ResponseEntity<Object> deletePublisherById(@PathVariable @NotNull Long publisherId) {
        try {
            Publisher publisher = publisherService.get(publisherId);
            if (publisher == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }
            publisherService.deletePublisher(publisherId);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (
                Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

}