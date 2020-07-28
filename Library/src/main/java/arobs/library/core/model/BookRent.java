package arobs.library.core.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookRent extends BaseEntity<Long>{
    @ManyToOne
    private Book book;

    @ManyToOne
    private Employee employee;

    @OneToOne
    private Copy copy;

    private Date rentalDate;
    private Date returnDate;
    private String status;
    private Integer grade;
}
