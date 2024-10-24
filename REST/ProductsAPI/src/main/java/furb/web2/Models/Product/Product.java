package furb.web2.Models.Product;

import java.util.Set;

import furb.web2.Models.ProductCategory.ProductCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Product")
public class Product {
	@Id
    @Column(name = "Category_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String productName;
	
	@Column(nullable=false)
	private double price;	
	
	@OneToMany(mappedBy = "product")
	private Set<ProductCategory> productCategories;

	public long getProductId() {
		return id;
	}

	public void setProductId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Set<ProductCategory> getProductCategories() {
		return this.productCategories;
	}
	
	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
	
}
