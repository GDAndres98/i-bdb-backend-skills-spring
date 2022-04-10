package bdb.progskills.service;

import bdb.progskills.model.Client;
import bdb.progskills.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClientService(clientRepository);
    }

    @Test
    void canGetAllClients() {
        // when
        underTest.getAll();
        // then
        verify(clientRepository).findAll();
    }

    @Test
    void canAddClient() {
        // given
        Client client = new Client("Andres", "1544789656");

        // when
        underTest.addClient(client);

        //then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);

        verify(clientRepository).save(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();

        assertEquals(client, capturedClient);
    }

    @Test
    void willThrowConflictWhenDocumentIsTaken() {
        // given
        Client client = new Client("Andres", "1544789656");

        given(clientRepository.existsByDocument(client.getDocument()))
                .willReturn(true);

        // when
        //then
        assertThrows(ResponseStatusException.class, () -> {underTest.addClient(client);});

        verify(clientRepository, never()).save(any());
    }
}