package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;
import domain.Hilo;
import domain.Token;
import domain.User;
import security.LoginService;
import security.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ServiceTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private UserService userService;
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private LoginService loginService;

	// Tests --------------------------------------------------------------

	// Create an user --------------------------------------------------------------

//	@Test
	public void testCreateUser() {

		User result;
		

		result = userService.create("Usuario prueba");
		
		result.getUserAccount().setPassword("prueba");
		result.setEmail("prueba@gmail.com");
		result.setSurname("prueba");
		result.setUrl("www.google.es");
		result.setLocation("Prueba");

		result=userService.save(result);
		
		System.out.println("El usuario ha sido creado correctamente.");
		

	}
	
	
	
	
	// Create a thread --------------------------------------------------------------

//		@Test
		public void testCreateThread() {
			
			authenticate("customer");
			Hilo result;
			

			result = threadService.create();
			
			result.setTitle("Titulo prueba");
			result.setText("Texto prueba");

			

			result=threadService.save(result);
			
			
			System.out.println("El hilo ha sido creado correctamente.");
			unauthenticate();

		}
		
		// Create a comment --------------------------------------------------------------

//				@Test
				public void testCreateComment() {
					
					authenticate("customer");
					Comment result;
					Hilo hilo;

					result = new Comment();
					
					hilo= threadService.findOne(5);
					result.setCreationMoment(new Date());
					
					result.setThread(hilo);
					result.setUser(userService.findUserByPrincipal());
					result.setText("Texto comentario");
					

					result=commentService.save(result);
					
					
					System.out.println("El comentario ha sido creado correctamente.");
					unauthenticate();

				}
			
				// List all threads --------------------------------------------------------------

//				@Test
				public void testListAllThreads() {
					
					authenticate("customer");
					
					Collection<Hilo> result;
					
					result= threadService.findAll();
					
					System.out.println("La lista de hilos es: "+ result);
					unauthenticate();

				}
				
				
				@Test
				public void testToken() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
					
					
				ObjectMapper objectMapper;
				UserAccount userAccount;
				String tokenToVerify;
				Token resultOfToken;
				
				objectMapper = new ObjectMapper();
				userAccount = new UserAccount();
				
				// Se asignan usuario y contraseña a una cuenta de usuario nueva porque si se recuperase una ya creada incluiría el hash
				// de la contraseña en lugar de la contraseña en sí
				userAccount.setUsername("usuario");
				userAccount.setPassword("usuario");
				
				tokenToVerify = loginService.verifyToken(userAccount);
				
				resultOfToken = objectMapper.readValue(new URL(
						"http://deliberations.hol.es/auth/api/checkToken?user="+userAccount+"&token="
								+ tokenToVerify), domain.Token.class);
				System.out.println("resultado del token: "+resultOfToken.isValid());
				
				Assert.isTrue(resultOfToken.isValid());
				}
}
