package com.library.web.model;

public class User {

	private int id;

	private String name;

	private Long mobile;
	
	private int issuedBook;

	public User(int id, String name, Long mobile, int issuedBook) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.issuedBook = issuedBook;
	}

	public User() {
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
		User other = (User) obj;
		if (mobile != other.mobile)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Long getMobile() {
		return mobile;
	}

	public int getId() {
		return id;
	}

	public int getIssuedBook() {
		return issuedBook;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		//result = prime * result + contact;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", issuedBook=" + issuedBook + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIssuedBook(int issuedBook) {
		this.issuedBook = issuedBook;
	}

	public void setName(String name) {
		this.name = name;
	}

}
