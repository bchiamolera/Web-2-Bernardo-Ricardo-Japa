package furb.web2.Dao.User;

import org.springframework.data.repository.CrudRepository;

import furb.web2.Models.User.User;

public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
