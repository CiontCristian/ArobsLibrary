package arobs.library.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity<ID extends Serializable> implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
}
