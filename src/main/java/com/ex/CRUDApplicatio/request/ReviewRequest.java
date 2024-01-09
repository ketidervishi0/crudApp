package com.ex.CRUDApplicatio.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import com.ex.CRUDApplicatio.model.Author;

@Data
public class ReviewRequest {

    @NotBlank(message = "Review text can not be null")
    private String reviewText;

    //@NotBlank(message = "Rating can not be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be at most 10")
    private int rating;

    @Getter
    private Long authorId;

}
