package co.cstad.devops.rest.repository;

import co.cstad.devops.rest.dto.CategoryResponse;
import co.cstad.devops.rest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    CategoryResponse findByName(String name);
}
