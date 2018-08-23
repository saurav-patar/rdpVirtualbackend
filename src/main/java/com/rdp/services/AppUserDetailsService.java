package com.rdp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rdp.entities.User;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author saurav patar
 *
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.find(username);
		return  user;
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		return userService.findById(id);
	}

}
