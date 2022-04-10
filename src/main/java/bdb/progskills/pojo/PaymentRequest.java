package bdb.progskills.pojo;

import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Setter
public class PaymentRequest {

    private Long idClient;
    private Integer idBill;
    private List<Long> productIds;

    public Long getIdClient() {
        if(idClient == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idClient is required");
        return idClient;
    }

    public Integer getIdBill() {
        if(idBill == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idBill is required");
        return idBill;
    }

    public List<Long> getProductIds() {
        if (productIds == null || productIds.size() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productIds are required");
        return productIds;
    }
}
