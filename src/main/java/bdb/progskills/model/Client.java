package bdb.progskills.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String document;

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, String document) {
        this.name = name;
        this.document = document;
    }
}
