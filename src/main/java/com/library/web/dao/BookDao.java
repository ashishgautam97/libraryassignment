package com.library.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.library.web.model.Book;

@Repository
public class BookDao {
	
	@Value("${spring.datasource.url}")
	private String hostUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;

	public List<Book> findAll() {
        List<Book> books = new ArrayList<Book>();
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ResultSet rs = null;
	        String sql = "Select b.id,b.name,b.isbn,b.quantity,(b.quantity-COUNT(br.id)) as available from books b left join borrows br ON br.book_id=b.id and br.return_on is NULL group by b.id";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rs.next()) { 
					  Book book = new Book();
					  book.setId(rs.getInt(1));
					  book.setName(rs.getString(2));
					  book.setIsbn(rs.getString(3));
					  book.setQuantity(rs.getInt(4));
					  book.setAvailable(Integer.parseInt(rs.getString(5)));
					  System.out.println(book.toString());
					  books.add(book);
				   }
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return books;
	}
	
	public List<Book> findAllBySearch(String str) {
		List<Book> books = new ArrayList<Book>();
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ResultSet rs = null;
	        String sql = "Select b.id,b.name,b.isbn,b.quantity,(b.quantity-COUNT(br.id)) as available from books b left join borrows br ON br.book_id=b.id and br.return_on is NULL group by b.id";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rs.next()) { 
					  Book book = new Book();
					  book.setId(rs.getInt(1));
					  book.setName(rs.getString(2));
					  book.setIsbn(rs.getString(3));
					  book.setQuantity(rs.getInt(4));
					  book.setAvailable(Integer.parseInt(rs.getString(5)));
					  System.out.println(book.toString());
					  books.add(book);
				   }
			    conn.close(); 
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return books;
	}

	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    Statement stmt = conn.createStatement();
		    stmt.execute("DELETE FROM books WHERE id = "+id);
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void addBook(String name, String isbn, int quantity) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    PreparedStatement stmt=conn.prepareStatement("insert into books(name,isbn,quantity) values(?,?,?)");  
		    stmt.setString(1,name);//1 specifies the first parameter in the query  
		    stmt.setString(2,isbn);
		    stmt.setInt(3,quantity);
		      
		    stmt.executeUpdate();  
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		Book book = new Book();
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ResultSet rs = null;
	        String sql = "Select b.id,b.name,b.isbn,b.quantity, (b.quantity-COUNT(br.id)) as available from books b "
	        		+ "	LEFT JOIN borrows br ON br.book_id=b.id and br.return_on is NULL "
	        		+ " where b.id="+id+" "
    				+ " group by b.id LIMIT 1";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				  
				while (rs.next()) { 
					  book.setId(rs.getInt(1));
					  book.setName(rs.getString(2));
					  book.setIsbn(rs.getString(3));
					  book.setQuantity(rs.getInt(4));
					  book.setAvailable(rs.getInt(5));
					  System.out.println(book.toString());
				   }
			    conn.close(); 
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return book;
	}

	public void updateBook(int id, String name, String isbn, int quantity) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    String sql = "update books set name=?,isbn=?,quantity=? where id=?";
		    PreparedStatement stmt=conn.prepareStatement(sql);  
		    stmt.setString(1,name);//1 specifies the first parameter in the query  
		    stmt.setString(2,isbn);
		    stmt.setInt(3,quantity);
		    stmt.setInt(4,id);
		      
		    stmt.executeUpdate();
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
