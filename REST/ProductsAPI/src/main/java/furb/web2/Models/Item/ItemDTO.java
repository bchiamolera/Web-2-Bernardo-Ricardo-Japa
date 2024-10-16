package furb.web2.Models.Item;

import furb.web2.Models.Product.ProductDTO;

public class ItemDTO {
	private ProductDTO product;
	private int quantity;
	private float totalPrice;
	
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
}