
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Hilo;
import repositories.ThreadRepository;

@Service
@Transactional
public class ThreadService {
	
	// Managed repository -------------------------
	
	@Autowired
	private ThreadRepository threadRepository;
	
	// Supporting services --------------------
	
	// Simple CRUD methods ---------------------
	
	public Hilo create(){
		Hilo result;
		
		result=new Hilo();
		
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
		
		return threadRepository.save(thread);
	}
	
	// Other business methods -------------------
	
}