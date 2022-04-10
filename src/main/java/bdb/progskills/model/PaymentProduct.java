package bdb.progskills.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_product")
public class PaymentProduct {

    @EmbeddedId
    private PaymentProductPK id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_payment", nullable = false, insertable = false, updatable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, insertable = false, updatable = false)
    private Product product;

    public PaymentProduct(Payment payment, Product product) {
        this.payment = payment;
        this.product = product;
        this.id = new PaymentProductPK(payment.getId(), product.getId());
    }
}
