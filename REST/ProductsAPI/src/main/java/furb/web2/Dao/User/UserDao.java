package furb.web2.Dao.User;

import org.springframework.data.jpa.repository.JpaRepository;

import furb.web2.Models.User.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
