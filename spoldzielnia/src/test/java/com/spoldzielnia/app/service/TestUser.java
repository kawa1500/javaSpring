package com.spoldzielnia.app.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spoldzielnia.app.dao.UserDAO;
import com.spoldzielnia.app.dao.UserDAOImpl;
import com.spoldzielnia.app.model.Flat;
import com.spoldzielnia.app.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestUser extends AbstractTransactionalJUnit4SpringContextTests {

	
	
	@Test
	public void addUser(){
//		ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/appServlet/spring-dao.xml");
//		
//		UserDAO userDAO = (UserDAO) context.getBean( "UserDAO" );
//		
//		
//		User u = new User();
//		u.setEmail("bbb@www.com");
//		u.setFirstName("bartek");
//		Flat f = new Flat();
//		f.setIdFlat(75);
//		u.setFlat(f);
//		u.setLastName("test");
//		u.setLogin("tescik");
//		u.setPassword("password");
//		u.setPESEL("9308888888");
//		u.setPhone("+48134565");
//		
//		userDAO.addUser(u);

	}

}
