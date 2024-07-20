package org.hisham.ScribesByHisham.payloads.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponseDTO {
    private String id;
    private String title;
    private String content;
    private String published_date;
    private int minute_read;
}
