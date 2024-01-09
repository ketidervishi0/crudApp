package com.ex.CRUDApplicatio.controller;

import com.ex.CRUDApplicatio.mappers.RestResponseMapper;
import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.model.Review;
import com.ex.CRUDApplicatio.request.ReviewRequest;
import com.ex.CRUDApplicatio.service.AuthorService;
import com.ex.CRUDApplicatio.service.BookService;
import com.ex.CRUDApplicatio.service.ReviewService;
import com.ex.CRUDApplicatio.model.Author;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ex.CRUDApplicatio.constants.Messages.*;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final AuthorService authorService;

    public ReviewController(ReviewService reviewService, ModelMapper modelMapper , AuthorService authorService) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
        this.authorService=authorService;
    }

    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<Object>getReviewById(@PathVariable @NotNull Long reviewId){
        try{
            Review review = reviewService.getReviewById(reviewId);
            if (review == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, RECORDS_RECEIVED);
            }
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, review, RECORDS_RECEIVED);
        }
        catch (Exception ex) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, RECORDS_RECEIVED);
    }
    }

    @PostMapping("/addReview")
    public ResponseEntity<Object>addReview(@RequestBody @Valid ReviewRequest reviewRequest){
        try{

            Review review = modelMapper.map(reviewRequest, Review.class);
            review = reviewService.addReview(review);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, review, RECORD_CREATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PostMapping("/addReviewForAuthor")
    public ResponseEntity<Object>addReviewForAuthor(@RequestBody @Valid ReviewRequest reviewRequest , @RequestParam Long authorId){
        try{
            Author author = authorService.findById(reviewRequest.getAuthorId());
            if (author == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
            }

            Review review = modelMapper.map(reviewRequest, Review.class);
            review.setAuthor(author);

            Review addedReview = reviewService.addReview(review);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, addedReview, RECORD_CREATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);

        }
    }

    @DeleteMapping("/deleteReviewById/{reviewId}")
    public ResponseEntity<Object> deleteReviewById(@PathVariable @NotNull Long reviewId) {
        try {
            Review review = reviewService.getReview(reviewId);
            if (review == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }
            reviewService.delete(reviewId);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);

        }
    }

    @PutMapping("/updateReviewById/{id}")
    public ResponseEntity<Object> updateReviewById(@PathVariable @NotNull Long reviewId, @Valid @RequestBody ReviewRequest reviewRequest) {
        try {
            Review review = reviewService.getReview(reviewId);
            if (review == null) {
                return RestResponseMapper.map(SUCCESS, HttpStatus.NOT_FOUND, null, NOT_FOUND);
            }
            review = modelMapper.map(reviewRequest, Review.class);
            review = reviewService.update(review, reviewId);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, review, RECORD_UPDATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }


}
