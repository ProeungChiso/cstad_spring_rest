package co.cstad.devops.rest.controller;

import co.cstad.devops.rest.dto.ProductCreateRequest;
import co.cstad.devops.rest.dto.ProductEditRequest;
import co.cstad.devops.rest.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping()
    Map<String, Object> getProducts(@RequestParam(required = false, defaultValue = "") String name,
                                    @RequestParam(required = false, defaultValue = "true") Boolean status)
    {
        return Map.of(
                "data",productService.getProducts(name, status)
        );
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
    void createNewProduct(@RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }
    @PutMapping("/edit/{uuid}")
    void editProductByUuid(@PathVariable String uuid, @RequestBody ProductEditRequest request){
        productService.editProductByUuid(uuid, request);
    }
    @DeleteMapping("/delete/{uuid}")
    void deleteProductByUuid(@PathVariable String uuid){
        productService.deleteProductByUuid(uuid);
    }
}
