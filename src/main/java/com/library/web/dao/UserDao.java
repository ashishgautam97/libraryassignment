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

import com.library.web.model.User;

@Repository
public class UserDao {


	@Value("${spring.datasource.url}")
	private String hostUrl;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	public List<User> findAll() {
		List<User> Users = new ArrayList<User>();
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
	        String sql = "select u.id,u.name,u.mobile,count(b.id) as issued FROM users u left join borrows b ON b.user_id=u.id and b.return_on is NULL GROUP BY u.id";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rs.next()) { 
					  User user = new User();
					  user.setId(rs.getInt(1));
					  user.setName(rs.getString(2));
					  user.setMobile(rs.getLong(3));
					  user.setIssuedBook(rs.getInt(4));
					  System.out.println(user.toString());
					  Users.add(user);
				   }
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return Users;
	}

	public void addUser(String name, Long mobile) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    PreparedStatement stmt=conn.prepareStatement("insert into users(name,mobile) values(?,?)");  
		    stmt.setString(1,name);//1 specifies the first parameter in the query  
		    stmt.setLong(2,mobile);
		      
		    stmt.executeUpdate();  
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User user = new User();
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
	        String sql = "Select b.id,b.name,b.mobile from users b where b.id="+id+" LIMIT 1";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				  
				while (rs.next()) { 
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setMobile(rs.getLong(3));
					user.setIssuedBook(0);
					  System.out.println(user.toString());
				   }
			    conn.close(); 
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return user;
	}

	public void updateUser(int id, String name, Long mobile) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    // Statement stmt = conn.createStatement();
		    String sql = "update users set name=?,mobile=? where id=?";
		    PreparedStatement stmt=conn.prepareStatement(sql);  
		    stmt.setString(1,name);//1 specifies the first parameter in the query  
		    stmt.setLong(2,mobile);
		    stmt.setInt(3,id);
		      
		    stmt.executeUpdate();
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(this.hostUrl,this.username,this.password)) {
		    System.out.println("Database connected!");
		    Statement stmt = conn.createStatement();
		    stmt.execute("DELETE FROM users WHERE id = "+id);
		    conn.close(); 

		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

	public List<User> getEligibleToIssueUser() {
		// TODO Auto-generated method stub
		List<User> Users = new ArrayList<User>();
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
	        String sql = "SELECT u.id, u.name, u.mobile, COUNT(b1.id) as total FROM users u " + 
			        		" LEFT JOIN borrows b on b.user_id=U.id AND b.return_on is Null AND b.issue_date >= DATE(NOW()) - INTERVAL 7 DAY " + 
			        		" LEFT JOIN borrows b1 on b1.user_id=U.id AND b1.return_on is Null " + 
			        		" GROUP by u.id HAVING COUNT(DISTINCT b.id) < 2";
	        try {
				rs = st.executeQuery( sql );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rs.next()) { 
					  User user = new User();
					  user.setId(rs.getInt(1));
					  user.setName(rs.getString(2));
					  user.setMobile(rs.getLong(3));
					  user.setIssuedBook(rs.getInt(4));
					  System.out.println(user.toString());
					  Users.add(user);
				   }
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
		return Users;
	}
}
