package com.ex.CRUDApplicatio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;
}
