package com.foodbox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodbox.entity.Cuisine;

@Repository
public class CuisineRepository {

	@Autowired
	EntityManager em;

	@Transactional
	public void insertCuisine(String cuisine_id, String cuisine_name) {
		Cuisine cuisine = new Cuisine(cuisine_id, cuisine_name);
		em.persist(cuisine);
	}

	public Cuisine findById(String cuisine_id) {
		return em.find(Cuisine.class, cuisine_id);
	}

	@Transactional
	public int removeCuisine(String cuisine_id) {
		int rowsDeleted = em.createQuery("Delete from Cuisine c where c.cuisineid = '" + cuisine_id + "'")
				.executeUpdate();
		return rowsDeleted;
	}

	@Transactional
	public List<Cuisine> fetchAll() {
		Query query = em.createQuery("SELECT c from Cuisine c");
		@SuppressWarnings("unchecked")
		List<Cuisine> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public String fetchCuisineId(String cuisineName) {
		Cuisine cuisine = new Cuisine();
		try {
			cuisine = (Cuisine) em.createQuery("SELECT c from Cuisine c where c.cuisinename = :value")
					.setParameter("value", cuisineName).getSingleResult();
			return cuisine.getCuisineid();
		} catch (NoResultException e) {
			return "";
		}
	}
	
	public boolean cuisineIdExists(String cuisineid) {
		Cuisine cuisine = em.find(Cuisine.class, cuisineid);
		if(cuisine != null) {
			return true;
		}else {
			return false;
		}
	
	}
}
