package arobs.library.web.dto;

import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookRentDTO extends BaseDTO{
    private BookWithCopiesDTO book;
    private EmployeeWithoutRequestsDTO employee;
    private CopyDTO copy;
    private Date rentalDate;
    private Date returnDate;
    private String status;
    private Integer grade;
}
