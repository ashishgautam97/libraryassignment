package com.library.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.web.model.User;
import com.library.web.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService service;
	
	@RequestMapping(value = "/list-users", method = RequestMethod.GET)
	public String showUser(ModelMap model) {
		
		System.out.println("ShowUser");
		model.put("users", service.findAll());
		model.addAttribute("search", "");
		return "list-users";
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.GET)
	public String showAddBookPage(ModelMap model) {
		System.out.println("Add user get");
		model.put("user", new User(0, "", 0L, 0));
		return "user";
	}
	
	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public String addBook(ModelMap model, @Valid User user, BindingResult result) {
		System.out.println("Add user post");
		if (result.hasErrors()) {
			return "user";
		}

		service.addUser(user.getName(), user.getMobile());
		return "redirect:/list-users";
	}
	
	@RequestMapping(value = "/update-user", method = RequestMethod.GET)
	public String showUpdateUserPage(@RequestParam int id,ModelMap model) {
		System.out.println("udpate user get");
		model.put("user", service.retrieveUser(id));
		return "user";
	}
	
	@RequestMapping(value = "/update-user", method = RequestMethod.POST)
	public String updateUser(ModelMap model, @Valid User user, BindingResult result) {
		System.out.println("update user post");
		if (result.hasErrors()) {
			return "user";
		}

		service.updateUser(user.getId(), user.getName(), user.getMobile());
		return "redirect:/list-users";
	}
	
	@RequestMapping(value = "/delete-user", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int id) {

		System.out.println("delete user");
		service.deleteUser(id);
		return "redirect:/list-users";
	}
}
