package bdb.progskills.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "id_client")
    private Long idClient;

    private Integer idBill;

    @Transient
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private Client client;

    @OneToMany(mappedBy = "payment", cascade = {CascadeType.ALL})
    private List<PaymentProduct> products;

    private LocalDateTime date;



}
