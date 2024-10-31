package furb.web2.Mapper;

import java.util.stream.Collectors;

import furb.web2.Models.Product.Product;
import furb.web2.Models.Product.ProductDTO;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategorias(product.getCategories() != null ?
            product.getCategories().stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList()) : null);
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }
}
