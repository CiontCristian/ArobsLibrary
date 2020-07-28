package arobs.library.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(name = "employeeWithRequests",
        attributeNodes = @NamedAttributeNode(value = "requests",
                subgraph = "requestWithEmployee"),
        subgraphs = @NamedSubgraph(name = "requestWithEmployee",
                attributeNodes = @NamedAttributeNode(value =
                        "employee")))
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

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
    private List<BookRequest> requests;
}
