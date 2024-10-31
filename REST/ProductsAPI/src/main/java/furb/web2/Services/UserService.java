package furb.web2.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import furb.web2.Dao.User.UserDao;
import furb.web2.Config.CustomUserDetails;
import furb.web2.Dao.Order.OrderDao;
import furb.web2.Dao.Role.RoleDao;
import furb.web2.Mapper.UserMapper;
import furb.web2.Models.Role.Role;
import furb.web2.Models.User.RegisterDTO;
import furb.web2.Models.User.User;
import furb.web2.Models.User.UserDTO;
import furb.web2.Util.ApiException;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService  {
	private final UserDao userDao;
	private final RoleDao roleDao;
	private final OrderDao orderDao;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, OrderDao orderDao, PasswordEncoder bcryptEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.orderDao = orderDao;
        this.bcryptEncoder = bcryptEncoder;
    }
	
	public List<UserDTO> getAll() {
		List<User> users = userDao.findAll();
		return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
	}
	
	public UserDTO getById(long id) {
		User user = userDao.findById(id).orElseThrow(() -> new ApiException("User not found with id: " + id, HttpStatus.NOT_FOUND));
		return UserMapper.toDTO(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new ApiException("User not found with username: " + username, HttpStatus.NOT_FOUND);
		}
		return CustomUserDetails.fromUserEntity(user);
	}
	
	@Transactional
	public UserDTO create(RegisterDTO user) {
		User newUser = new User();
		Role role = roleDao.findByName("ROLE_USER");
		
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.getRoles().add(role);
		newUser = userDao.save(newUser);
		return UserMapper.toDTO(newUser);
	}
	
	@Transactional
	public UserDTO update(long userId, RegisterDTO userDto) {
		User user = userDao.findById(userId).orElseThrow(() -> new ApiException("User not found with id: " + userId, HttpStatus.NOT_FOUND));
		user.setUsername(userDto.getUsername());
		user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
		user = userDao.save(user);
		return UserMapper.toDTO(user);
	}
	
	@Transactional
	public void delete(long id) {
		User user = userDao.findById(id).orElseThrow(() -> new ApiException("User not found with id: " + id, HttpStatus.NOT_FOUND));
		orderDao.deleteAll(user.getOrders());
		
		userDao.delete(user);
	}
	
	public UserDTO assignRoleToUser(String username, String roleName) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new ApiException("User not found with username: " + username, HttpStatus.NOT_FOUND);
		}
        Role role = roleDao.findByName(roleName);
        if (role == null) {
			throw new ApiException("role not found with name: " + roleName, HttpStatus.NOT_FOUND);
		}
        
        user.getRoles().add(role);
        user = userDao.save(user);
		return UserMapper.toDTO(user);
    }
}
