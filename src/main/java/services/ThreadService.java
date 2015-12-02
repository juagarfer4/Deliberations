package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comment;
import domain.User;
import repositories.ThreadRepository;

import javax.transaction.Transactional;


import domain.Thread;
import security.LoginService;

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

	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
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
	
	public void delete(Thread thread) {
		threadRepository.delete(thread);
	}

	// Other business methods --------------------

	public Collection<Thread> findThreadWithMoreComments(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithMoreComments();
		return res;
	}
	
	public Collection<Thread> findThreadWithLessComments(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithLessComments();
		return res;
	}
	
	public Collection<Thread> findThreadOfUser(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadOfUser(LoginService.getPrincipal().getId());
		return res;
	}
	
	public Collection<Thread> findThreadWithTitle(String title){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadWithTitle(title);
		return res;
	}
	
	public Collection<Thread> findThreadAvailables(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadAvailables();
		return res;
	}
	
	public Collection<Thread> findThreadMoreRating(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadMoreRating();
		return res;
	}
	
	public Collection<Thread> findThreadLessRating(){
		Collection<Thread> res= new ArrayList<Thread>();
		res=threadRepository.findThreadLessRating();
		return res;
	}
}
