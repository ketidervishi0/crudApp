package com.ex.CRUDApplicatio.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Title can not be null")
    private String title;

    @NotBlank(message = "Author can not be null")
    private String author;

    @NotBlank(message = "Description can not be null")
    private String description;

    @NotBlank(message = "Publication_date can not be null")
    private String publicationDate;

}
