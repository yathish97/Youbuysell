package com.youbuysell.authentication.serviceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.youbuysell.authentication.exception.EmailAlreadyExistException;

import com.youbuysell.authentication.model.User;
import com.youbuysell.authentication.repository.UserRepo;
import com.youbuysell.authentication.service.UserServiceImpl;




public class UserServiceImplTest {






 
	@Mock
    private UserRepo userepo;

    private User user;
    
    @InjectMocks
    private UserServiceImpl userServiceImp;

    Optional<User> optional;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
     
        user = new User();
        user.setEmailid("jhon@gmail.com");
        user.setUsername("jhon ");
        user.setPhonenumber("22233392");
        user.setPassword("pass123");
          optional=Optional.of(user);
    }

    @Test
    public void testAddUserSuccess() throws EmailAlreadyExistException {
        Mockito.when(userepo.save(user)).thenReturn(user);
        User usr = userServiceImp.addUser(user);
        assertEquals(user, usr);

    }


    @Test
    public void testAddUserFailure() throws EmailAlreadyExistException{
        Mockito.when(userepo.findById("jhon@gmail.com")).thenReturn(optional);
        Mockito.when(userepo.save(user)).thenReturn(user);
        assertThrows(
        		EmailAlreadyExistException.class,
                    () -> { userServiceImp.addUser(user); });

    }

    @Test
    public void testLogin()  {
        Mockito.when(userepo.save(user)).thenReturn(user);
        boolean flag = userServiceImp.login(user);
        assertEquals(false, flag);
    }
    

    }
