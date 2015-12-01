
package domain;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor{
	
	// Constructors ------------------------------------------------------------
	public User() {
		super();
	}
	
	// Attributes -------------------------------------------------------------
	private boolean banned;
	private int numberOfMessages;
	private String url;
	
	
	
//	@NotNull
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
//	@NotNull
	public int getNumberOfMessages() {
		return numberOfMessages;
	}
	public void setNumberOfMessages(int numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}
	
//	@SafeHtml
//	@NotBlank
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	// Relationships ----------------------------------------------------------
	Collection<Comment> comments;
	Collection<Hilo> threads;
	
	@NotNull
	@OneToMany(mappedBy="user")
	public Collection<Comment> getComments() {
		return comments;
	}
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	@NotNull
	@OneToMany(mappedBy="user")
	public Collection<Hilo> getThreads() {
		return threads;
	}
	public void setThreads(Collection<Hilo> threads) {
		this.threads = threads;
	}
	
	
	
	
}