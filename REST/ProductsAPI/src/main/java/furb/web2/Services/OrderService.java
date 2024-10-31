package furb.web2.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Order.OrderDao;
import furb.web2.Dao.User.UserDao;
import furb.web2.Dao.Item.ItemDao;
import furb.web2.Models.Item.Item;
import furb.web2.Models.Order.Order;
import furb.web2.Models.Order.OrderCreateDTO;
import furb.web2.Models.Order.OrderDTO;
import furb.web2.Models.User.User;
import furb.web2.Util.ApiException;
import furb.web2.Mapper.OrderMapper;

@Service
public class OrderService {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final ItemDao itemDao;

    @Autowired
    public OrderService(OrderDao orderDao, UserDao userDao, ItemDao itemDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.itemDao = itemDao;
    }

    public List<OrderDTO> getAll() {
    	List<Order> orders = orderDao.findAll();
        return orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getById(long id) {
        Order order = orderDao.findById(id).orElseThrow(() -> new ApiException("Order not found with id: " + id, HttpStatus.NOT_FOUND));
        return OrderMapper.toDTO(order);
    }

    @Transactional
    public OrderDTO create(OrderCreateDTO orderDto) {
        User user = userDao.findById(orderDto.getUserId())
                .orElseThrow(() -> new ApiException("User not found with id: " + orderDto.getUserId(), HttpStatus.NOT_FOUND));

        Order order = new Order();
        order.setUser(user);
        return OrderMapper.toDTO(orderDao.save(order));
    }

    @Transactional
    public OrderDTO removeItem(long orderId, long itemId) {
        Order order = orderDao.findById(orderId).orElseThrow(() -> new ApiException("Order not found with id: " + orderId, HttpStatus.NOT_FOUND));
        Item item = itemDao.findById(itemId)
                .orElseThrow(() -> new ApiException("Item not found with id: " + itemId, HttpStatus.NOT_FOUND));

        if (order.getItems().remove(item)) {
            itemDao.delete(item);
        }

        return OrderMapper.toDTO(orderDao.save(order));
    }

    @Transactional
    public void delete(long orderId) {
    	Order order = OrderMapper.toEntity(getById(orderId));
        orderDao.delete(order);
    }
}
