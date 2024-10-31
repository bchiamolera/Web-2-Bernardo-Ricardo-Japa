package furb.web2.Services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import furb.web2.Dao.Product.ProductDao;
import furb.web2.Dao.Category.CategoryDao;
import furb.web2.Models.Category.Category;
import furb.web2.Models.Product.Product;
import furb.web2.Models.Product.ProductCreateDTO;
import furb.web2.Models.Product.ProductDTO;
import furb.web2.Util.ApiException;
import furb.web2.Mapper.ProductMapper;

@Service
public class ProductService {
    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    @Autowired
    public ProductService(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    public List<ProductDTO> getAll() {
    	List<Product> products = productDao.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getById(long id) {
    	Product product = productDao.findById(id).orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        return ProductMapper.toDTO(product);
    }

    public List<ProductDTO> listProductsWithNameContaining(String name) {
        List<Product> products = productDao.findByProductNameContaining(name);
        if (products.isEmpty() || products == null) {
        	throw new ApiException("No products found with name: " + name, HttpStatus.NOT_FOUND);
        }
            
        return products.stream()
        		.map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> listProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < minPrice) {
            throw new ApiException("Invalid price range: " + minPrice + " - " + maxPrice, HttpStatus.BAD_REQUEST);
        }
        List<Product> products = productDao.findByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            throw new ApiException("No products found within the price range: " + minPrice + " - " + maxPrice, HttpStatus.NOT_FOUND);
        }
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO create(ProductCreateDTO productDto) {
    	if (productDto.getName() == null) {
            throw new ApiException("Name cannot be null", HttpStatus.BAD_REQUEST);
        }
    	if (productDto.getPrice() < 0) {
            throw new ApiException("Invalid price", HttpStatus.BAD_REQUEST);
        }
    	
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        setCategoriesForProduct(product, productDto.getCategoriesId());
        
        return ProductMapper.toDTO(productDao.save(product));
    }

    @Transactional
    public ProductDTO update(long productId, ProductCreateDTO productDto) {
    	Product product = productDao.findById(productId).orElseThrow(() -> new ApiException("Product not found with id: " + productId, HttpStatus.NOT_FOUND));
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getPrice() >= 0) {
            product.setPrice(productDto.getPrice());
        }
        setCategoriesForProduct(product, productDto.getCategoriesId());
        return ProductMapper.toDTO(productDao.save(product));
    }
    
    @Transactional
    public ProductDTO addCategoryToProduct(long productId, long categoryId) {
    	Product product = productDao.findById(productId).orElseThrow(() -> new ApiException("Product not found with id: " + productId, HttpStatus.NOT_FOUND));
        Category category = categoryDao.findById(categoryId).orElseThrow(() -> new ApiException("Category not found with id: " + categoryId, HttpStatus.NOT_FOUND));
        product.getCategories().add(category);
        return ProductMapper.toDTO(productDao.save(product));
    }
    
    private void setCategoriesForProduct(Product product, List<Long> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            Set<Category> categories = categoryIds.stream()
                    .map(categoryId -> categoryDao.findById(categoryId)
                            .orElseThrow(() -> new ApiException("Category not found with id: " + categoryId, HttpStatus.NOT_FOUND)))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }
    }

    @Transactional
    public void delete(long productId) {
    	Product product = ProductMapper.toEntity(getById(productId));
        productDao.delete(product);
    }
}
