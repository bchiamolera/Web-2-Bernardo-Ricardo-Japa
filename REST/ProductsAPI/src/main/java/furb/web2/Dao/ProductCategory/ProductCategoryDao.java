package furb.web2.Dao.ProductCategory;

import org.springframework.data.repository.CrudRepository;

import furb.web2.Models.ProductCategory.ProductCategory;

public interface ProductCategoryDao extends CrudRepository<ProductCategory, Long> {

}
