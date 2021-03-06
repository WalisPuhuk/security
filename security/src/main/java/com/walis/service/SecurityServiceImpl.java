package com.walis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.walis.util.LogUtil;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(userDetails instanceof UserDetails) {
			return ((UserDetails)userDetails).getUsername();
		}
		
		return null;
	}

	@Override
	public void autologin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		
		authenticationManager.authenticate(upaToken);
		
		if(upaToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(upaToken);
			LogUtil.logger.debug("Auto login {} successfully!", username);
		}
	}

}
