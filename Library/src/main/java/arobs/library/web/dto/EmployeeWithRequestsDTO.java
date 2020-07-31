package arobs.library.web.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class EmployeeWithRequestsDTO extends BaseDTO{
    private String name;
    private String email;
    private String role;
    private String password;
    private List<BookRequestDTO> requests;
}
