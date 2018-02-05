package com.garcia.github.core.data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.garcia.github.core.data.entities.Repo;
import com.garcia.github.core.data.entities.User;
import com.google.common.collect.Lists;

/**
 * Manage DB data for Github entities
 * 
 * @author Luis Garcia
 * @version 1.0
 * @since 21/01/2018
 *
 */
@Repository
public class RepositoryDaoImpl implements RepositoryDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void add(Repo repository) {
		em.merge(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Repo> listAllReposBy(int page) {
		try {
			return em.createNamedQuery(Repo.LIST_ALL, Repo.class)
					.setParameter("page", page)
					.getResultList();
		} catch (NoResultException e) {
			return Lists.newArrayList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Repo findRepoBy(String name) {
		try {
			return em.createNamedQuery(Repo.BY_NAME, Repo.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listMissingUserName() {
		try {
			return em.createNamedQuery(User.BY_MISSING_NAME, User.class)
					.getResultList();
		} catch (NoResultException e) {
			return Lists.newArrayList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserBy(String login) {
		try {
			return em.createNamedQuery(User.BY_LOGIN, User.class)
					.setParameter("login", login)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		em.createNamedQuery(User.UPDATE_BY_NAME)
			.setParameter("name", user.getName())
			.setParameter("login", user.getLogin())
			.executeUpdate();
	}
	
	@Override
	@Transactional
	public void updateRepo(Repo repository) {
		em.merge(repository);
	}

}
