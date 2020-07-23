package arobs.library.core.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Copy extends BaseEntity<Long> {
    @Column(unique = true)
    private Integer code;
    private Boolean isAvailable;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Override
    public String toString() {
        return "Copy{" +
                "code='" + code + '\'' +
                ", isAvailable=" + isAvailable +
                + '\'' + ", status=" + status +
                '}';
    }
}
