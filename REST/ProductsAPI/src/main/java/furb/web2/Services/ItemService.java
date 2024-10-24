package furb.web2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furb.web2.Dao.Item.ItemDao;
import furb.web2.Models.Item.Item;
import furb.web2.Models.Item.ItemCreateDTO;
import furb.web2.Models.Order.Order;
import furb.web2.Models.Product.Product;

@Service
public class ItemService {
	private final ItemDao itemDao;
	private final ProductService productService;
	private final OrderService orderService;
	
	@Autowired
	public ItemService(ItemDao itemDao, ProductService productService, OrderService orderService) {
		this.itemDao = itemDao;
		this.productService = productService;
		this.orderService = orderService;
	}
	
	public Item getById(long id) {
		Optional<Item> item = itemDao.findById(id);
		return item.orElseThrow(() -> new RuntimeException("Item not found with Id: " + id));
	}
	
	public Item create(ItemCreateDTO itemDto) {
		Item item = new Item();
		Order order = orderService.getById(itemDto.getOrderId());
		item.setOrder(order);
		Product product = productService.getById(itemDto.getProductId());
		item.setProduct(product);
		item.setQuantity(itemDto.getQuantity());
		
		item = itemDao.save(item);
		
		return item;
	}
	
	public void delete(long id) {
		itemDao.delete(getById(id));
	}
}
