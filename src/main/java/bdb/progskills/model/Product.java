package bdb.progskills.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
