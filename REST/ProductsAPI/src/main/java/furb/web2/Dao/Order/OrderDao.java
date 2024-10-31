package furb.web2.Dao.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import furb.web2.Models.Order.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	List<Order> findByUserId(long userId);
}
