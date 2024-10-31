package furb.web2.Dao.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import furb.web2.Models.Product.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	List<Product> findByProductNameContaining(String name);
	
	@Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    List<Product> findByCategoryId(long categoryId);
	
	@Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
	List<Product> findByPriceRange(double minPrice, double maxPrice);
}
