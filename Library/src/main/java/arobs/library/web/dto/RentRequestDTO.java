package arobs.library.web.dto;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class RentRequestDTO extends BaseDTO{
    private BookWithCopiesDTO book;
    private EmployeeWithoutRequestsDTO employee;
    private CopyDTO copy;
    private Date requestDate;
    private String status;
}
