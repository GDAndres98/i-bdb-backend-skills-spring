package bdb.progskills.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PaymentProductPK implements Serializable {

    @Column(name = "id_payment")
    private Integer idPayment;

    @Column(name = "id_product")
    private Integer idProduct;

}
