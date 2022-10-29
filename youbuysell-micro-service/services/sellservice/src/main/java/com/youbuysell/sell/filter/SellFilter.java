package com.youbuysell.sell.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class SellFilter extends GenericFilterBean  {
	
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
    	
    	 
		HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse httpresponse=(HttpServletResponse) response;
		
		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
		httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
		httpresponse.setHeader("Access-Control-Allow-Headers", "*");
		httpresponse.setHeader("Access-Control-Allow-Credential", "true");
		if(httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
	chain.doFilter(httprequest, httpresponse);
		}

		else {
		String auth= httprequest.getHeader("Authorization");
		System.out.println(auth);
		
		if((auth==null) || (!auth.startsWith("Bearer")))
		{
			throw new ServletException("JWT Token is missing");
		}
		
		String tokenvalue= auth.substring(7);
		
		try
		{
			JwtParser jparseobj=Jwts.parser().setSigningKey("ustw15");
			
			Jwt jwtobj=jparseobj.parse(tokenvalue);
			
			Claims claim=(Claims)jwtobj.getBody();
			System.out.println(claim.getSubject());
		}
		catch(SignatureException e)
		{
			throw new ServletException("Mismatch in JWT token");
		}
		
		catch(MalformedJwtException e)
		{
			throw new ServletException("token is invalid");
		}
	
		chain.doFilter(httprequest, httpresponse);
	}

}		



}

