package furb.web2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import furb.web2.Models.Product.ProductCreateDTO;
import furb.web2.Models.Product.ProductDTO;
import furb.web2.Services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
    	List<ProductDTO> products = productService.getAll();
    	return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
    	ProductDTO product = productService.getById(id);
    	return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
    	List<ProductDTO> products = productService.listProductsWithNameContaining(name);
    	return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductDTO>> getProductsByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
    	List<ProductDTO> products = productService.listProductsByPriceRange(minPrice, maxPrice);
    	return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productDto) {
    	ProductDTO product = productService.create(productDto);
    	return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable long id, @RequestBody ProductCreateDTO productDto) {
    	ProductDTO product = productService.update(id, productDto);
    	return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{productId}/add-category/{categoryId}")
    public ResponseEntity<ProductDTO> addCategoryToProduct(@PathVariable long productId, @PathVariable long categoryId) {
    	ProductDTO product = productService.addCategoryToProduct(productId, categoryId);
    	return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
