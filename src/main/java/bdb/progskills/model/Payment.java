package bdb.progskills.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idBill;

    @CreationTimestamp
    private LocalDateTime date;

    @Transient
    private Double total;


    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "payment", cascade = {CascadeType.ALL})
    private List<PaymentProduct> paymentProducts = new ArrayList<PaymentProduct>();

    public Double getTotal() {
        double total = 0;
        for (PaymentProduct paymentProduct : paymentProducts)
            total += paymentProduct.getProduct().getPrice();
        return total;
    }

    public void addProduct(Product product) {
        paymentProducts.add(new PaymentProduct(this, product));
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (PaymentProduct paymentProduct : paymentProducts)
            products.add(paymentProduct.getProduct());
        return products;
    }


}
