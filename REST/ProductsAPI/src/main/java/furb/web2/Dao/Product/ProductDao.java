package furb.web2.Dao.Product;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Product.Product;

public interface ProductDao extends CrudRepository<Product, Long> {
	List<Product> findByNameContaining(String name);
	List<Product> findByCategoryId(long categoryId);
	List<Product> findByPriceRange(double minPrice, double maxPrice);
}
