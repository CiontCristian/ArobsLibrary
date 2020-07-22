package arobs.library.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NamedEntityGraph(name = "bookWithCopies",
        attributeNodes = @NamedAttributeNode(value = "copies",
                subgraph = "copyWithBook"),
        subgraphs = @NamedSubgraph(name = "copyWithBook",
                attributeNodes = @NamedAttributeNode(value =
                        "book")))
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity<Long> {
    private String title;
    private String author;
    @Column(length = 2000)
    private String description;
    private String tags;
    private Date date;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "book", orphanRemoval = true)
    private List<Copy> copies;
}
