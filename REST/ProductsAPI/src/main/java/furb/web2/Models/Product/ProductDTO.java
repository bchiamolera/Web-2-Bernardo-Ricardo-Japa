package furb.web2.Models.Product;

import java.util.List;

import furb.web2.Models.Category.CategoryDTO;

public class ProductDTO {
	private long id;
	private String name;
	private float price;
	private List<CategoryDTO> categorias;
	
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
	
	public List<CategoryDTO> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<CategoryDTO> categorias) {
		this.categorias = categorias;
	}
}
