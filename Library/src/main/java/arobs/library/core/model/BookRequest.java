package arobs.library.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookRequest extends BaseEntity<Long>{
    private String title;
    private String author;
    private String publisher;
    private String website;
    private Integer nrOfCopies;
    private Double cost;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;
}
