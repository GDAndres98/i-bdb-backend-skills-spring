package bdb.progskills.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @CreationTimestamp
    private LocalDateTime date;

    @Transient
    private Double total;


    @ManyToOne
    @JoinColumn(name = "id_client", insertable = false, updatable = false)
    private Client client;

    @OneToMany(mappedBy = "payment", cascade = {CascadeType.ALL})
    private List<PaymentProduct> products = new ArrayList<PaymentProduct>();

    public Double getTotal() {
        double total = 0;
        for (PaymentProduct paymentProduct : products)
            total += paymentProduct.getProduct().getPrice();
        return total;
    }

    public void addProduct(Product product) {
        products.add(new PaymentProduct(this, product));
    }


}
