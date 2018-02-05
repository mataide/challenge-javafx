package com.garcia.github.core.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.google.common.collect.Lists;

/**
 * Class responsible for keeping Repository information
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Repo.LIST_ALL, query = "select r from Repo r where r.page = :page"),
	@NamedQuery(name = Repo.BY_NAME, query = "select r from Repo r where r.name = :name")
})
public class Repo extends BaseEntity<Long> {

	/** Serial version */
	private static final long serialVersionUID = 1L;
	
	// Constants
	public static final String LIST_ALL = "Repo.listAll";
	public static final String BY_NAME = "Repo.byName";
	
	// Attributes
	private String name;
	private String description;
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "ownerId", unique = false, nullable = false, updatable = false)
	private User owner;
	private int forks;
	private int stars;
	private int page;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "pullRequestId", unique = false, nullable = false, updatable = false)
	private List<PullRequest> pullrequests = Lists.newArrayList();
	
	// Constructors
	public Repo() { super(); }
	public Repo(String name, String description, User owner, int forks, int stars, int page, List<PullRequest> pullrequests) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.forks = forks;
		this.stars = stars;
		this.page = page;
		this.pullrequests = pullrequests;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getForks() {
		return forks;
	}
	public void setForks(int forks) {
		this.forks = forks;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<PullRequest> getPullrequests() {
		return pullrequests;
	}
	public void setPullrequests(List<PullRequest> pullrequests) {
		this.pullrequests = pullrequests;
	}
	
	@Override
	public String toString() {
		return "Repo [name=" + name + ", description=" + description + ", owner=" + owner + ", forks=" + forks
				+ ", stars=" + stars + ", page=" + page + ", pullrequests=" + pullrequests + "]";
	}
	
}
