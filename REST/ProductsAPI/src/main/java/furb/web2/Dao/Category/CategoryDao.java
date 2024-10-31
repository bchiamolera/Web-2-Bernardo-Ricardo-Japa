package furb.web2.Dao.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import furb.web2.Models.Category.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
	List<Category> findByCategoryNameContaining(String name);
}
