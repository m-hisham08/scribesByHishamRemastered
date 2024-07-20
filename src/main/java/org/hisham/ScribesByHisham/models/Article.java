package org.hisham.ScribesByHisham.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hisham.ScribesByHisham.models.audit.DateAudit;
import org.hisham.ScribesByHisham.models.audit.UserDateAudit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

/*
* title
* published_date
* minute_read
* content
*
* */

public class Article extends DateAudit {
    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content; //  or a rich text field type

    @Min(value = 1, message = "Estimated reading time must be at least 1 minute")
    private int minuteRead;
}
