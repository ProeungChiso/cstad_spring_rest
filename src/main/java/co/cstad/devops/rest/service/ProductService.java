package co.cstad.devops.rest.service;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductEditRequest;
import co.cstad.devops.rest.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> findProducts(String name, Boolean status);
    ProductResponse findProductById(Integer id);
    ProductResponse findProductByUuid(String uuid);
    void createNewProduct(ProductCreateRequest request);
    void editProductById(Integer id, ProductEditRequest request);
    void deleteProductById(Integer id);


}
