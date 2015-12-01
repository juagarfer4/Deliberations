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

import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.ThreadService;
import services.UserService;
import domain.CensusUser;
import domain.Comment;
import domain.Hilo;
import domain.Token;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

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

	public UserController() {
		super();
	}

	// Listing
	// ------------------------------------------------------------------

	// Displaying ---------------------------------------------------------

	// Creation
	// --------------------------------------------------------------------------

	// Edition
	// ------------------------------------------------------------------------

	// // Cookies from authenticate
	// public String getCookieValue(String cookieName, HttpServletRequest
	// request) {
	// String value;
	//
	// value = userService.getCookieValue(cookieName, request);
	//
	// return value;
	// }

	// //TODO para servicio
	// @RequestMapping("/login2")
	// public ModelAndView login( HttpServletRequest request){
	//
	// ModelAndView result=new ModelAndView("user/login");
	//
	// UserAccount account=new UserAccount();
	// Authority au=new Authority();
	// au.setAuthority("CUSTOMER");
	// account.addAuthority(au);
	// result.addObject("account", account);
	// //PRUEBA DE COOKIES FROM AUTENTICATE
	//
	// System.out.println(getCookieValue("user", request));
	// System.out.println(getCookieValue("token", request));
	// System.out.println("se deberían haber mostrado");
	//
	// return result;
	// }

	// TODO a servicio
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView result;
		Authority authority;
		UserAccount userAccount;

		userAccount = new UserAccount();
		authority = new Authority();

		authority.setAuthority("CUSTOMER");
		userAccount.addAuthority(authority);

		result = new ModelAndView("user/login");
		result.addObject("account", userAccount);

		return result;
	}

	// //login from census, this make a http get to census module and get the
	// json output, after tries to make a login with json output
	// //if the person is no present in the bd, save the new person and log in
	// the context.
	// //we are to trust the username census give us is unique
	// //if the person is present in the bd, log in the context
	// //TODO hacerlo bien, nueva funcionalidad
	// @RequestMapping("/loginFromCensus")
	// public ModelAndView loginFromCensus(String username, HttpServletRequest
	// httpRequest) throws JsonParseException, JsonMappingException,
	// IOException{
	//
	// //implementar
	// ModelAndView result;
	//
	// System.out.println(username);
	//
	// // Encontrar en Censos con JSon
	//
	// ObjectMapper objectMapper=new ObjectMapper();
	//
	// //Document
	// doc=Jsoup.connect("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username).get();
	// //System.out.println(doc.toString());
	//
	// // Si da error, el usuario no está en el censo
	//
	// CensusUser censusUser=null;
	// String nameFinal = "";
	// try{
	// // censusUser=objectMapper.readValue(new
	// URL("http://localhost:8080/ADMCensus/census/json_one_user.do?votacion_id=1&username="+username),CensusUser.class);
	//
	// try{
	// censusUser=objectMapper.readValue(new
	// URL("http://localhost:8080/ADMCensus/census/findCensusByVote.do?idVotacion="+1),CensusUser.class);
	// }catch( JsonParseException e){
	// System.out.println(e.toString());
	// return loginFromCensusFrom();
	// }
	// System.out.println(censusUser.toString());
	// Assert.isTrue(censusUser.getUsername()!=null);
	//
	// for (String name: censusUser.getVoto_por_usuario().keySet()){
	//
	// if(name.equals(username)){
	//
	// nameFinal=name;
	// }
	//
	// }
	//
	//
	// }catch(Exception e){
	//
	//
	// return loginFromCensusFrom();
	// }
	//
	// // Si no, adelante
	//
	// if(nameFinal.equals("")){
	//
	// return loginFromCensusFrom();
	// }else if(userService.findUserByUsername(nameFinal)!=null){//esta en la
	// base de datos
	//
	// // Login
	//
	// loginMakeFromCensus(userService.findUserByUsername(username).getUserAccount(),
	// httpRequest);
	//
	// result=new ModelAndView("thread/listThreads");
	//
	//
	// }else{ // Al no estar, se le registra
	// User user;
	// UserAccount userAccount;
	//
	// user = userService.create(username);
	// userAccount = user.getUserAccount();
	//
	// userService.save(user);
	// loginMakeFromCensus(userAccount, httpRequest);
	//
	// result=new ModelAndView("thread/listThreads");
	// }
	// return result;
	// }

	// @RequestMapping(value="loginFromCensusForm", method = RequestMethod.GET)
	// public ModelAndView loginFromCensusFrom(){
	// ModelAndView result;
	//
	// result = new ModelAndView("user/loginFromCensusForm");
	//
	// return result;
	// }

	// public void loginMakeFromCensus(UserAccount user, HttpServletRequest
	// request){
	//
	// try {
	// // Must be called from request filtered by Spring Security, otherwise
	// SecurityContextHolder is not updated
	// System.out.println(request.toString());
	// System.out.println("contraseña pepe de base de datos: "+user.getPassword());
	// UsernamePasswordAuthenticationToken token = new
	// UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
	// null);
	// token.setDetails(new WebAuthenticationDetails(request));
	// DaoAuthenticationProvider authenticator = new
	// DaoAuthenticationProvider();
	// authenticator.setUserDetailsService(userDetailsService);
	//
	// Authentication authentication = authenticator.authenticate(token);
	// SecurityContextHolder.getContext().setAuthentication(authentication);
	// } catch (Exception e) {
	// e.printStackTrace();
	// SecurityContextHolder.getContext().setAuthentication(null);
	// }
	// }

	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount user,
			BindingResult bindingResult, HttpServletRequest request)
			throws IOException {
		ModelAndView result = null;

		if (bindingResult.hasErrors()) {
			result = login();
			System.out.println(bindingResult.toString());
		}
		// primero, debemos ver si esta logeado en el sistema mediante el token
		ObjectMapper objectMapper = new ObjectMapper();

		Token resultOfToken = new Token();

		String tokenToVerify;

		tokenToVerify = loginService.verifyToken(user);

//		System.out.println("el token para comprobar es: " + tokenToVerify);
		// try {
		resultOfToken = objectMapper.readValue(new URL(
				"http://deliberations.hol.es/auth/api/checkToken?token="
						+ tokenToVerify), domain.Token.class);
//		System.out.println("el resultado de autenticación es: "
//				+ resultOfToken.isValid());
		// } catch (JsonParseException e1) {
		// e1.printStackTrace();
		// } catch (JsonMappingException e1) {
		// e1.printStackTrace();
		// } catch (MalformedURLException e1) {
		// e1.printStackTrace();
		// }
		if (resultOfToken.isValid()) {// el usuario esta logueado en
										// autenticación, debemos de loguearlo
										// aqui
			if (!(bindingResult.hasErrors()) || bindingResult == null) {
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				// System.out.println("password encodeado de customer: "+md5.encodePassword(user.getPassword(),
				// null));
				// System.out.println("password de base de datos cust: "+userService.findByPrincipal());
				try {
					String passDB = loginService.loadUserByUsername(
							user.getUsername()).getPassword();
					String passForm = md5.encodePassword(user.getPassword(),
							null);
					System.out.println(passDB);
					System.out.println(passForm);
					Assert.isTrue(loginService
							.loadUserByUsername(user.getUsername())
							.getPassword()
							.equals(md5.encodePassword(user.getPassword(), null)));
				} catch (Exception e) {
					System.out.println(e.toString());
					// Si no está en la base de datos, se crea:

					User user2;

					user2 = userService.create(user.getUsername());

					userService.save(user2);

				}

				try {
					// Must be called from request filtered by Spring Security,
					// otherwise SecurityContextHolder is not updated

					System.out.println(request.toString());

					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
							user.getUsername(), md5.encodePassword(
									user.getPassword(), null));
					token.setDetails(new WebAuthenticationDetails(request));
					DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
					authenticator.setUserDetailsService(userDetailsService);

					Authentication authentication = authenticator
							.authenticate(token);
					SecurityContextHolder.getContext().setAuthentication(
							authentication);
				} catch (Exception e) {
					e.printStackTrace();
					SecurityContextHolder.getContext().setAuthentication(null);
				}

				result = new ModelAndView("thread/list");

			}

		} else { // Si no está logueado, se le reenvía de nuevo a la vista de logi

//			result = login();
			result = new ModelAndView("user/login");

		}

		// A partir de aquí, se puede loguear también en este subsistema

		return result;

	}

