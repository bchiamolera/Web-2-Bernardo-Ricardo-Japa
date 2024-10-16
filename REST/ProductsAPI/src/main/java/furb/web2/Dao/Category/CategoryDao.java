package furb.web2.Dao.Category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Category.Category;

public interface CategoryDao extends CrudRepository<Category, Integer> {
	List<Category> findByNameContaining();
}
