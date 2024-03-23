package co.cstad.devops.rest.service.impl;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductEditRequest;
import co.cstad.devops.rest.dto.ProductResponse;
import co.cstad.devops.rest.model.Product;
import co.cstad.devops.rest.repository.ProductRepository;
import co.cstad.devops.rest.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> findProducts(String name, Boolean status) {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(name) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product has not been found"
                )
        );
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty());
    }
    @Override
    public ProductResponse findProductByUuid(String uuid) {
        return productRepository.findByUuid(uuid);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product product = new Product();
        product.setUuid(UUID.randomUUID().toString());
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        product.setImportedDate(LocalDateTime.now());
        product.setStatus(true);
        productRepository.save(product);
    }

    @Override
    public void editProductById(Integer id, ProductEditRequest request) {
        Product product = productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product has not been found"
                )
        );
        product.setName(request.name());
        product.setPrice(request.price());
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        if (!productRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product has not been found"
            );
        }
        productRepository.deleteById(id);
    }
}
