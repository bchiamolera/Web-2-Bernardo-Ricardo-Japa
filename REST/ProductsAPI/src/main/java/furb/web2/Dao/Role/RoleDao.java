package furb.web2.Dao.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import furb.web2.Models.Role.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
