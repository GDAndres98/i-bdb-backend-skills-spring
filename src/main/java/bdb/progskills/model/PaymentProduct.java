package bdb.progskills.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_product")
public class PaymentProduct {

    @EmbeddedId
    private PaymentProductPK id;

    @ManyToOne
    @JoinColumn(name = "id_payment", insertable = false, updatable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private Product product;

}
