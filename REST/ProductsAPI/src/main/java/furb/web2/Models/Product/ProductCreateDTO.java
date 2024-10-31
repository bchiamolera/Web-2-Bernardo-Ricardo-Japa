package furb.web2.Models.Product;

import java.util.List;


public class ProductCreateDTO {
	private String name;
	private float price;
	private List<Long> categoriesId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<Long> getCategoriesId() {
		return categoriesId;
	}
	public void setCategoriesId(List<Long> categoriesId) {
		this.categoriesId = categoriesId;
	}
	
}
