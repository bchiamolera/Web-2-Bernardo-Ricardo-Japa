package furb.web2.Models.Order;

import java.util.List;

import furb.web2.Models.Item.ItemDTO;
import furb.web2.Models.User.UserDTO;

public class OrderCreateDTO {
	private long id;
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
