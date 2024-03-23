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
    ResponseEntity<?> findProduct(@RequestParam(required = false, defaultValue = "") String name,
                                  @RequestParam(required = false, defaultValue = "true") Boolean status) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", productService.findProducts(name, status)
                )
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<?> findProductById(@PathVariable Integer id) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", productService.findProductById(id)
                )
        );
    }
    @GetMapping("/uuid/{uuid}")
    ResponseEntity<?> findProductByUuid(@PathVariable String uuid) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data",productService.findProductByUuid(uuid)
                )
        );
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }

    @PutMapping("/{id}")
    void editProductById(@PathVariable Integer id, @Valid @RequestBody ProductEditRequest request){
        productService.editProductById(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}
