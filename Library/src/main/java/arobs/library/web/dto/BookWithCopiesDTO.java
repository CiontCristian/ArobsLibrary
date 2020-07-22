package arobs.library.web.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookWithCopiesDTO extends BaseDTO {
    private String title;
    private String author;
    private String description;
    private String tags;
    private Date date;
    private List<CopyDTO> copies;

}
