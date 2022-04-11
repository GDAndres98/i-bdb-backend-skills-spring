package bdb.progskills.service;

import bdb.progskills.model.Client;
import bdb.progskills.model.Product;
import bdb.progskills.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent())
            return productOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found");
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        HashSet<Long> uniqueProductIds = new HashSet<Long>(productIds);
        List<Product> products = productRepository.findByIdIn(new ArrayList<Long>(uniqueProductIds));
        if (uniqueProductIds.size() != products.size())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Some products were not found");
        return products;
    }


}
