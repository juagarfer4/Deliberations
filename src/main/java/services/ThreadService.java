
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.User;
import repositories.ThreadRepository;

import domain.Thread;
@Service
@Transactional
public class ThreadService {
	
	// Managed repository -------------------------
	
	@Autowired
	private ThreadRepository threadRepository;
	
	// Constructors ---------------------
	
	public ThreadService(){
		super();
	}
	
	// Supporting services --------------------
	
	@Autowired
	private UserService userService;
	
	// Simple CRUD methods ---------------------
	
	public Thread create(){
		Thread result;
		User user;
		Date creationMoment;
		Collection<Comment> comments;
		
		result = new Thread();
		user = userService.findUserByPrincipal();
		creationMoment = new Date(System.currentTimeMillis()-1);
		comments = new ArrayList<Comment>();
		
		result.setUser(user);
		result.setCreationMoment(creationMoment);
		result.setComments(comments);
		
		return result;
	}

	public Collection<Thread> findAll() {
		return threadRepository.findAll();
	}

	public Thread findOne(Integer valueOf) {
		return threadRepository.findOne(valueOf);
	}

	public Thread save(Thread thread) {
		Assert.notNull(thread);
		
		Date creationMoment;
		
		creationMoment=new Date(System.currentTimeMillis()-1);
		
		thread.setCreationMoment(creationMoment);
		
		return threadRepository.save(thread);
	}
	
	// Other business methods -------------------
	
}