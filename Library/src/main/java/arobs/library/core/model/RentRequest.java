package arobs.library.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentRequest extends BaseEntity<Long>{
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    private Employee employee;

    private Date requestDate;
    private String status;
}
