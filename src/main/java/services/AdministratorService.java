
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	
	// Managed repository ----------------------
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Constructors ---------------------
	
	public AdministratorService(){
		super();
	}
	
	// Simple CRUD methods ------------------------

	public Collection<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Administrator findOne(Integer valueOf) {
		return administratorRepository.findOne(valueOf);
	}

	public Administrator save(Administrator administrator) {
		return administratorRepository.save(administrator);
	}
	
	// Other business methods -------------------
	
}