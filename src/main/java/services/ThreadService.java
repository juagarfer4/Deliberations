
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
	
	public List<Comment> findCommentsByPage(Integer valueOf, Integer p){
		Hilo hilo;
		Integer numberRows;
		List<Comment> paginatedComments;
		
		hilo = findOne(valueOf);
		numberRows = p*10;
		paginatedComments = new ArrayList<Comment>();
		
		for(int i=numberRows-10; i<=hilo.getComments().size()-1; i++){
			if(i < numberRows){
				System.out.println((Comment) hilo.getComments().toArray()[i]);
				paginatedComments.add((Comment) hilo.getComments().toArray()[i]);
			}else{
				break;
			}
		}
		return paginatedComments;
		
	}
	
	public Integer calculateLastPage(Comment comment, Hilo hilo){
		Double numberComments;
		Double pageDouble;
		Integer res;
		
		if(comment != null)
			numberComments = (double) comment.getThread().getComments().size()+1;
		else
			numberComments = (double) hilo.getComments().size();
		// Calcula qué pagina corresponde al mensaje recién creado, en decimal
		pageDouble = numberComments/10;
		// Se le aplica un redondeo siempre hacia arriba para el cálculo final de la página

		res = (int) Math.ceil(pageDouble);
		
		return res;
		
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