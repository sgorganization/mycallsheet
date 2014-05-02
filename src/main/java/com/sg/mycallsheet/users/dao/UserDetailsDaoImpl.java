package com.sg.mycallsheet.users.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.LockedException;

import com.sg.mycallsheet.users.model.UserAttempts;

public class UserDetailsDaoImpl extends JdbcDaoSupport implements UserDetailsDao{
	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USERS SET accountNonLocked = ? WHERE username = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USERS WHERE username = ?";

	private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM USER_ATTEMPTS WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO USER_ATTEMPTS (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";

	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = attempts + 1, lastmodified = ? WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = 0, lastmodified = null WHERE username = ?";
	
	private static final int MAX_ATTEMPTS = 3;
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	@Override
	public void updateFailAttempts(String userName) {
		UserAttempts user = getUserAttempts(userName);
		if(user == null){
			if(isUserExists(userName)){
				//If no record insert a new record
				getJdbcTemplate().update(SQL_USER_ATTEMPTS_INSERT,new Object[] {userName,1,new Date()});
			}
		} else {
			if(isUserExists(userName)){
				//Update attempts count+1
				getJdbcTemplate().update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, new Object[]{new Date(),userName});
			}
			
			if(user.getAttempts() +1 >= MAX_ATTEMPTS){
				//locked user
				getJdbcTemplate().update(SQL_USERS_UPDATE_LOCKED, new Object[]{false,userName});
				
				//throw exception
				throw new LockedException("User Account is Locked !");
			}
		}
		
	}

	@Override
	public void resetFailAttempts(String userName) {
		getJdbcTemplate().update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS,new Object[]{userName});
	}

	@Override
	public UserAttempts getUserAttempts(String userName) {
		try{
			UserAttempts userAttempts = getJdbcTemplate().queryForObject(
					SQL_USER_ATTEMPTS_GET, 
					new Object[]{userName},
					new RowMapper<UserAttempts> (){

						@Override
						public UserAttempts mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserAttempts user = new UserAttempts();
							user.setId(rs.getInt("id"));
							user.setUserName(rs.getString("username"));
							user.setAttempts(rs.getInt("attempts"));
							user.setLastModified(rs.getDate("lastModified"));
							
							return user;
						}
						
					});
		}catch(Exception e){
			
		}
		return null;
	}
	
	public boolean isUserExists(String userName){
		boolean result = false;
		int count = getJdbcTemplate().queryForObject(SQL_USERS_COUNT,
				new Object[] {userName}, Integer.class );
		
		if(count > 0 ){
			result = true;
		}		
		return result;
	}

}
