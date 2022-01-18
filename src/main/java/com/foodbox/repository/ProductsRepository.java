package com.foodbox.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodbox.entity.Cuisine;
import com.foodbox.entity.Products;

@Repository
@Transactional
public class ProductsRepository {

	@Autowired
	EntityManager em;

	@Transactional
	public List<Products> fetchAll() {
		Query query = em.createQuery("SELECT p FROM Products p");
		@SuppressWarnings("unchecked")
		List<Products> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public List<Products> fetchCuisineFoods(String cuisineId) {
		Query query = em.createQuery("SELECT p FROM Products p WHERE p.cuisineid is '" + cuisineId + "'");
		@SuppressWarnings("unchecked")
		List<Products> resultList = query.getResultList();
		return resultList;
	}

	@Transactional
	public List<Products> fetchFoodItems(String productname) {
		Query query = em.createQuery("SELECT p from Products p where upper(p.productname) like '" + '%'
				+ productname.toUpperCase() + '%' + "'");
		@SuppressWarnings("unchecked")
		List<Products> resultList = query.getResultList();
		return resultList;
	}

	public boolean foodIdExists(int productid) {
		Products product = em.find(Products.class, productid);
		if (product != null) {
			return true;
		} else {
			return false;
		}
	}

	public Products findById(int productid) {
		Products product = em.find(Products.class, productid);
		return product;
	}

	public void updateFoodItemDetails(int productid, String productname, int productprice, String cuisineid,
			String description) {
		Products product = em.find(Products.class, productid);
		Cuisine cuisine = em.find(Cuisine.class, cuisineid);
		if (!productname.equals("")) {
			product.setProductname(productname);
		}
		if (productprice != 0) {
			product.setProductprice(productprice);
		}
		if (!cuisineid.equals("")) {
			product.setCuisineid(cuisine);
		}
		if (!description.equals("")) {
			product.setDescription(description);
		}
		em.merge(product);
	}

	public void addProduct(Products product) {
		if (product != null) {
			em.persist(product);
		}
	}

	public int deleteProduct(int productid) {
		Query query = em.createQuery("DELETE from Products p where p.productid is '" + productid + "'");
		int rowDeleted = query.executeUpdate();
		return rowDeleted;
	}
}
