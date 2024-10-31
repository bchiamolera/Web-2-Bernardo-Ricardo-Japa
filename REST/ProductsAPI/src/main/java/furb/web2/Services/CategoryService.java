package furb.web2.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Category.CategoryDao;
import furb.web2.Dao.Product.ProductDao;
import furb.web2.Mapper.CategoryMapper;
import furb.web2.Mapper.ProductMapper;
import furb.web2.Models.Category.CategoryDTO;
import furb.web2.Models.Product.Product;
import furb.web2.Models.Product.ProductDTO;
import furb.web2.Util.ApiException;
import furb.web2.Models.Category.Category;

@Service
public class CategoryService {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public List<CategoryDTO> getAll() {
    	List<Category> categories = categoryDao.findAll();
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getById(long id) {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ApiException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
        return CategoryMapper.toDTO(category);
    }

    public List<CategoryDTO> listCategoriesWithNameContaining(String name) {
        List<Category> categories = categoryDao.findByCategoryNameContaining(name);
        if (categories.isEmpty()) {
            throw new ApiException("No categories found with name containing: " + name, HttpStatus.NOT_FOUND);
        }
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> listProductsByCategoryId(long categoryId) {
    	categoryDao.findById(categoryId)
                .orElseThrow(() -> new ApiException("Category not found with id: " + categoryId, HttpStatus.NOT_FOUND));
    	
        List<Product> products = productDao.findByCategoryId(categoryId);
        if (products == null || products.isEmpty()) {
        	throw new ApiException("No products found with categoryId: " + categoryId, HttpStatus.NOT_FOUND);
        }
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO create(CategoryDTO categoryDto) {
    	if (categoryDto.getName() == null) {
            throw new ApiException("Name cannot be null", HttpStatus.BAD_REQUEST);
        }
    	
        Category category = new Category();
        category.setName(categoryDto.getName());

        category = categoryDao.save(category);
        return CategoryMapper.toDTO(category);
    }

    @Transactional
    public CategoryDTO update(long categoryId, CategoryDTO categoryDto) {
        Category category = categoryDao.findById(categoryId).orElseThrow(() -> new ApiException("Category not found with id: " + categoryId, HttpStatus.NOT_FOUND));
        
        if (categoryDto.getName() != null) {
            category.setName(categoryDto.getName());
        }
        
        category = categoryDao.save(category);
        return CategoryMapper.toDTO(category);
    }

    @Transactional
    public void delete(long categoryId) {
    	Category category = CategoryMapper.toEntity(getById(categoryId));
        categoryDao.delete(category);
    }
}
