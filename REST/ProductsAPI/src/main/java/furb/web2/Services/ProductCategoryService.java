package furb.web2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.ProductCategory.ProductCategoryDao;
import furb.web2.Models.Category.Category;
import furb.web2.Models.Category.CategoryDTO;
import furb.web2.Models.Product.Product;
import furb.web2.Models.ProductCategory.ProductCategory;

@Service
public class ProductCategoryService {
	
	private final ProductCategoryDao productCategoryDao;
	
	@Autowired
	public ProductCategoryService(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}
	
	@Transactional
	public ProductCategory create(Product product, Category category) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProduct(product);
		productCategory.setCategory(category);
		
		productCategory = productCategoryDao.save(productCategory);
		
		return productCategory;
	}
}
