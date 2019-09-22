package com.library.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.library.web.model.Borrow;

@Repository
public class BorrowDao {

	public void getDetail(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<Borrow> getIssuedBook() {
		// TODO Auto-generated method stub
		List<Borrow> borrows = new ArrayList<Borrow>();
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","123456")) {
		    System.out.println("Database connected!");
			Statement st = null;
			try {
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ResultSet rs = null;
	        String sql = "SELECT br.id,br.book_id,br.user_id,br.issue_date,br.return_date,br.remarks,br.return_on,b.name,u.name FROM borrows br " + 
	        		"LEFT JOIN users u ON u.id=br.user_id " + 
	        		"LEFT JOIN books b on b.id=br.book_id " + 
	        		"WHERE return_on is null " + 
	        		"order by issue_date DESC";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rs.next()) { 
					Borrow borrow = new Borrow();
					borrow.setId(rs.getInt(1));
					borrow.setBook_id(rs.getInt(2));
					borrow.setUser_id(rs.getInt(3));
					borrow.setIssue_date(rs.getString(4));
					borrow.setReturn_date(rs.getString(5));
					borrow.setRemarks(rs.getString(6));
					borrow.setReturn_on(rs.getString(7));
					borrow.setBook_name(rs.getString(8));
					borrow.setUser_name(rs.getString(9));
					  System.out.println(borrow.toString());
					  borrows.add(borrow);
				   }
			    conn.close(); 
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return borrows;
	}

	public void addBorrow(int book_id, int user_id, String return_date, String remarks) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","123456")) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    PreparedStatement stmt=conn.prepareStatement("insert into borrows(book_id,user_id,issue_date,return_date,remarks) values(?,?,now(),?,?)");  
		    stmt.setInt(1,book_id);//1 specifies the first parameter in the query  
		    stmt.setInt(2,user_id);
		    stmt.setString(3,return_date);
		    stmt.setString(4,remarks);
		      
		    stmt.executeUpdate();  
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void returnBook(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","123456")) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    String sql = "update borrows set return_on=now() where id=?";
		    PreparedStatement stmt=conn.prepareStatement(sql);  
		    stmt.setInt(1,id);//1 specifies the first parameter in the query  
		    stmt.executeUpdate();
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

}
