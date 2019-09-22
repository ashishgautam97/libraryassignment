package com.library.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.web.dao.UserDao;
import com.library.web.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao users;
	
	public List<User> findAll() {
		
		List<User> allUsers = users.findAll();
		return allUsers;
	}

	public void addUser(String name, Long mobile) {
		// TODO Auto-generated method stub
		users.addUser(name,mobile);
	}

	public User retrieveUser(int id) {
		// TODO Auto-generated method stub
		return users.getUserById(id);
	}

	public void updateUser(int id, String name, Long mobile) {
		// TODO Auto-generated method stub
		users.updateUser(id,name,mobile);
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		users.deleteUser(id);
	}

	public List<User> getEligibleToIssueUser() {
		// TODO Auto-generated method stub
		return users.getEligibleToIssueUser();
	}

}
