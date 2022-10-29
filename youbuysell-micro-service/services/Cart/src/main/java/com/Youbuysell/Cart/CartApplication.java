package com.Youbuysell.Cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.Youbuysell.Cart.filter.CartFilter;


@SpringBootApplication
public class CartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean getfileterbean()
	{
	
	UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();	
	CorsConfiguration config=new CorsConfiguration();
	config.setAllowCredentials(true);
	config.setAllowedHeaders(Arrays.asList("Accept","Authorization"));
	config.addAllowedOrigin("*");
	config.addAllowedMethod("*");
	config.addAllowedHeader("*");
	urlconfig.registerCorsConfiguration("/**", config);
		
	FilterRegistrationBean fbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
	
	fbean.setFilter(new CartFilter());
	

	
	fbean.addUrlPatterns("/Cart/*");
	
	return fbean;
		
	}
	
}


	

