package services;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Comment;
import domain.Hilo;
import domain.User;

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

	// Tests --------------------------------------------------------------

	// Create an user --------------------------------------------------------------

	@Test
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

		@Test
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

				@Test
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

				@Test
				public void testListAllThreads() {
					
					authenticate("customer");
					
					Collection<Hilo> result;
					
					result= threadService.findAll();
					
					System.out.println("La lista de hilos es: "+ result);
					unauthenticate();

				}
}
