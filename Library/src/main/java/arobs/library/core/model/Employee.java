package arobs.library.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseEntity<Long> {
    private String name;
    @Column(unique = true)
    private String email;
    private String role;
    private String password;
}
