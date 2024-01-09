package com.ex.CRUDApplicatio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String publicationDate;
    private String description;

    @Column(name = "author_id", insertable=false, updatable=false)
    private Long authorId; //Todo: convert to authorId, Create Author entity

    @ManyToOne
    @JoinColumn(name = "author_id")

    private Author author;


    @Column(name = "publisher_id")
    private Long publisherId;

    @ManyToOne
    @JoinColumn(name = "publisher_id",insertable=false, updatable=false)

    private Publisher publisher;
}
