/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.CensusUser;
import domain.Comment;
import domain.Hilo;
import domain.Token;
import domain.User;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.ThreadService;
import services.UserService;


@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {
	
	// Supporting services -----------------------
	
	@Autowired
	private ThreadService threadService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Listing ------------------------------------------------------------------
	
	@RequestMapping(value="/listThreads", method= RequestMethod.GET)
	public ModelAndView prueba(){
		//creacion de variables
		ModelAndView result;
		Collection<Hilo> threads;
		//asignacion de valores
		threads=threadService.findAll();
		result=new ModelAndView("customer/listThreads");
		result.addObject("threads",threads);
		//mostrar resultado
		return result;
	}
	
	// Displaying ---------------------------------------------------------
	
	@RequestMapping(value = "/seeThread", method = RequestMethod.GET)
	public ModelAndView seeThread(@RequestParam int id){
		ModelAndView result;
		Hilo hilo;
			
		hilo=threadService.findOne(id);
		result =new ModelAndView("customer/seeThread");
		result.addObject("hilo",hilo);
		result.addObject("comments",hilo.getComments());
		
		Comment comment=new Comment();
		
		comment.setCreationMoment(new Date());
		comment.setThread(hilo);
		comment.setUser(userService.findUserByPrincipal());
		result.addObject("comment", comment);
		//devuelve hilo mas sus comentarios
		return result;
			
		
		
	}
	
	// Creation --------------------------------------------------------------------------
	
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public ModelAndView saveComment(@Valid Comment comment,BindingResult binding){
		
		ModelAndView result=new ModelAndView("redirect:listThreads.do");
		
		if(binding.hasErrors()){
			
			result=seeThread(comment.getThread().getId());
			System.out.println(binding.toString());
			
		}else{
			try{
			commentService.save(comment);
			result=seeThread(comment.getThread().getId());
			//debemos comprobar si se guarda o no para guardar tambien el hilo
			}catch(Throwable op){
				result=seeThread(comment.getThread().getId());
				op.printStackTrace();
			}
		}
		return result;
	}
	
	
	// Creation ------------------------------------------------------------------------
	
	@RequestMapping("/createThread")
	public ModelAndView createThread(){
		
		ModelAndView result=createEditModelAndView(new domain.Hilo());
		
		return result;
		
	}
	
	// Edition ------------------------------------------------------------------------
	
	@RequestMapping(value = "/editThread", method = RequestMethod.GET)
	public ModelAndView editThread(@RequestParam int id){
		Hilo thread=threadService.findOne(id);
		
		ModelAndView result=createEditModelAndView(thread);
		
		return result;
	}
	
	@RequestMapping(value = "/saveThread", method = RequestMethod.POST)
	public ModelAndView saveThread(@ModelAttribute("thread") @Valid Hilo thread, BindingResult binding){
		
		ModelAndView result=null;
		if(binding.hasErrors()){
			result=createEditModelAndView(thread);
			
			System.out.println(binding.toString());
		}else{
			
			
			try{
				
				
				threadService.save(thread);
				result=new ModelAndView("redirect:listThreads.do");
			}catch(Throwable opps){
				
				result=createEditModelAndView(thread, "Transactional error");
				
			}
			
		}
		
		
		return result;
	}
	
	@RequestMapping("/deleteThread")
	public ModelAndView deleteThread(@RequestParam int id){
		
		
		Hilo thread=threadService.findOne(id);
		
		
		//TODO
		
		return new ModelAndView("customer/deleteThread");
		
	}
	
	//cookies from autenticate
	 public String getCookieValue(String cookieName, HttpServletRequest request) {
		    String value;
		    
		    value = userService.getCookieValue(cookieName, request);
		    
		    return value;
	 }
	
	
	
//	//TODO para servicio
//	@RequestMapping("/login2")
//	public ModelAndView login( HttpServletRequest request){
//		
//		ModelAndView result=new ModelAndView("customer/login");
//		
//		UserAccount account=new UserAccount();
//		Authority au=new Authority();
//		au.setAuthority("CUSTOMER");
//		account.addAuthority(au);
//		result.addObject("account", account);
//		//PRUEBA DE COOKIES FROM AUTENTICATE
//		
//		System.out.println(getCookieValue("user", request));
//		System.out.println(getCookieValue("token", request));
//		System.out.println("se deber�an haber mostrado");
//		
//		return result; 
//	}
	//TODO a servicio
	//Prueba control de cambios
	@RequestMapping("/login")
	public ModelAndView login(){
		
		ModelAndView result=new ModelAndView("customer/login");
		
		UserAccount account=new UserAccount();
		Authority au=new Authority();
		au.setAuthority("CUSTOMER");
		account.addAuthority(au);
		result.addObject("account", account);
		
		return result; 
	}
	
	//login from census, this make a http get to census module and get the json output, after tries to make a login with json output
	//if the person is no present in the bd, save the new person and log in the context.
	//we are to trust the username census give us is unique
	//if the person is present in the bd, log in the context
	//TODO hacerlo bien, nueva funcionalidad
	@RequestMapping("/loginFromCensus")
	public ModelAndView loginFromCensus(String username, HttpServletRequest httpRequest) throws JsonParseException, JsonMappingException, IOException{
		
		//implementar
		ModelAndView result;
		
		System.out.println(username);
		
		// Encontrar en Censos con JSon
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		//Document doc=Jsoup.connect("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username).get();
		//System.out.println(doc.toString());
		
		// Si da error, el usuario no est� en el censo
		
		CensusUser censusUser=null;
		String nameFinal = "";
		try{
		// censusUser=objectMapper.readValue(new URL("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username),CensusUser.class);
		
			try{
			censusUser=objectMapper.readValue(new URL("http://localhost:8080/ADMCensus/census/findCensusByVote.do?idVotacion="+1),CensusUser.class);
			}catch( JsonParseException e){
				System.out.println(e.toString());	
				return loginFromCensusFrom();
			}
		System.out.println(censusUser.toString());
		Assert.isTrue(censusUser.getUsername()!=null);
		
		for (String name: censusUser.getVoto_por_usuario().keySet()){
			
			if(name.equals(username)){
				
				nameFinal=name;
			}
			
		}
		
		
		}catch(Exception e){
			
			
			return loginFromCensusFrom();
		}
		
		// Si no, adelante
		
		if(nameFinal.equals("")){
			
			return loginFromCensusFrom();
		}else if(userService.findUserByUsername(nameFinal)!=null){//esta en la base de datos
			
			// Login
			
			loginMakeFromCensus(userService.findUserByUsername(username).getUserAccount(), httpRequest);
			
			result=new ModelAndView("customer/listThreads");
			
			
		}else{ // Al no estar, se le registra
			User user;
			UserAccount userAccount;
			
			user = userService.create(username);
			userAccount = user.getUserAccount();
			
			userService.save(user);
			loginMakeFromCensus(userAccount, httpRequest);
			
			result=new ModelAndView("customer/listThreads");
		}
		return result;
	}
	
	@RequestMapping(value="loginFromCensusForm", method = RequestMethod.GET)
	public ModelAndView loginFromCensusFrom(){
		ModelAndView result;
		
		result = new ModelAndView("customer/loginFromCensusForm");
		
		return result;
	}
	
	
	public void loginMakeFromCensus(UserAccount user, HttpServletRequest request){
		
		  try {
	            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
	        	System.out.println(request.toString());
	        	System.out.println("contrase�a pepe de base de datos: "+user.getPassword());
	            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(), null);
	            token.setDetails(new WebAuthenticationDetails(request));
	            DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
	            authenticator.setUserDetailsService(userDetailsService);
	           
	            Authentication authentication = authenticator.authenticate(token);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        } catch (Exception e) {
	            e.printStackTrace();
	            SecurityContextHolder.getContext().setAuthentication(null);
	        }
	}
	
	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount user, BindingResult bindingResult, HttpServletRequest request){
		ModelAndView result=null;
		
		if(bindingResult.hasErrors()){
			result=login();
			System.out.println(bindingResult.toString());
		}
		//primero, debemos ver si esta logeado en el sistema mediante el token
		ObjectMapper objectMapper=new ObjectMapper();

		Token resultOfToken=new Token();
		//para generar el token se envia el password con MD5
		String passwordMd5=new Md5PasswordEncoder().encodePassword(user.getPassword(), null);
		//depues se vuelve a calcular el md5 del password + nombre de usario antes
		passwordMd5=user.getUsername()+new Md5PasswordEncoder().encodePassword(passwordMd5, null);
		//despues de vuelve a calcular el md5 y se le a�ade el nombre mas dos puntos
		passwordMd5=user.getUsername()+":"+new Md5PasswordEncoder().encodePassword(passwordMd5, null);
		String tokenToVerify=passwordMd5;
		System.out.println("el token para comprobar es: "+tokenToVerify);
		try {
			resultOfToken = objectMapper.readValue(new URL("http://deliberations.hol.es/auth/api/checkToken?token="+tokenToVerify),domain.Token.class);
			System.out.println("el resultado de autenticaci�n es: "+resultOfToken.isValid());
			
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(resultOfToken.isValid()){//el usuario esta logueado en autenticaci�n, debemos de loguearlo aqui
			if(!(bindingResult.hasErrors()) || bindingResult==null){
				Md5PasswordEncoder md5=new Md5PasswordEncoder();
				//System.out.println("password encodeado de customer: "+md5.encodePassword(user.getPassword(), null));
			//	System.out.println("password de base de datos cust: "+userService.findByPrincipal());
				try{
				String passDB=loginService.loadUserByUsername(user.getUsername()).getPassword();
				String passForm=md5.encodePassword(user.getPassword(), null);
				System.out.println(passDB);
				System.out.println(passForm);
				Assert.isTrue(loginService.loadUserByUsername(user.getUsername()).getPassword().equals(md5.encodePassword(user.getPassword(), null)));
				}catch( Exception e){
					System.out.println(e.toString());
					//no esta en la base de datos, lo creamos en entonces:

					User user2 = new User();
					UserAccount userAccount=new UserAccount();
					Authority a=new Authority();
					a.setAuthority("CUSTOMER");
					userAccount.setUsername(user.getUsername());
					userAccount.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), null));
					userAccount.addAuthority(a);
					user2.setName(user.getUsername());
					user2.setUserAccount(userAccount);
					user2.setBanned(false);
					user2.setEmail("user@mail");
					user2.setLocation("location2");
					user2.setNumberOfMessages(0);
					user2.setSurname("usernameSurnam");
					user2.setComments(new ArrayList<Comment>());
					user2.setThreads(new ArrayList<Hilo>());
					
					userService.save(user2);
					
				}

		        try {
		            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
		            
		        	System.out.println(request.toString());
		        	
		            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), md5.encodePassword(user.getPassword(), null));
		            token.setDetails(new WebAuthenticationDetails(request));
		            DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
		            authenticator.setUserDetailsService(userDetailsService);
		           
		            Authentication authentication = authenticator.authenticate(token);
		            SecurityContextHolder.getContext().setAuthentication(authentication);
		        } catch (Exception e) {
		            e.printStackTrace();
		            SecurityContextHolder.getContext().setAuthentication(null);
		        }
				
				
				
				
				result=new ModelAndView("customer/listThreads");
				
				
				
			}
			
			
			
			
			
			
		}else{//no esta logueado, a tomar por el fonete
			
			result=login();
			
		}
		
		
		
		//depues ya podemos loguearlo en el sistema nuestro
		

		return result;
		
		
	}
	
	
	
	//login from cookies
	
	@RequestMapping("/loginMake2")
	public ModelAndView loginMake2(@Valid UserAccount user, BindingResult bindingResult, HttpServletRequest request){
		
		
		
		ModelAndView result=null;
		
		if(bindingResult.hasErrors()){
			
			
			result=login();
			System.out.println(bindingResult.toString());
			
		}
		
		
		
		//primero, debemos ver si esta logeado en el sistema mediante el token
		ObjectMapper objectMapper=new ObjectMapper();

		Token resultOfToken=new Token();
		//para generar el token se envia el password con md5
		String passwordMd5=new Md5PasswordEncoder().encodePassword(user.getPassword(), null);
		//depues se vuelve a calcular el md5 del password + nombre de usario antes
		passwordMd5=user.getUsername()+new Md5PasswordEncoder().encodePassword(passwordMd5, null);
		//despues de vuelve a calcular el md5 y se le a�ade el nombre mas dos puntos
		passwordMd5=user.getUsername()+":"+new Md5PasswordEncoder().encodePassword(passwordMd5, null);
		String tokenToVerify=passwordMd5;
		System.out.println("el token para comprobar es: "+tokenToVerify);
		try {
			resultOfToken = objectMapper.readValue(new URL("http://deliberations.hol.es/auth/api/checkToken?token="+tokenToVerify),domain.Token.class);
			System.out.println("el resultado de autenticaci�n es: "+resultOfToken.isValid());
			
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		if(resultOfToken.isValid()){//el usuario esta logueado en autenticaci�n, debemos de loguearlo aqui
			
			
			
			
			
			if(!(bindingResult.hasErrors()) || bindingResult==null){
				Md5PasswordEncoder md5=new Md5PasswordEncoder();
				//System.out.println("password encodeado de customer: "+md5.encodePassword(user.getPassword(), null));
			//	System.out.println("password de base de datos cust: "+userService.findByPrincipal());
				try{
				String passDB=loginService.loadUserByUsername(user.getUsername()).getPassword();
				String passForm=md5.encodePassword(user.getPassword(), null);
				System.out.println(passDB);
				System.out.println(passForm);
				Assert.isTrue(loginService.loadUserByUsername(user.getUsername()).getPassword().equals(md5.encodePassword(user.getPassword(), null)));
				}catch( Exception e){
					System.out.println(e.toString());
					//no esta en la base de datos, lo creamos en entonces:
					
					
					User user2 = new User();
					UserAccount userAccount=new UserAccount();
					Authority a=new Authority();
					a.setAuthority("CUSTOMER");
					userAccount.setUsername(user.getUsername());
					userAccount.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), null));
					userAccount.addAuthority(a);
					user2.setName(user.getUsername());
					user2.setUserAccount(userAccount);
					user2.setBanned(false);
					user2.setEmail("user@mail");
					user2.setLocation("location2");
					user2.setNumberOfMessages(0);
					user2.setSurname("usernameSurnam");
					user2.setComments(new ArrayList<Comment>());
					user2.setThreads(new ArrayList<Hilo>());
					
					userService.save(user2);
					
					
					
					
					
					
				}

		        try {
		            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
		            
		        	System.out.println(request.toString());
		        	
		            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), md5.encodePassword(user.getPassword(), null));
		            token.setDetails(new WebAuthenticationDetails(request));
		            DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
		            authenticator.setUserDetailsService(userDetailsService);
		           
		            Authentication authentication = authenticator.authenticate(token);
		            SecurityContextHolder.getContext().setAuthentication(authentication);
		        } catch (Exception e) {
		            e.printStackTrace();
		            SecurityContextHolder.getContext().setAuthentication(null);
		        }
				
				
				
				
				result=new ModelAndView("customer/listThreads");
				
				
				
			}
			
			
			
			
			
			
		}else{//no esta logueado, a tomar por el fonete
			
			result=login();
			
		}
		
		
		
		//depues ya podemos loguearlo en el sistema nuestro
		

		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	// Ancillary methods ----------------------------------------------------------------------
	
	private ModelAndView createEditModelAndView(Hilo thread){
		ModelAndView result;
		result = createEditModelAndView(thread, null);
		return result;
	}
	
	private ModelAndView createEditModelAndView(Hilo thread, String message){
		ModelAndView result;
		
		if(thread.getUser()==null){//NUEVO
			
			thread.setUser(userService.findUserByPrincipal());
			thread.setCreationMoment(new Date());//necesario para la restricci�n de fecha de creaci�n
			result=new ModelAndView("customer/editThread");
			result.addObject("user", thread.getUser());
			result.addObject("thread", thread);			
			
		}else{
			User user=thread.getUser();
			
			result=new ModelAndView("customer/editThread");
			
			result.addObject("thread", thread);
			result.addObject("user", user);
		}
		
		return result;
	}
	

