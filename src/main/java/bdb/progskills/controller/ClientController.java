package bdb.progskills.controller;

import bdb.progskills.model.Client;
import bdb.progskills.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    @ApiOperation("Get all clients")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Search client by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Client with ID not found")
    })
    public ResponseEntity<Client> getById(@ApiParam(value = "Id of Client", required = true, example = "7") @PathVariable("id") Long idClient) {
        return new ResponseEntity<>(clientService.getById(idClient), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation("Create a new client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "Document already taken")
    })
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.addClient(client), HttpStatus.CREATED);
    }

}
