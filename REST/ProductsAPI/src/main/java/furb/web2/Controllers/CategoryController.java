package furb.web2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import furb.web2.Models.Category.CategoryDTO;
import furb.web2.Models.Product.ProductDTO;
import furb.web2.Services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    	List<CategoryDTO> items = categoryService.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
    	CategoryDTO item = categoryService.getById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchCategories(@RequestParam String name) {
    	List<CategoryDTO> items = categoryService.listCategoriesWithNameContaining(name);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable long categoryId) {
    	List<ProductDTO> products = categoryService.listProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDto) {
    	CategoryDTO item = categoryService.create(categoryDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable long id, @RequestBody CategoryDTO categoryDto) {
    	CategoryDTO item = categoryService.update(id, categoryDto);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
