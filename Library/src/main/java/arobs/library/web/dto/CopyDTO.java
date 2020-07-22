package arobs.library.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class CopyDTO extends BaseDTO{
    private Integer code;
    private Boolean isAvailable;
    private String status;
    private BookWithoutCopiesDTO book;
}
