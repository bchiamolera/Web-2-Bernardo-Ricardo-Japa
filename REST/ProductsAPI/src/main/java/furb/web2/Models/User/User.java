package furb.web2.Models.User;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import furb.web2.Models.Order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "User",
uniqueConstraints = {
		   @UniqueConstraint(name = "USER_UK", columnNames = "Username") })
public class User {

	@Id
	@Column(name = "User_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy="user")
	private Set<Order> orders;

	public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}
