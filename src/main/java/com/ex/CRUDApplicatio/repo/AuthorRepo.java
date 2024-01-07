package com.ex.CRUDApplicatio.repo;

import com.ex.CRUDApplicatio.model.Author;
import com.ex.CRUDApplicatio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
