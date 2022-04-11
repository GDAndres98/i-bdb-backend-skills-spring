package bdb.progskills.service;

import bdb.progskills.model.Client;
import bdb.progskills.model.Payment;
import bdb.progskills.model.Product;
import bdb.progskills.pojo.PaymentRequest;
import bdb.progskills.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment createPayment(PaymentRequest paymentRequest) {
        Client client = clientService.getById(paymentRequest.getIdClient());
        List<Product> products = productService.getProductsByIds(paymentRequest.getProductIds());
        Payment payment = new Payment();
        payment.setClient(client);
        payment.setIdBill(paymentRequest.getIdBill());
        paymentRepository.save(payment);
        for (Product product : products)
            payment.addProduct(product);
        return paymentRepository.save(payment);
    }
}
