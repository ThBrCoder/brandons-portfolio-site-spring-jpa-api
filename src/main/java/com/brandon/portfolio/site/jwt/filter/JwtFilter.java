package com.brandon.portfolio.site.jwt.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.brandon.portfolio.site.cnst.Constants;
import com.brandon.portfolio.site.service.UserServiceImpl;
import com.brandon.portfolio.site.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	public JwtFilter(JwtUtil jwtUtil, UserServiceImpl userServiceImpl) {
		this.jwtUtil = jwtUtil;
		this.userServiceImpl = userServiceImpl;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Optional<String> authHeader = Optional.ofNullable(request.getHeader("Authorization"));

		Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());

		authHeader.ifPresent(header -> { 
			Optional<String> username = Optional.empty();
			Optional<String> token = Optional.empty();
			if(header.startsWith("Bearer ")) {

				token = Optional.ofNullable(header.substring(7)).filter(
					val -> val != null && !val.toString().equals(Constants.LOWERCASE_NULL_STRING));

				if(token.isPresent()) {
					username = Optional.ofNullable(jwtUtil.extractUsername(token.get())); 
				}
			}

			if(username.isPresent() && token.isPresent() && !auth.isPresent()) {
				UserDetails userCredentials = userServiceImpl.loadUserByUsername(username.get());
				if(jwtUtil.validateToken(token.get(), userCredentials)) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                        new UsernamePasswordAuthenticationToken(userCredentials, null, userCredentials.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);				
				}
			}			
		});

		filterChain.doFilter(request, response);
	}


}