//CREACI�N LOGIN FROM CABINA DE VOTACI�N, NOS VIENE UNA ID Y UN TOKEN PARA COMPRAR CON AUTENTIFICACI�N IMPLEMENTAR ES NECESARIO IMPLEMENTAR - 
	
//CONEXION CON AUTENTICICAION A TRAVES DE JSON PARA PODER LOGUEAR DESDE EL CABINA DE VOTACI�N
	
	
	
	
	
	//CREACI�N DE HILOS DESDE CREACI�N/ADMINISTRACI�N DE VOTACIONES, LES DEBEMOS DE DAR UN LINK PARA QUE NOS TRAIGA Y CREEMOS UNOS NOSOTROS
	
	
	@RequestMapping("/createThreadFromVotacion")
	public ModelAndView createTreadFromVotacion(String name){
		
		User user=userService.findUserByUsername("customer");
		
		Hilo nuevo=new Hilo();
		nuevo.setCreationMoment(new Date());
		nuevo.setText("Hilo sobre la votaci�n: "+name);
		nuevo.setUser(user);
		nuevo.setTitle("Votaci�n "+name);
		nuevo.setComments(new ArrayList<Comment>());
		
		threadService.save(nuevo);
		return 	new ModelAndView("redirect:listThreads.do");
		
		//CreacionAdminVotaciones/#/create
	}
	
	
	
	
	
	//login desde cabina votacion con toquen, falta implementar por falta de token por parte de autenticaci�n a ellos
	//@RequestMapping("loginFromCabinaVotacion")
}
