package bdb.progskills.service;

import bdb.progskills.model.Client;
import bdb.progskills.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long Id) {
        Optional<Client> clientOptional = clientRepository.findById(Id);
        if (clientOptional.isPresent())
            return clientOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with ID " + Id + " not found");
    }

    public Client addClient(Client client) {
        boolean existsDocument = clientRepository
                .existsByDocument(client.getDocument());
        if (existsDocument)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Document " + client.getDocument() + " already taken");

        return clientRepository.save(client);
    }


}
