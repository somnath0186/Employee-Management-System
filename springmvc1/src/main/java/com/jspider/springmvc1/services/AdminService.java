package com.jspider.springmvc1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.springmvc1.pojo.AdminPOJO;
import com.jspider.springmvc1.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;

	public AdminPOJO create(String username, String password) {
		AdminPOJO admin=repository.addAdmin(username,password);
		return admin;
	}

	public AdminPOJO login(String username, String password) {
	AdminPOJO admin=repository.login(username,password);
		return admin;
	}



	

}
