package furb.web2.Mapper;

import java.util.stream.Collectors;

import furb.web2.Models.Order.Order;
import furb.web2.Models.Order.OrderDTO;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUser(UserMapper.toDTO(order.getUser()));
        dto.setItems(order.getItems() != null ? 
            order.getItems().stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList()) : null);
        return dto;
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        order.setId(dto.getId());
        order.setUser(UserMapper.toEntity(dto.getUser()));
        return order;
    }
}
