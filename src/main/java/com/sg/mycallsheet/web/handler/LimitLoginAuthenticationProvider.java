package com.sg.mycallsheet.web.handler;

import java.util.Date;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import com.sg.mycallsheet.users.dao.UserDetailsDao;
import com.sg.mycallsheet.users.model.UserAttempts;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider{
	UserDetailsDao userDetailsDao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		try{
			Authentication auth = super.authenticate(authentication);
			userDetailsDao.resetFailAttempts(authentication.getName());
			return auth;
		}catch(BadCredentialsException e){
			userDetailsDao.updateFailAttempts(authentication.getName());
			throw e;
		}catch(LockedException e){
			String error=null;
			UserAttempts userAttempts = userDetailsDao.getUserAttempts(authentication.getName());
			if(userAttempts != null){
				Date lastAttempts = userAttempts.getLastModified();
				error = "User Account is Locked !! <br><br> Username : "+authentication.getName()
						+"Last Attempts : "+lastAttempts;
			}else{
				error = e.getMessage();
			}
			throw new LockedException(error);
		}		
	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
	
	
}
