
package services;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.ThreadRepository;
import domain.Thread;
@Service
@Transactional
public class ThreadService {
@Autowired
	private ThreadRepository threadRepository;
public Collection<Thread>  findAll(){
return threadRepository.findAll();
}
public Thread findOne(Integer valueOf) {
return threadRepository.findOne(valueOf);
}
public Thread save(Thread thread){
return threadRepository.save(thread);
}
}