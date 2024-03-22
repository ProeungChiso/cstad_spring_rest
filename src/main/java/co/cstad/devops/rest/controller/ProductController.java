package co.cstad.devops.rest.controller;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductEditRequest;
import co.cstad.devops.rest.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    ResponseEntity<?> findProducts(@RequestParam(required = false, defaultValue = "") String name,
                                   @RequestParam(required = false, defaultValue = "true") Boolean status) {

        Map<String, Object> data = Map.of(
                "message", "Products have been found",
                "data", productService.getProducts(name, status));
        //return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
        return ResponseEntity.accepted().body(data);
    }

    @GetMapping("/{id}")
    Map<String, Object> getProductById(@PathVariable Integer id){
        return Map.of(
                "data",productService.getProductById(id)
        );
    }
    @GetMapping("/uuid/{uuid}")
    Map<String, Object> getProductByUuid(@PathVariable String uuid){
        return Map.of(
                "data",productService.getProductByUuid(uuid)
        );
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }
    @PutMapping("/{uuid}")
    void editProductByUuid(@PathVariable String uuid, @RequestBody ProductEditRequest request){
        productService.editProductByUuid(uuid, request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }
}
