package com.youbuysell.authentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youbuysell.authentication.exception.EmailAlreadyExistException;
import com.youbuysell.authentication.model.User;
import com.youbuysell.authentication.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin

@RequestMapping("/authentication")
public class UserController {
	

	@Autowired
	UserService usrservice;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> adduser(@RequestBody User usrnew ){
		
			try {
				User usrobj=usrservice.addUser(usrnew);
				return new ResponseEntity<User>(usrobj,HttpStatus.CREATED);
			} catch (EmailAlreadyExistException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
			}
	}
	
	@GetMapping("/viewuser/{emailid}")
	public ResponseEntity<?> viewproducts(@PathVariable("emailid") String product)
	{
		
		List<User> users= usrservice.findByEmailid(product);
	
		return new ResponseEntity<List>(users,HttpStatus.OK);
		
		
	}
	@PostMapping("/login")
	public ResponseEntity<?> validateuser(@RequestBody User usr)
	{
		boolean result=	 usrservice.login(usr);
		if(result)
		{
			String mytoken=generateToken(usr);
			Map mymap=new HashMap();
			
			mymap.put("token",mytoken);
			
			return new ResponseEntity<Map>(mymap,HttpStatus.OK);

		}
		else
			return new ResponseEntity<String>("Invalid credentials",HttpStatus.UNAUTHORIZED);

	}

	 @PutMapping("/updateuser/{emailid}")
		public ResponseEntity<?> updateuser(@RequestBody User usrupdate,String emailid)
		{
		 
		 User custresult=usrservice.updateUser(emailid,usrupdate);
		
			if(custresult==null)
				return new ResponseEntity<String>("Update failed",HttpStatus.NOT_FOUND);
			else
				
				return new ResponseEntity<String>("updated ",HttpStatus.OK);
			
		}
	
	
	
		
	public String generateToken(User usr)
	{
		long expiry=10_000_00;
		
		return Jwts.builder().setSubject(usr.getEmailid()).setIssuedAt((new Date(System.currentTimeMillis())))
				.setExpiration(new Date(System.currentTimeMillis()+expiry))
				.signWith(SignatureAlgorithm.HS256 ,"ustw15")
				.compact();

	}

}
