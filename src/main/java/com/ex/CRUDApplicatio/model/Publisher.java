package com.ex.CRUDApplicatio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "publisher")
@Data

public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long publisherId;
    private int year;
    private String publisherName;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    private List<Book> books;

}
