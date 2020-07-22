package arobs.library.web.dto;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class EmployeeDTO extends BaseDTO{
    private String name;
    private String email;
    private String role;
    private String password;
}
