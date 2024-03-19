package co.cstad.devops.rest.service.impl;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductEditRequest;
import co.cstad.devops.rest.dto.ProductResponse;
import co.cstad.devops.rest.model.Product;
import co.cstad.devops.rest.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> productList;
    public ProductServiceImpl(){
        productList = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1);
        p1.setUuid(UUID.randomUUID().toString());
        p1.setName("IPhone 12 Pro Max");
        p1.setPrice(829.90);
        p1.setQty(2);
        p1.setImportedDate(LocalDateTime.now());
        p1.setStatus(true);
        Product p2 = new Product();
        p2.setId(1);
        p2.setUuid(UUID.randomUUID().toString());
        p2.setName("IPhone 14 Pro Max");
        p2.setPrice(1199.90);
        p2.setQty(4);
        p2.setImportedDate(LocalDateTime.now());
        p2.setStatus(true);
        Product p3 = new Product();
        p3.setId(1);
        p3.setUuid(UUID.randomUUID().toString());
        p3.setName("Macbook Pro M2");
        p3.setPrice(2199.90);
        p3.setQty(10);
        p3.setImportedDate(LocalDateTime.now());
        p3.setStatus(true);
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);

    }


    @Override
    public List<ProductResponse> getProducts(String name, Boolean status) {
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
    public ProductResponse getProductById(Integer id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).findFirst().orElseThrow();
    }

    @Override
    public ProductResponse getProductByUuid(String uuid) {
        return productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                ))
                .findFirst().orElseThrow();
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        Product newProduct = new Product();
        newProduct.setId(productList.size());
        newProduct.setUuid(UUID.randomUUID().toString());
        newProduct.setName(request.name());
        newProduct.setPrice(request.price());
        newProduct.setQty(request.qty());
        newProduct.setImportedDate(LocalDateTime.now());
        newProduct.setStatus(true);
        productList.add(newProduct);
    }

    @Override
    public void editProductByUuid(String uuid, ProductEditRequest request) {
        long count = productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .peek(oldProduct -> {
                    oldProduct.setName(request.name());
                    oldProduct.setPrice(request.price());
                }).count();
        System.out.println("Affected row: "+count);
    }

    @Override
    public void deleteProductByUuid(String uuid) {
        productList.removeIf(product -> product.getUuid().equals(uuid));
    }
}
