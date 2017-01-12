package com.blog.mvc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */
@Service("userDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;



	/**
	 * Returns a populated {@link UserDetails} object.
	 * The username is first retrieved from the database and then mapped to
	 * a {@link UserDetails} object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			com.blog.mvc.model.User user = userService.findByUsername(username);

			if(user==null) {throw new UsernameNotFoundException("No such user: " + username);}



			return new User(
					user.getUsername(),
					user.getPassword(),
					true,
					true,
					true,
					true,
					getAuthorities("ROLE_ADMIN"));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param role
	 * @return
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	/**
	 * @param role
	 * @return
	 */
	public static Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(role);
		return authList;
	}
}
