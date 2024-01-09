package com.ex.CRUDApplicatio.repo;

import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
