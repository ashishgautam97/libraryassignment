package com.library.web.model;

import java.util.List;

public class Borrow {

	private int id;
	private int book_id;
	private int user_id;
	private String issue_date;
	private String return_date;
	private String remarks;
	public Borrow(int book_id, String book_name) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
	}

	private String return_on;
	private String book_name;
	private String user_name;
	private Book book;
	private List<User> users;
	private Long mobile;

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Borrow(int id, int book_id, int user_id, String issue_date, String return_date, String remarks,
			String return_on) {
		super();
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.remarks = remarks;
		this.return_on = return_on;
	}

	public Borrow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Borrow other = (Borrow) obj;
		if (book_id != other.book_id)
			return false;
		if (id != other.id)
			return false;
		if (issue_date == null) {
			if (other.issue_date != null)
				return false;
		} else if (!issue_date.equals(other.issue_date))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (return_date == null) {
			if (other.return_date != null)
				return false;
		} else if (!return_date.equals(other.return_date))
			return false;
		if (return_on == null) {
			if (other.return_on != null)
				return false;
		} else if (!return_on.equals(other.return_on))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	public int getBook_id() {
		return book_id;
	}

	public int getId() {
		return id;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getReturn_date() {
		return return_date;
	}

	public String getReturn_on() {
		return return_on;
	}

	public int getUser_id() {
		return user_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + book_id;
		result = prime * result + id;
		result = prime * result + ((issue_date == null) ? 0 : issue_date.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((return_date == null) ? 0 : return_date.hashCode());
		result = prime * result + ((return_on == null) ? 0 : return_on.hashCode());
		result = prime * result + user_id;
		return result;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public void setReturn_on(String return_on) {
		this.return_on = return_on;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Borrow [id=" + id + ", book_id=" + book_id + ", user_id=" + user_id + ", issue_date=" + issue_date
				+ ", return_date=" + return_date + ", remarks=" + remarks + ", return_on=" + return_on + "]";
	}

	public Borrow(int id, int book_id, int user_id, String issue_date, String return_date, String remarks,
			String return_on, String book_name, String user_name) {
		super();
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.remarks = remarks;
		this.return_on = return_on;
		this.book_name = book_name;
		this.user_name = user_name;
	}
	
	public Borrow(Book book, List<User> users) {
		this.book = book;
		this.users = users;
	}

}
