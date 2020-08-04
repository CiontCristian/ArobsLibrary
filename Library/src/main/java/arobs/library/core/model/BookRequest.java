package arobs.library.core.model;

import lombok.*;

import javax.persistence.*;

@NamedEntityGraph(name = "BookRequestWithEmployee",
        attributeNodes = @NamedAttributeNode(value = "employee"))
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

    @Override
    public String toString(){
        return "book request";
    }
}
