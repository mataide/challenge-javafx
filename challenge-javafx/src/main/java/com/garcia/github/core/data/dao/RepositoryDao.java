package com.garcia.github.core.data.dao;

import java.util.List;

import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.data.entities.User;

/**
 * [Interface] Manage DB data for Github entities
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
public interface RepositoryDao {

	public void add(Repo repository);
	public List<Repo> listAllReposBy(int page);
	public Repo findRepoBy(String name);
	public List<User> listMissingUserName();
	public User findUserBy(String login);
	public void updateUser(User user);
	public void updateRepo(Repo repository);
	
}
