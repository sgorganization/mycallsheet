package com.sg.mycallsheet.users.dao;

import com.sg.mycallsheet.users.model.UserAttempts;

public interface UserDetailsDao {
	void updateFailAttempts(String userName);
	void resetFailAttempts(String userName);
	UserAttempts getUserAttempts(String userName);
}
