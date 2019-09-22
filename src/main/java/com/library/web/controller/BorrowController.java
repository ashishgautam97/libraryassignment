package com.library.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.web.model.Book;
import com.library.web.model.Borrow;
import com.library.web.service.BookService;
import com.library.web.service.BorrowService;
import com.library.web.service.UserService;

@Controller
public class BorrowController {

	@Autowired
	BorrowService service;
	@Autowired
	BookService bookservice;
	@Autowired
	UserService userservice;
	
	@RequestMapping(value = "/issue-book", method = RequestMethod.GET)
	public String issueBook(@RequestParam int id,ModelMap model) {
		System.out.println("issue book get");
		if(id < 1)
			return "borrow-list";
		Book book = bookservice.retrieveBook(id);
		
//		if(book.getQuantity()< book.getAvailable())
//			return "redirect:/borrow-list";

		// model.put("issue_book", book);
		model.put("borrow",new Borrow(book.getId(),book.getName()));
		model.addAttribute("users", userservice.getEligibleToIssueUser());
		return "issue-book";
	}
	
	@RequestMapping(value = "/issue-book", method = RequestMethod.POST)
	public String updateBook(ModelMap model, @Valid Borrow borrow, BindingResult result) {
		System.out.println("issue book post");
		if (result.hasErrors()) {
			return "issue-book";
		}

		service.addBorrow(borrow.getBook_id(), borrow.getUser_id(), borrow.getReturn_date(),borrow.getRemarks());
		return "redirect:/borrow-list";
	}
	
	@RequestMapping(value = "/borrow-list", method = RequestMethod.GET)
	public String showIssuedBook(ModelMap model) {
		System.out.println("show issued book get");
		model.put("borrows",service.getIssuedBook());
		return "borrow-list";
	}
	
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int id) {

		System.out.println("delete book");
		service.returnBook(id);
		return "redirect:/borrow-list";
	}
}
