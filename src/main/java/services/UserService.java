
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
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
		
		res = new User();
		userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		authority = new Authority();
		threads = new ArrayList<Thread>();
		comments = new ArrayList<Comment>();
		
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
		
		return res;
	}

	public User save(User user) {
		return userRepository.save(user);
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
}