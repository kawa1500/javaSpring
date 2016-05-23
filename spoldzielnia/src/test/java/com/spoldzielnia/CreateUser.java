package com.spoldzielnia;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.UserDAO;
import com.spoldzielnia.app.dao.UserDAOImpl;
import com.spoldzielnia.app.model.User;
import com.spoldzielnia.app.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:/dao.xml"
})
@Transactional
public class CreateUser {
	
	@Inject
	static UserDAO userDB;
	
	static User user;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		user = new User();
		user.setEmail("test");
		user.setFirstName("test");
		user.setLastName("test");
		user.setLogin("test");
		user.setPassword("test");
		user.setPESEL("123123");
		user.setPhone("qwer");
		//System.out.println(user.getLogin());
//		User clear = userDB.getUser(user.getLogin());
//		if(clear!=null)userDB.removeUser(clear.getIdUser());
		userDB.addUser(user);
	}

	@Test
	public void test() {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
	}

}
