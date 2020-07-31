package arobs.library.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookRequestDTO extends BaseDTO{
    private String title;
    private String author;
    private String publisher;
    private String website;
    private Integer nrOfCopies;
    private Double cost;
    private String status;
    private EmployeeWithoutRequestsDTO employee;
}
