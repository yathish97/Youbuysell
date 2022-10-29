package com.youbuysell.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.youbuysell.authentication.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
	List<User> findByEmailid(String emailid);
	User findByEmailidAndPassword(String email,String pass);
	
	

}
