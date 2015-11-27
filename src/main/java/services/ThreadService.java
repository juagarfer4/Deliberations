
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Hilo;
import domain.User;
import repositories.ThreadRepository;

@Service
@Transactional
public class ThreadService {
	
	// Managed repository -------------------------
	
	@Autowired
	private ThreadRepository threadRepository;
	
	// Supporting services --------------------
	
	@Autowired
	private UserService userService;
	
	// Simple CRUD methods ---------------------
	
	public Hilo create(){
		Hilo result;
		User user;
		Date creationMoment;
		Collection<Comment> comments;
		
		result = new Hilo();
		user = userService.findUserByPrincipal();
		creationMoment = new Date(System.currentTimeMillis()-1);
		comments = new ArrayList<Comment>();
		
		result.setUser(user);
		result.setCreationMoment(creationMoment);
		result.setComments(comments);
		
		return result;
	}

	public Collection<Hilo> findAll() {
		return threadRepository.findAll();
	}

	public Hilo findOne(Integer valueOf) {
		return threadRepository.findOne(valueOf);
	}

	public Hilo save(Hilo thread) {
		Assert.notNull(thread);
		
		Date creationMoment;
		
		creationMoment=new Date(System.currentTimeMillis()-1);
		
		thread.setCreationMoment(creationMoment);
		
		return threadRepository.save(thread);
	}
	
	// Other business methods -------------------
	
}