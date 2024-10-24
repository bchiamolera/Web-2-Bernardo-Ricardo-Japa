package furb.web2.Dao.Order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Order.Order;

public interface OrderDao extends CrudRepository<Order, Long> {
	List<Order> findByUserId(long userId);
}
