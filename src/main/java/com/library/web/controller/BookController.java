package com.library.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.web.model.Book;
import com.library.web.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService service;
	
	@RequestMapping(value = "/list-books", method = RequestMethod.GET)
	public String showBooks(ModelMap model)
	{
		// String name = getLoggedInUserName(model);
		System.out.println("ShowBooks");
		model.put("books", service.retrieveBooks());
		model.addAttribute("search", "");
		return "list-books";
	}
	
	@RequestMapping(value = "/search-book", method = RequestMethod.POST)
	public String searchBooks(@RequestBody String search,ModelMap model)
	{
		// String name = getLoggedInUserName(model);
		System.out.println("Search Book");
		model.addAttribute("books", service.serachBooks(search));
		model.addAttribute("search", search);
		return "list-books";
	}
	
	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public String showAddBookPage(ModelMap model) {
		System.out.println("Add book get");
		model.put("book", new Book(0, "", "", 0, 0));
		return "book";
	}
	
	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String addBook(ModelMap model, @Valid Book book, BindingResult result) {
		System.out.println("Add book post");
		if (result.hasErrors()) {
			return "book";
		}

		service.addBook(book.getName(), book.getIsbn(), book.getQuantity());
		return "redirect:/list-books";
	}
	
	@RequestMapping(value = "/update-book", method = RequestMethod.GET)
	public String showUpdateBookPage(@RequestParam int id,ModelMap model) {
		System.out.println("udpate book get");
		model.put("book", service.retrieveBook(id));
		return "book";
	}
	
	@RequestMapping(value = "/update-book", method = RequestMethod.POST)
	public String updateBook(ModelMap model, @Valid Book book, BindingResult result) {
		System.out.println("update book post");
		if (result.hasErrors()) {
			return "book";
		}

		service.updateBook(book.getId(), book.getName(), book.getIsbn(), book.getQuantity());
		return "redirect:/list-books";
	}
	
	@RequestMapping(value = "/delete-book", method = RequestMethod.GET)
	public String deleteBook(@RequestParam int id) {

		System.out.println("delete book");
		service.deleteBook(id);
		return "redirect:/list-books";
	}
	
}
