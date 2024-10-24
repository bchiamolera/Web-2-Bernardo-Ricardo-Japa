package furb.web2.Models.Product;

import java.util.List;


public class ProductCreateDTO {
	private long id;
	private String name;
	private float price;
	private List<Long> categoriasId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public List<Long> getCategoriasId() {
		return categoriasId;
	}
	public void setCategoriasId(List<Long> categoriasId) {
		this.categoriasId = categoriasId;
	}
	
}
