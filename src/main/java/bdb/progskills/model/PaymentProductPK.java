package bdb.progskills.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PaymentProductPK implements Serializable {

    @Column(name = "id_payment")
    private Long idPayment;

    @Column(name = "id_product")
    private Long idProduct;

}
