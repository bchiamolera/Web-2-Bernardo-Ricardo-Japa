package furb.web2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import furb.web2.Models.Order.OrderCreateDTO;
import furb.web2.Models.Order.OrderDTO;
import furb.web2.Services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
    	List<OrderDTO> orders = orderService.getAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable long id) {
    	OrderDTO order = orderService.getById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderCreateDTO orderDto) {
    	OrderDTO order = orderService.create(orderDto);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/remove-item/{itemId}")
    public ResponseEntity<OrderDTO> removeItemFromOrder(@PathVariable long orderId, @PathVariable long itemId) {
    	OrderDTO order = orderService.removeItem(orderId, itemId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
