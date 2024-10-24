package furb.web2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Product.ProductDao;
import furb.web2.Dao.ProductCategory.ProductCategoryDao;
import furb.web2.Models.Category.Category;
import furb.web2.Models.Category.CategoryDTO;
import furb.web2.Models.Product.Product;
import furb.web2.Models.Product.ProductCreateDTO;
import furb.web2.Models.Product.ProductDTO;
import furb.web2.Models.ProductCategory.ProductCategory;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final ProductCategoryService productCategoryservice;
	private final CategoryService categoryService;
	
	@Autowired
	public ProductService(ProductDao productDao, ProductCategoryService productCategoryservice, CategoryService categoryService) {
		this.productDao = productDao;
		this.productCategoryservice = productCategoryservice;
		this.categoryService = categoryService;
	}
	
	public Product getById(long id) {
		Optional<Product> product = productDao.findById(id);
		return product.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}
	
	public List<Product> listProductsByCategoryId(long categoryId) {
        return productDao.findByCategoryId(categoryId);
    }
	
	@Transactional
	public Product create(ProductCreateDTO productDto) {
		Product product = new Product();
		product.setProductName(productDto.getName());
		product.setPrice(productDto.getPrice());
		
		product = productDao.save(product);
		
		for (long categoryId : productDto.getCategoriasId()) {
			Category category = categoryService.getCategoryById(categoryId);
			productCategoryservice.create(product, category);
		}
		
		return product;
	}
}
