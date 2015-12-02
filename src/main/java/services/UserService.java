package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.User;
import repositories.UserRepository;
import security.LoginService;

@Service
@Transactional
public class UserService {

	// Managed repository
	@Autowired
	private UserRepository userRepository;

	// Supporting services

	// Constructors
	public UserService() {
		super();
	}

	// Simple CRUD methods
/*	public User create() {
		User res = new User();
		return res;
	}
*/
	public User findOne(int userId) {
		return userRepository.findOne(userId);
	}

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	// Other business methods

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
