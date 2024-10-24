package furb.web2.Dao.Category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Category.Category;
import furb.web2.Models.Product.Product;

public interface CategoryDao extends CrudRepository<Category, Long> {
	List<Category> findByNameContaining(String name);
}
