package co.cstad.devops.rest.controller;

import co.cstad.devops.rest.dto.CategoryRequest;
import co.cstad.devops.rest.dto.CategoryResponse;
import co.cstad.devops.rest.model.Product;
import co.cstad.devops.rest.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Categories not found",
                    content = @Content)
    })
    @GetMapping
    ResponseEntity<?> findCategories(){
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", categoryService.findCategories()
                )
        );
    }

    @GetMapping("/name/{name}")
    ResponseEntity<?> findCategoryByName(@PathVariable String name) {
        return ResponseEntity.accepted().body(
                Map.of(
                        "data",categoryService.findCategoryByName(name)
                )
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findCategoryById(@PathVariable Integer id){
        return ResponseEntity.accepted().body(
                Map.of(
                        "data", categoryService.findCategoryById(id)
                )
        );
    }

    @PostMapping
    void createNewCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.createNewCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    CategoryResponse editCategoryById(@PathVariable Integer id,@Valid @RequestBody CategoryRequest request) {
        return categoryService.editCategoryById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategoryById(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }

}
