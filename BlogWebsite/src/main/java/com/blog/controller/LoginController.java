package com.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.model.Roles;
import com.blog.model.User;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRpository;
	
	@Autowired
	RoleRepository roleRepoistory;
	
	@GetMapping("/register")
	public String getRegister()
	{
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute("user") User user) {
		String password=user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
	List<Roles> role=new ArrayList<>();
	role.add(roleRepoistory.findById(2).get());
	user.setRoles(role);
	userRpository.save(user);
		System.out.println(password);
		return "redirect:/blogs";
	}
	
	@GetMapping("/login")
	public String getLoginpage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@RequestParam("email") String email , @RequestParam("password") String password) {
		System.out.println(email);
		return "redirect:/blogs";
		
	}
	
}
