package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Thread;
import repositories.ThreadRepository;
import security.LoginService;

@Service
@Transactional
public class ThreadService {

	// Managed repository
	@Autowired
	private ThreadRepository threadRepository;

	// Supporting services

	// Constructors
	public ThreadService() {
		super();
	}

	// Simple CRUD methods
	public Thread create() {
		Thread res = new Thread();
		return res;
	}

	public Thread findOne(int threadId) {
		return threadRepository.findOne(threadId);
	}

	public Collection<Thread> findAll() {
		return threadRepository.findAll();
	}

	public void save(Thread thread) {
		threadRepository.save(thread);
	}

	public void delete(Thread thread) {
		threadRepository.delete(thread);
	}

	// Other business methods

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
