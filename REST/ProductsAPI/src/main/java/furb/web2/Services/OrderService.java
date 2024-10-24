package furb.web2.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Order.OrderDao;
import furb.web2.Models.Category.Category;
import furb.web2.Models.Order.Order;
import furb.web2.Models.Order.OrderCreateDTO;
import furb.web2.Models.Order.OrderDTO;
import furb.web2.Models.User.User;

@Service
public class OrderService {

	private final OrderDao orderDao;
	private final JwtUserDetailsService userService;
	private final ProductService productService;
	
	@Autowired
	public OrderService(OrderDao orderDao, JwtUserDetailsService userService, ProductService productService) {
		this.orderDao = orderDao;
		this.userService = userService;
		this.productService = productService;
	}
	
	public Order getById(long id) {
		Optional<Order> order = orderDao.findById(id);
		return order.orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
	}
	
	@Transactional
	public Order create(OrderCreateDTO orderDto) {
		Order order = new Order();
		User user = userService.getById(orderDto.getUserId());
		order.setUser(user);
		order = orderDao.save(order);
		
		return order;
	}
}
