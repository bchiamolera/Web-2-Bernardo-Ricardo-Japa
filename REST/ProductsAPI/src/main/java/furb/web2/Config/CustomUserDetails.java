package furb.web2.Config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import furb.web2.Models.Role.Role;
import furb.web2.Models.User.User;

public class CustomUserDetails implements UserDetails {
	private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
	
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities.stream().collect(Collectors.toList());
    }
	
	public static CustomUserDetails fromUserEntity(User user) {
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList())
        );
    }
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize as needed
    }

}
