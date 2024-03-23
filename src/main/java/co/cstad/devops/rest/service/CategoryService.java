package co.cstad.devops.rest.service;

import co.cstad.devops.rest.dto.CategoryRequest;
import co.cstad.devops.rest.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findCategories();
    CategoryResponse findCategoryById(Integer id);
    CategoryResponse findCategoryByName(String name);
    void createNewCategory(CategoryRequest categoryRequest);
    CategoryResponse editCategoryById(Integer id, CategoryRequest categoryRequest);
    void deleteCategoryById(Integer id);

}
