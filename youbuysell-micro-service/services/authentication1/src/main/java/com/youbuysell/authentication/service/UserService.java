package com.youbuysell.authentication.service;



import java.util.List;

import com.youbuysell.authentication.exception.EmailAlreadyExistException;
import com.youbuysell.authentication.model.User;

public interface UserService {
	User addUser(User usrobj) throws EmailAlreadyExistException;
	boolean login(User usrobj);
	User updateUser(String emailid,User usrobj);
	
	List<User> findByEmailid(String emailid);
	
	
		
}
