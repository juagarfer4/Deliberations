
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
import domain.Hilo;
import domain.User;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoginService loginService;

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(Integer valueOf) {
		return userRepository.findOne(valueOf);
	}
	
	public User create(String username){
		User res;
		UserAccount userAccount;
		Authority authority;
		List<Hilo> threads;
		List<Comment> comments;
		
		res = new User();
		userAccount =new UserAccount();
		authority = new Authority();
		threads = new ArrayList<Hilo>();
		comments = new ArrayList<Comment>();
		
		authority.setAuthority("CUSTOMER");
		userAccount.setUsername(username);
		userAccount.setPassword(new Md5PasswordEncoder().encodePassword(username, null));
		userAccount.addAuthority(authority);
		
		res.setName(username);
		res.setUserAccount(userAccount);
		res.setBanned(false);
		res.setNumberOfMessages(0);
		res.setComments(comments);
		res.setThreads(threads);
		
		return res;
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByPrincipal() {
		// TODO Auto-generated method stub
		return userRepository.findOneByPrincipal(loginService.getPrincipal().getId());
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
}