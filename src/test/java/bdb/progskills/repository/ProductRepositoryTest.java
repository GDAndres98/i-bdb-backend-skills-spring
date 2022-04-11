package bdb.progskills.repository;

import bdb.progskills.model.Product;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Test
    void itShouldGetProductsByIds() {
        // given
        List<Product> productSaved = new ArrayList<>();
        productSaved.add(new Product("Teclado", 300000.0));
        productSaved.add(new Product("Pantalla", 1500000.0));
        productSaved.add(new Product("Mouse", 23999.9));

       underTest.saveAll(productSaved);
        List<Long> productIds = new ArrayList<>();
        for (Product product : productSaved)
            productIds.add(product.getId());

        // when
        List<Product> productsResult = underTest.findByIdIn(productIds);

        //then
        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id")
                .build();

        assertThat(productsResult).usingRecursiveFieldByFieldElementComparator(configuration)
                .containsExactlyElementsOf(productSaved);
    }

}