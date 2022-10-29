package com.youbuysell.authentication.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youbuysell.authentication.controller.UserController;
import com.youbuysell.authentication.model.User;
import com.youbuysell.authentication.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	
	
	
	

	

	
	@Autowired
    private MockMvc mockMvc;


@MockBean
private UserService userService;
private User user;
   @InjectMocks
   private UserController userController;

   @BeforeEach
   public void setUp() throws Exception {

       MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

     user = new User();
     user.setEmailid("jhon@gmail.com");
     user.setUsername("jhon");
     user.setPhonenumber("22233392");
     user.setPassword("pass123");


   }
   @Test
   public void testRegisterUser() throws Exception {

       Mockito.when(userService.addUser(user)).thenReturn(user);
       	
       mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
             .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());

   }

 @Test
 public void testLoginUser() throws Exception {


	 String emailid = "jhon@gmail.com";
      String  password = "pass123";


Mockito.when(userService.addUser(user)).thenReturn(user);
   		        	
mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login").contentType(MediaType.APPLICATION_JSON).
content(jsonToString(user))).andExpect(MockMvcResultMatchers.status().isUnauthorized())
.andDo(MockMvcResultHandlers.print());
        }

   
   // Parsing String format data into JSON format
   private static String jsonToString(final Object obj) throws JsonProcessingException {
      String result;
      try {
          final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
          result = jsonContent;
       } catch (JsonProcessingException e) {
           result = "Json processing error";
      }
       return result;

   }
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.youbuysell.authentication.controller.UserController;
//import com.youbuysell.authentication.exception.EmailIdNotFoundException;
//import com.youbuysell.authentication.model.User;
//import com.youbuysell.authentication.service.UserService;


//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest
	
//@Autowired
//    private MockMvc mockMvc;
//    
//    @InjectMocks
//    private UserController userController;
//
//	 @MockBean
//	  private UserService userService;
//			
//	
//	 private User user;

//	private String ;
	
	
	
//	@BeforeEach
//	 
//    public void setup() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//
//        user = new User();
//        user.setEmailid("jhon@gmail.com");
//        user.setUsername("Jhon ");
//        user.setPhonenumber(22233392);
//        user.setPassword("pass123");
//      
//        
//    }
//	
//    @Test
//    public void testRegisterUser() throws Exception {
//
//        Mockito.when(userService.addUser(user)).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register")
//        		.contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
//
//    }
//    
//    
    

    
    
    
//    @Test
//    public void testUpdateUser() throws Exception {
//
//        Mockito.when(userService.updateUser(user)).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.put("/authentication/updateuser").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound()).andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//    @Test
//    public void testLoginUser() throws Exception {
//
//
//        String emailid = "jhon@gmail.com";
//        String  password = "pass123";
//
//
//        Mockito.when(userService.addUser(user)).thenReturn(user);
//        Mockito.when(userService.login(user)).thenReturn(false);
//        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
//                .andDo(MockMvcResultHandlers.print());
//    }
    
  
//    
//   @Test
//    public void deleteUserFailure() throws Exception {
//        Mockito.when(userService.deleteUser("jhon@gmail.com")).thenThrow(EmailIdNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/authentication/delete/jhon@gmail.com")
//               .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//        
//	 }
//    
//    
//    @Test
//    public void deleteUserSuccess() throws Exception {
//        Mockito.when(userService.deleteUser("jhon@gmail.com")).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/authentication/delete/jhon@gmail.com")
//               .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//   }
//    
//    
//   
//    
//    // Parsing String format data into JSON format
//    private static String jsonToString(final Object obj) throws JsonProcessingException {
//        String result;
//        try {
//            final ObjectMapper mapper = new ObjectMapper();
//            final String jsonContent = mapper.writeValueAsString(obj);
//            result = jsonContent;
//        } catch (JsonProcessingException e) {
//            result = "Json processing error";
//        }
//        return result;
//    }
//}
	
	
	
	
	
	
	
	
	
	
	
	





    
    
    
    
    
    
    
    
    
    
    
    /////0000000000000000000000000000000000
//	@Test
//	public void whenaddthenstoreusersuccess() throws Exception
//	{
//	Mockito.when(userService.addUser(user)).thenReturn(user);
//	mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(convertobject(user)))
//			.andExpect(MockMvcResultMatchers.status().isCreated())
//			.andDo(MockMvcResultHandlers.print());
//		
//	}
//	
//	
//
//
//private String convertobject(User user2) throws JsonProcessingException{
//	// TODO Auto-generated method stub
//	ObjectMapper objmap= new ObjectMapper();
//	String result = objmap.writeValueAsString(user2);
//	
//	return result;
//}
//

	






	

