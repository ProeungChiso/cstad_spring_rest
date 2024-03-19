package co.cstad.devops.rest.service;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts(String name, Boolean status);
    ProductResponse getProductById(Integer id);
    ProductResponse getProductByUuid(String uuid);
    void createNewProduct(ProductCreateRequest request);

}
