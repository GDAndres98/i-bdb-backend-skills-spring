package bdb.progskills.service;

import bdb.progskills.model.Client;
import bdb.progskills.model.Product;
import bdb.progskills.repository.ProductRepository;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProductService(productRepository);
    }

    @Test
    void canGetAllProducts() {
        // when
        underTest.getAll();
        // then
        verify(productRepository).findAll();
    }

    @Test
    void canAddProduct() {
        // given
        Product product = new Product("Sistema de Sonido", 324999.9);

        // when
        underTest.addProduct(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        assertEquals(product, capturedProduct);
    }

    @Test
    void itWillGetProductsFormIdList() {
        // given
        List<Product> productsSaved = new ArrayList<>();
        productsSaved.add(new Product("Teclado", 300000.0));
        productsSaved.add(new Product("Pantalla", 1500000.0));
        productsSaved.add(new Product("Mouse", 23999.9));

        given(productRepository.findByIdIn(any())).willReturn(productsSaved);

        // when

        //then
        List<Product> productsResult = underTest.getProductsByIds(Arrays.asList(3L, 1L, 4L));

        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id")
                .build();

        assertThat(productsResult).usingRecursiveFieldByFieldElementComparator(configuration)
                .containsExactlyElementsOf(productsSaved);

        verify(productRepository, never()).save(any());
    }

    @Test
    void willThrowNotFoundWhenProductIdsAreNotFound() {
        // given
        List<Product> productsSaved = new ArrayList<>();
        productsSaved.add(new Product("Teclado", 300000.0));
        productsSaved.add(new Product("Pantalla", 1500000.0));
        productsSaved.add(new Product("Mouse", 23999.9));

        given(productRepository.findByIdIn(any())).willReturn(productsSaved);

        // when
        //then
        assertThrows(ResponseStatusException.class, () -> {
            underTest.getProductsByIds(Arrays.asList(3L, 1L));
        });

    }
}