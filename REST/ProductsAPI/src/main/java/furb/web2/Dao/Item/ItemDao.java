package furb.web2.Dao.Item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import furb.web2.Models.Item.Item;

public interface ItemDao extends JpaRepository<Item, Long> {
	@Query("SELECT i FROM Item i WHERE i.order.id = :orderId AND i.product.id = :productId")
    Optional<Item> findByOrderIdAndProductId(@Param("orderId") long orderId, @Param("productId") long productId);
}
