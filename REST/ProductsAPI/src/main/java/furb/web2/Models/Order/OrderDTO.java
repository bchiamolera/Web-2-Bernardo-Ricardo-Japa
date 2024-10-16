package furb.web2.Models.Order;

import java.util.List;

import furb.web2.Models.Item.ItemDTO;
import furb.web2.Models.User.UserDTO;

public class OrderDTO {
	private UserDTO user;
	private List<ItemDTO> items;
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public List<ItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
}
