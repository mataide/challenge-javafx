package com.garcia.github.core.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garcia.github.core.data.dao.RepositoryDao;
import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.data.entities.User;

/**
 * Class responsible for access DAO layer
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Service
public class RepositoryBusiness {

	@Autowired
	private RepositoryDao dao;

	public void add(Repo repository) {
		dao.add(repository);
	}

	public List<Repo> listBy(int page) {
		return dao.listAllReposBy(page);
	}
	
	public Repo findByName(String name) {
		return dao.findRepoBy(name);
	}

	public List<User> listMissingUserName() {
		return dao.listMissingUserName();
	}
	
	public User findUserBy(String login) {
		return dao.findUserBy(login);
	}
	
	public void findAndUpdateUserBy(String login, String name) {
		User user = this.findUserBy(login);
		if (user != null) {
			user.setName(name);
			dao.updateUser(user);
		}
	}
	
	public void update(Repo repository) {
		dao.updateRepo(repository);
	}

}
