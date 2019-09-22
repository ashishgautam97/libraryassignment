package com.library.web.service;

//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.web.dao.BookDao;
import com.library.web.model.Book;

@Service
public class BookService {
    @Autowired
    private BookDao books;

    public List<Book> retrieveBooks() {
        //List<Book> filteredTodos = new ArrayList<Book>();
        List<Book> allbooks = books.findAll();
        return allbooks;
    }
    
    public List<Book> serachBooks(String str) {
    	List<Book> allbooks = books.findAllBySearch(str);
        return allbooks;
        //List<Book> filteredTodos = new ArrayList<Book>();
//        List<Book> allbooks = books.getBookSearch(str);
//        for (Book todo : allbooks) {
//            if (todo.getUser().equalsIgnoreCase(user)) {
//                filteredTodos.add(todo);
//            }
//        }
//        return filteredTodos;
//        return allbooks;
    }

	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		books.deleteBook(id);
	}

	public void addBook(String name, String isbn, int quantity) {
		// TODO Auto-generated method stub
		books.addBook(name, isbn, quantity);
	}

	public Book retrieveBook(int id) {
		// TODO Auto-generated method stub
		return books.getBookById(id);
	}
	
	public void updateBook(int id,String name, String isbn, int quantity) {
		// TODO Auto-generated method stub
		books.updateBook(id, name, isbn, quantity);
	}
    
//    public Book retrieveTodo(int id) {
//        for (Book todo : todos) {
//            if (todo.getId()==id) {
//                return todo;
//            }
//        }
//        return null;
//    }
//
//    public void updateTodo(Book todo){
//    		todos.remove(todo);
//    		todos.add(todo);
//    }
//
//    public void addTodo(String name, String desc, Date targetDate,
//            boolean isDone) {
//        //todos.add(new Book(++todoCount, name, desc, targetDate, isDone));
//    }
//
//    public void deleteTodo(int id) {
//        Iterator<Book> iterator = todos.iterator();
//        while (iterator.hasNext()) {
//            Book todo = iterator.next();
//            if (todo.getId() == id) {
//                iterator.remove();
//            }
//        }
//    }
}