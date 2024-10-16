package furb.web2.Dao.Product;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Product.Product;

public interface ProductDao extends CrudRepository<Product, Integer> {
	List<Product> findByNameContaining();
	List<Product> findByCategory();
	List<Product> findByPriceRange();
}
