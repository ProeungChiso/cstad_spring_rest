package co.cstad.devops.rest.service.impl;

import co.cstad.devops.rest.dto.CategoryRequest;
import co.cstad.devops.rest.dto.CategoryResponse;
import co.cstad.devops.rest.model.Category;
import co.cstad.devops.rest.repository.CategoryRepository;
import co.cstad.devops.rest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryResponse> findCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getName(),
                        category.getDescription()
                )).toList();
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found1"
                ));
        return new CategoryResponse(category.getName(), category.getDescription());
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        if (!categoryRepository.existsByName(name)){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "Category name has not been found"
            );
        }
        return categoryRepository.findByName(name);
    }

    @Override
    public void createNewCategory(CategoryRequest request) {

        // check category if exist
        if (categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exist!"
            );
        }

        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryRequest request) {

        // load old category
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found"
                ));
        category.setName(request.name());
        category.setDescription(request.description());

        categoryRepository.save(category);

        return this.findCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {

        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category has not been found"
            );
        }

        categoryRepository.deleteById(id);

    }
}
