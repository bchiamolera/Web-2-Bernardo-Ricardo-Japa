package furb.web2.Models.ProductCategory;

import furb.web2.Models.Category.Category;
import furb.web2.Models.Product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Product_Category",
	   uniqueConstraints = {
		   @UniqueConstraint(name = "PRODUCT_CATEGORY_UK", columnNames = { "Product_Id", "Category_Id" }) })
public class ProductCategory {
	@Id
    @Column(name = "Product_Category_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_Id", nullable = false)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_Id", nullable = false)
	private Category category;
	
	public long getProductCategoryId() {
		return id;
	}

	public void setProductCategoryId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
