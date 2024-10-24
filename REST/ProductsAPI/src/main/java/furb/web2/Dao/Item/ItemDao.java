package furb.web2.Dao.Item;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import furb.web2.Models.Item.Item;

public interface ItemDao extends CrudRepository<Item, Long> {
	List<Item> findByOrderId(long orderId);
	List<Item> findByProductId(long productId);
}
