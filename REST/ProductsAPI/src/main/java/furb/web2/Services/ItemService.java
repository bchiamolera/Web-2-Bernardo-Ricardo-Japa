package furb.web2.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Item.ItemDao;
import furb.web2.Dao.Order.OrderDao;
import furb.web2.Dao.Product.ProductDao;
import furb.web2.Models.Item.Item;
import furb.web2.Models.Item.ItemCreateDTO;
import furb.web2.Models.Item.ItemDTO;
import furb.web2.Models.Order.Order;
import furb.web2.Models.Product.Product;
import furb.web2.Util.ApiException;
import furb.web2.Mapper.ItemMapper;

@Service
public class ItemService {
    private final ItemDao itemDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;

    @Autowired
    public ItemService(ItemDao itemDao, OrderDao orderDao, ProductDao productDao) {
        this.itemDao = itemDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    public List<ItemDTO> getAll() {
    	List<Item> items =  itemDao.findAll();
        return items.stream()
        		.map(ItemMapper::toDTO)
        		.collect(Collectors.toList());
    }

    public ItemDTO getById(long id) {
    	Item item = itemDao.findById(id)
                .orElseThrow(() -> new ApiException("Item not found with Id: " + id, HttpStatus.NOT_FOUND));
        return ItemMapper.toDTO(item);
    }

    public ItemDTO getByOrderIdAndProductId(long orderId, long productId) {
    	orderDao.findById(orderId).orElseThrow(() -> new ApiException("Order not found with id " + orderId, HttpStatus.NOT_FOUND));
    	productDao.findById(productId).orElseThrow(() -> new ApiException("Product not found with id: " + productId, HttpStatus.NOT_FOUND));
    	Item item = itemDao.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new ApiException("Item not found for OrderId " + orderId + " and ProductId: " + productId, HttpStatus.NOT_FOUND));
        return ItemMapper.toDTO(item);
    }

    @Transactional
    public ItemDTO create(long orderId, ItemCreateDTO itemDto) {
    	if (itemDto.getQuantity() < 0) {
    		throw new ApiException("Invalid quantity", HttpStatus.BAD_REQUEST);
        }
    	
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new ApiException("Order not found with id: " + orderId, HttpStatus.NOT_FOUND));
        Product product = productDao.findById(itemDto.getProductId())
                .orElseThrow(() -> new ApiException("Product not found with id: " + itemDto.getProductId(), HttpStatus.NOT_FOUND));
        
        Item item = new Item();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(itemDto.getQuantity());
        item = itemDao.save(item);
        return ItemMapper.toDTO(item);
    }

    @Transactional
    public ItemDTO update(long orderId, ItemCreateDTO itemDto) {
        Item item = ItemMapper.toEntity(getByOrderIdAndProductId(orderId, itemDto.getProductId()));
        if (itemDto.getQuantity() < 0) {
        	throw new ApiException("Invalid quantity", HttpStatus.BAD_REQUEST);
        }
        item.setQuantity(itemDto.getQuantity());
        item = itemDao.save(item);
        return ItemMapper.toDTO(item);
    }

    @Transactional
    public void delete(long id) {
        Item item = ItemMapper.toEntity(getById(id));
        itemDao.delete(item);
    }
}
