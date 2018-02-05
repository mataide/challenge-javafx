package com.garcia.github.core.data.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Class responsible for keeping Pull Request
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Entity
public class PullRequest extends BaseEntity<Long> {

	/** Serial version */
	private static final long serialVersionUID = 1L;
	
	// Attributes
	private String title;
	private String description;
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "authorId", unique = false, nullable = false, updatable = false)
	private User author;
	private boolean open;
	
	// Constructors
	public PullRequest() { super(); }
	public PullRequest(String title, String description, User author, boolean open) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.open = open;
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	// Methods
	@Override
	public String toString() {
		return "PullRequest [id=" + super.getId() + ", title=" + title + ", description=" + description + ", author=" + author
				+ ", open=" + open + "]";
	}
	
}
