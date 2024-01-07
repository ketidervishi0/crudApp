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

    @Column(name = "author_id")
    private Long authorId; //Todo: convert to authorId, Create Author entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")

    private Author author;

}
