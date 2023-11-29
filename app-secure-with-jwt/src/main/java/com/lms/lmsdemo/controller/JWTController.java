package com.lms.lmsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsdemo.model.JWTRequest;
import com.lms.lmsdemo.model.JWTResponse;
import com.lms.lmsdemo.service.Impl.MyUserDetailsService;
import com.lms.lmsdemo.util.JWTUtil;

@RestController
public class JWTController {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Bad Credentials : " + e);
		}

		UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT Token : " + token);

		return ResponseEntity.ok(new JWTResponse(token));

	}
}
