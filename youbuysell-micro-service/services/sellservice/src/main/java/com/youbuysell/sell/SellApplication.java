package com.youbuysell.sell;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.youbuysell.sell.filter.SellFilter;




@SpringBootApplication
public class SellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
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
	
	fbean.setFilter(new SellFilter());
	

	
	fbean.addUrlPatterns("/Sell/product/addproduct,/Sell/product/viewbysid/{sellerid},/Sell/delete/{productid}");
	
	return fbean;
		
	}

}
