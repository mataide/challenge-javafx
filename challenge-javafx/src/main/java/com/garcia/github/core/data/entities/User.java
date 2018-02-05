package com.garcia.github.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Class responsible for keeping GitHub User attributes
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = User.BY_MISSING_NAME, query = "select u from User u where u.name is null"),
	@NamedQuery(name = User.BY_LOGIN, query = "select u from User u where u.login = :login"),
	@NamedQuery(name = User.UPDATE_BY_NAME, query = "update User u set u.name = :name where u.login = :login")
})
public class User extends BaseEntity<Long> {

	/** Serial version */
	private static final long serialVersionUID = 1L;
	
	// Constants
	public static final String BY_MISSING_NAME = "User.byMissingName";
	public static final String BY_LOGIN = "User.byLogin";
	public static final String UPDATE_BY_NAME = "User.byName";
	
	// Attributes
	private String avatarUrl;
	private String login;
	private String name;
	
	// Constructors
	public User() { super(); }
	public User(String avatarUrl, String login, String name) {
		super();
		this.avatarUrl = avatarUrl;
		this.login = login;
		this.name = name;
	}
	
	// Getters and Setters
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Methods
	@Override
	public String toString() {
		return "User [avatarUrl=" + avatarUrl + ", login=" + login + ", name=" + name + "]";
	}
	
}
