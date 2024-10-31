package furb.web2.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import furb.web2.Config.Jwt.JwtUtil;
import furb.web2.Models.Auth.AuthRequest;
import furb.web2.Models.Auth.AuthResponse;
import furb.web2.Models.User.RegisterDTO;
import furb.web2.Services.UserService;

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {

		authenticate(authRequest.getUsername(), authRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());;
		final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody RegisterDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.create(user));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
