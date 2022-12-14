package kodlama.io.devs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "languages")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "language",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Framework> frameworks;
}
