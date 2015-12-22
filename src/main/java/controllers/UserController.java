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
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Token;
import domain.User;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import services.CommentService;
import services.ThreadService;
import services.UserService;

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
		result.addObject("userAccount", userAccount);

		return result;
	}

	@RequestMapping("/loginMake")
	public ModelAndView loginMake(@Valid UserAccount user,
			BindingResult bindingResult, HttpServletRequest request)
			throws IOException {
		ModelAndView result = null;

		if (bindingResult.hasErrors()) {
			result = login();
			System.out.println(bindingResult.toString());
		}
		// Mediante el token es posible comprobar si el nombre de usuario y la contraseña introducidos se corresponden
		// con un usuario creado en Autenticación
		ObjectMapper objectMapper = new ObjectMapper();

		Token resultOfToken = new Token();

		String tokenToVerify;

		tokenToVerify = loginService.verifyToken(user);

		resultOfToken = objectMapper.readValue(new URL(
				"http://deliberations.hol.es/auth/api/checkToken?token="
						+ tokenToVerify), domain.Token.class);
		if (resultOfToken.isValid()) {// El usuario está logueado en
										// Autenticación, debemos loguearlo
										// aquí
			if (!(bindingResult.hasErrors()) || bindingResult == null) {
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
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
					// Si no está en nuestra base de datos, se crea:

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

				result = new ModelAndView("redirect:/");

			}

		} else { // Si no está logueado, se le reenvía de nuevo a la vista de logi
			System.out.println("#5");
//			result = login();
			result = new ModelAndView("user/login");

		}

		// A partir de aquí, se puede loguear también en este subsistema

		return result;

	}

	// Ancillary methods ----------------------------------------------------------------------

	private ModelAndView createEditModelAndView(domain.Thread thread) {
		ModelAndView result;
		
		result = createEditModelAndView(thread, null);
		
		return result;
	}

	private ModelAndView createEditModelAndView(domain.Thread thread, String message) {
		ModelAndView result;

		if (thread.getUser() == null) {// NUEVO

			thread.setUser(userService.findOneByPrincipal());
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

		User user = userService.findByUsername("customer");

		domain.Thread nuevo = new domain.Thread();
		nuevo.setCreationMoment(new Date());
		nuevo.setDecription("Hilo sobre la votación: " + name);
		nuevo.setUser(user);
		nuevo.setTitle("Votación " + name);
		nuevo.setComments(new ArrayList<Comment>());

		threadService.save(nuevo);
		return new ModelAndView("redirect:thread/list.do");

		// CreacionAdminVotaciones/#/create

	}
}
