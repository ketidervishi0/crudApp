package com.ex.CRUDApplicatio.service;

import com.ex.CRUDApplicatio.model.Book;
import com.ex.CRUDApplicatio.model.Publisher;
import com.ex.CRUDApplicatio.repo.PublisherRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    public void deletePublisher(Long publisherId) {
        publisherRepo.deleteById(publisherId);
    }

    public Publisher get(Long publisherId) {
        return publisherRepo.findById(publisherId).orElse(null);
    }

}
