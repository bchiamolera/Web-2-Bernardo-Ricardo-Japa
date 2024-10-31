package furb.web2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import furb.web2.Models.Item.ItemCreateDTO;
import furb.web2.Models.Item.ItemDTO;
import furb.web2.Services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
    	List<ItemDTO> items = itemService.getAll();
    	return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable long id) {
    	ItemDTO item = itemService.getById(id);
    	return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<ItemDTO> createItem(@PathVariable long orderId, @RequestBody ItemCreateDTO itemDto) {
    	ItemDTO item = itemService.create(orderId, itemDto);
    	return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable long orderId, @RequestBody ItemCreateDTO itemDto) {
    	ItemDTO item = itemService.update(orderId, itemDto);
    	return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
