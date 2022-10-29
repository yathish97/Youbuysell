package com.youbuysell.authentication.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.youbuysell.authentication.exception.EmailAlreadyExistException;
import com.youbuysell.authentication.model.User;
import com.youbuysell.authentication.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo usrrepo;

	@Override
	public User addUser(User usrobj) throws EmailAlreadyExistException {
		// TODO Auto-generated method stub
		 
		Optional<User> usrresult=usrrepo.findById(usrobj.getEmailid());
			
		if(usrresult.isPresent())
			throw new EmailAlreadyExistException("Email id already found");
		else
			usrrepo.save(usrobj);

		return usrobj;
	}
	@Override
	public User updateUser(String emailid, User usrobj) {
		Optional<User> users=usrrepo.findById(usrobj.getEmailid());
		if(users.isPresent())
		{
			usrrepo.save(usrobj);
			return usrobj;
		}
		else
		return null;
	}
	

	@Override
	public boolean login(User usrobj) {
		// TODO Auto-generated method stub
		User usrresult=usrrepo.findByEmailidAndPassword(usrobj.getEmailid(), usrobj.getPassword());
		
		if(usrresult==null)
		
			return false;
		
		else

		return true;
	}

	@Override
	public List<User> findByEmailid(String emailid){
		List<User> item=usrrepo.findByEmailid(emailid);
		return item;
		
	}


}
