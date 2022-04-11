package bdb.progskills.controller;

import bdb.progskills.model.Payment;
import bdb.progskills.pojo.PaymentRequest;
import bdb.progskills.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    @ApiOperation("Get all payments")
    public ResponseEntity<List<Payment>> getAll() {
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @ApiOperation("Search payments by Client Id")
    public ResponseEntity<List<Payment>> getByClientId(@ApiParam(value = "Id of Client", required = true, example = "2") @PathVariable("id")  Long idClient) {
        return new ResponseEntity<>(paymentService.getByClientId(idClient), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation("Save payment by Client Id and Product Ids")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client with ID not found"),
            @ApiResponse(code = 404, message = "Some products were not found")
    })
    public ResponseEntity<Payment> save(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.createPayment(paymentRequest), HttpStatus.CREATED);
    }

}
