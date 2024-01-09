package com.ex.CRUDApplicatio.service;

import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.model.Review;
import com.ex.CRUDApplicatio.repo.ReviewRepo;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    public Review addReview(Review review) {
        return reviewRepo.save(review);
    }

    public Review getReview(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    public void delete(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }

    public Review update(Review review, Long reviewId) {
        review.setReviewId(reviewId);
        return reviewRepo.save(review);
    }
}
