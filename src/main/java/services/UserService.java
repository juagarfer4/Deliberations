package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import domain.Comment;
import domain.Rating;
import domain.Thread;
import domain.User;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UserService {
	
	// Managed repository ------------------
	
	@Autowired
	private UserRepository userRepository;
	
	// Supporting services ----------------
	
	@Autowired
	private LoginService loginService;
	
	// Constructors -------------------
	
	public UserService(){
		super();
	}
	
	// Simple CRUD methods -----------------

	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Integer userId) {
		return userRepository.findOne(userId);
	}
	
	public User create(String username){
		User res;
		UserAccount userAccount;
		Authority authority;
		List<Thread> threads;
		List<Comment> comments;
		List<Rating> ratings;
		
		res = new User();
		userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		authority = new Authority();
		threads = new ArrayList<Thread>();
		comments = new ArrayList<Comment>();
		ratings = new ArrayList<Rating>();
		
		authority.setAuthority("USER");
		authorities.add(authority);
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.setAuthorities(authorities);
		
		res.setName(username);
		res.setUserAccount(userAccount);
		res.setIsBanned(false);
		res.setNumberOfMessages(0);
		res.setComments(comments);
		res.setThreads(threads);
		res.setRatings(ratings);
		
		return res;
	}

	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	// Other business methods ------------------------

	public User findUserByPrincipal() {
		return userRepository.findOneByPrincipal(loginService.getPrincipal().getId());
	}

	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public String getCookieValue(String cookieName, HttpServletRequest request) {
	    String value = null;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	      int i = 0;
	      boolean cookieExists = false;
	      while (!cookieExists && i < cookies.length) {
	        if (cookies[i].getName().equals(cookieName)) {
	          cookieExists = true;
	          value = cookies[i].getValue();
	        } else {
	          i++;
	        }
	      }
	    }
	    return value;
	  }

	public User findOneByPrincipal(){
		User res=new User();
		res=userRepository.findOneByPrincipal(LoginService.getPrincipal().getId());
		return res;
	}
	
	public User findByUsername(String username){
		User res=new User();
		res=userRepository.findByUsername(username);
		return res;
	}
	
	public Collection<User> findUserWithZeroComments(){
		Collection<User> res= new ArrayList<User>();
		res=userRepository.findUserWithZeroComments();
		return res;
	}
	
	public Collection<User> findUserWithMoreComments(){
		Collection<User> res= new ArrayList<User>();
		res=userRepository.findUserWithMoreComments();
		return res;
	}
		
	public Collection<User> findUserWithMoreThreads(){
		Collection<User> res= new ArrayList<User>();
		res=userRepository.findUserWithMoreThreads();
		return res;
	}
	
}