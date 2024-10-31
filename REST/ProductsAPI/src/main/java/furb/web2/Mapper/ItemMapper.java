package furb.web2.Mapper;

import furb.web2.Models.Item.Item;
import furb.web2.Models.Item.ItemDTO;

public class ItemMapper {
    public static ItemDTO toDTO(Item item) {
        if (item == null) {
            return null;
        }

        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setProduct(ProductMapper.toDTO(item.getProduct()));
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public static Item toEntity(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        Item item = new Item();
        item.setId(dto.getId());
        item.setProduct(ProductMapper.toEntity(dto.getProduct()));
        item.setQuantity(dto.getQuantity());
        return item;
    }
}
