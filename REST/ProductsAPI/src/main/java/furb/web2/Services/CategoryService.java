package furb.web2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Category.CategoryDao;
import furb.web2.Models.Category.CategoryDTO;
import furb.web2.Models.Category.Category;

@Service
public class CategoryService {
	
	private final CategoryDao categoryDao;
	
	@Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
	
	public Category getCategoryById(long id) {
		Optional<Category> categoryIdentity = categoryDao.findById(id);
		return categoryIdentity.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
	}
	
	public List<Category> listCategoriesByName(String name) {
		return categoryDao.findByNameContaining(name);
	}
	
	@Transactional
	public Category create(CategoryDTO categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getName());
		
		category = categoryDao.save(category);
		
		return category;
	}
	
}
