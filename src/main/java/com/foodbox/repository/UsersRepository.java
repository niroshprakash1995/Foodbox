package com.foodbox.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodbox.entity.Users;

@Repository
public class UsersRepository {

	@Autowired
	EntityManager em;

	public Users findById(String username) {
		return em.find(Users.class, username);
	}

	@Transactional
	public void addUser(Users user) {
		em.persist(user);
	}
}
