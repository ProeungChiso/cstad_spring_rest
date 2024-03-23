package co.cstad.devops.rest.repository;

import co.cstad.devops.rest.dto.ProductResponse;
import co.cstad.devops.rest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    ProductResponse findByUuid(String uuid);
}
