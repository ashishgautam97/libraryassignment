package com.library.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.web.dao.BorrowDao;
import com.library.web.model.Borrow;

@Service
public class BorrowService {
	
	@Autowired
	BorrowDao borrow;

	public Object getDetail(int id) {
		// TODO Auto-generated method stub
		borrow.getDetail(id);
		return null;
	}

	public List<Borrow> getIssuedBook() {
		// TODO Auto-generated method stub
		return borrow.getIssuedBook();
	}

	public void addBorrow(int book_id, int user_id, String return_date, String remarks) {
		// TODO Auto-generated method stub
		borrow.addBorrow(book_id,user_id,return_date,remarks);
	}

	public void returnBook(int id) {
		// TODO Auto-generated method stub
		borrow.returnBook(id);
	}

	public List<Borrow> getUsersToNotify() {
		// TODO Auto-generated method stub
		return borrow.getUsersToNotify();
	}

}
