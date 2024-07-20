package org.hisham.ScribesByHisham.payloads.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
public class ArticleRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @Min(value = 1, message = "Estimated reading time must be at least 1 minute")
    private int minuteRead;
}
