package bdb.progskills.repository;

import bdb.progskills.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldExistsByDocument() {
        // given
        String document = "75785478547";
        Client client = new Client("Nombre Generico", document);
        underTest.save(client);

        // when
        boolean exists = underTest.existsByDocument(document);

        //then
        assertTrue(exists);

    }

}