//	// Login desde cookies
//
//	@RequestMapping("/loginMake2")
//	public ModelAndView loginMake2(@Valid UserAccount user,
//			BindingResult bindingResult, HttpServletRequest request) {
//
//		ModelAndView result = null;
//
//		if (bindingResult.hasErrors()) {
//
//			result = login();
//			System.out.println(bindingResult.toString());
//
//		}
//
//		// primero, debemos ver si esta logeado en el sistema mediante el token
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		Token resultOfToken = new Token();
//		// para generar el token se envia el password con md5
//		String passwordMd5 = new Md5PasswordEncoder().encodePassword(
//				user.getPassword(), null);
//		// depues se vuelve a calcular el md5 del password + nombre de usario
//		// antes
//		passwordMd5 = user.getUsername()
//				+ new Md5PasswordEncoder().encodePassword(passwordMd5, null);
//		// despues de vuelve a calcular el md5 y se le añade el nombre mas dos
//		// puntos
//		passwordMd5 = user.getUsername() + ":"
//				+ new Md5PasswordEncoder().encodePassword(passwordMd5, null);
//		String tokenToVerify = passwordMd5;
//		System.out.println("el token para comprobar es: " + tokenToVerify);
//		try {
//			resultOfToken = objectMapper.readValue(new URL(
//					"http://deliberations.hol.es/auth/api/checkToken?token="
//							+ tokenToVerify), domain.Token.class);
//			System.out.println("el resultado de autenticación es: "
//					+ resultOfToken.isValid());
//
//		} catch (JsonParseException e1) {
//			e1.printStackTrace();
//		} catch (JsonMappingException e1) {
//			e1.printStackTrace();
//		} catch (MalformedURLException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		if (resultOfToken.isValid()) {// el usuario esta logueado en
//										// autenticación, debemos de loguearlo
//										// aqui
//
//			if (!(bindingResult.hasErrors()) || bindingResult == null) {
//				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//				// System.out.println("password encodeado de customer: "+md5.encodePassword(user.getPassword(),
//				// null));
//				// System.out.println("password de base de datos cust: "+userService.findByPrincipal());
//				try {
//					String passDB = loginService.loadUserByUsername(
//							user.getUsername()).getPassword();
//					String passForm = md5.encodePassword(user.getPassword(),
//							null);
//					System.out.println(passDB);
//					System.out.println(passForm);
//					Assert.isTrue(loginService
//							.loadUserByUsername(user.getUsername())
//							.getPassword()
//							.equals(md5.encodePassword(user.getPassword(), null)));
//				} catch (Exception e) {
//					System.out.println(e.toString());
//					// no esta en la base de datos, lo creamos en entonces:
//
//					User user2 = new User();
//					UserAccount userAccount = new UserAccount();
//					Authority a = new Authority();
//					a.setAuthority("CUSTOMER");
//					userAccount.setUsername(user.getUsername());
//					userAccount.setPassword(new Md5PasswordEncoder()
//							.encodePassword(user.getPassword(), null));
//					userAccount.addAuthority(a);
//					user2.setName(user.getUsername());
//					user2.setUserAccount(userAccount);
//					user2.setBanned(false);
//					user2.setEmail("user@mail");
//					user2.setLocation("location2");
//					user2.setNumberOfMessages(0);
//					user2.setSurname("usernameSurnam");
//					user2.setComments(new ArrayList<Comment>());
//					user2.setThreads(new ArrayList<Hilo>());
//
//					userService.save(user2);
//
//				}
//
//				try {
//					// Must be called from request filtered by Spring Security,
//					// otherwise SecurityContextHolder is not updated
//
//					System.out.println(request.toString());
//
//					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//							user.getUsername(), md5.encodePassword(
//									user.getPassword(), null));
//					token.setDetails(new WebAuthenticationDetails(request));
//					DaoAuthenticationProvider authenticator = new DaoAuthenticationProvider();
//					authenticator.setUserDetailsService(userDetailsService);
//
//					Authentication authentication = authenticator
//							.authenticate(token);
//					SecurityContextHolder.getContext().setAuthentication(
//							authentication);
//				} catch (Exception e) {
//					e.printStackTrace();
//					SecurityContextHolder.getContext().setAuthentication(null);
//				}
//
//				result = new ModelAndView("thread/listThreads");
//
//			}
//
//		} else {// no esta logueado, a tomar por el fonete
//
//			result = login();
//
//		}
//
//		// depues ya podemos loguearlo en el sistema nuestro
//
//		return result;
//
//	}

	// Ancillary methods ----------------------------------------------------------------------

	private ModelAndView createEditModelAndView(Hilo thread) {
		ModelAndView result;
		
		result = createEditModelAndView(thread, null);
		
		return result;
	}

	private ModelAndView createEditModelAndView(Hilo thread, String message) {
		ModelAndView result;

		if (thread.getUser() == null) {// NUEVO

			thread.setUser(userService.findUserByPrincipal());
			thread.setCreationMoment(new Date());// necesario para la
													// restricción de fecha de
													// creación
			result = new ModelAndView("thread/editThread");
			result.addObject("user", thread.getUser());
			result.addObject("thread", thread);

		} else {
			User user = thread.getUser();

			result = new ModelAndView("thread/editThread");

			result.addObject("thread", thread);
			result.addObject("user", user);
		}

		return result;
	}

	// CREACIÓN LOGIN FROM CABINA DE VOTACIÓN, NOS VIENE UNA ID Y UN TOKEN PARA
	// COMPRAR CON AUTENTIFICACIÓN IMPLEMENTAR ES NECESARIO IMPLEMENTAR -

	// CONEXION CON AUTENTICICAION A TRAVES DE JSON PARA PODER LOGUEAR DESDE EL
	// CABINA DE VOTACIÓN

	// CREACIÓN DE HILOS DESDE CREACIÓN/ADMINISTRACIÓN DE VOTACIONES, LES
	// DEBEMOS DE DAR UN LINK PARA QUE NOS TRAIGA Y CREEMOS UNOS NOSOTROS

	@RequestMapping("/createThreadFromVotacion")
	public ModelAndView createTreadFromVotacion(String name) {

		User user = userService.findUserByUsername("customer");

		Hilo nuevo = new Hilo();
		nuevo.setCreationMoment(new Date());
		nuevo.setText("Hilo sobre la votación: " + name);
		nuevo.setUser(user);
		nuevo.setTitle("Votación " + name);
		nuevo.setComments(new ArrayList<Comment>());

		threadService.save(nuevo);
		return new ModelAndView("redirect:thread/list.do");

		// CreacionAdminVotaciones/#/create
	}

	// login desde cabina votacion con toquen, falta implementar por falta de
	// token por parte de autenticación a ellos
	// @RequestMapping("loginFromCabinaVotacion")
}
