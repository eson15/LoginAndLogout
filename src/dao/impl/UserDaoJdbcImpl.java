package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.JdbcUtils;
import dao.UserDao;
import domain.User;
import exception.DaoException;

public class UserDaoJdbcImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	//向数据库中添加user
	@Override
	public void addUser(User user) {
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into users(id,username,password,email,birthday) values(?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setString(1, user.getId());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.setString(4, user.getEmail());
			st.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, rs, st);
		}
		

	}

	@Override
	public User findUser(String username) {
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where username=?";
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			rs = st.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, rs, st);
		}
		
	}

	@Override
	public User findUser(String username, String password) {
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from users where username=? and password=?";
			st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			JdbcUtils.release(conn, rs, st);
		}
	}

}
