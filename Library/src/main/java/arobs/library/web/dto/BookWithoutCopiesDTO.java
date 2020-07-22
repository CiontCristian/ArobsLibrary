package arobs.library.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookWithoutCopiesDTO extends BaseDTO{
    private String title;
    private String author;
    private String description;
    private String tags;
    private Date date;
}
