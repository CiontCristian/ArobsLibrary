package arobs.library.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentRequest extends BaseEntity<Long>{
    @ManyToOne
    private Book book;

    @ManyToOne
    private Employee employee;

    private Date requestDate;
    private String status;
}
