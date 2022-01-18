package com.foodbox.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodbox.entity.Cuisine;
import com.foodbox.entity.Products;
import com.foodbox.repository.CuisineRepository;
import com.foodbox.repository.ProductsRepository;

@Controller
public class CuisineController {

	@Autowired
	CuisineRepository cuisinerepo;

	@Autowired
	ProductsRepository productsrepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/addcuisine", method = RequestMethod.POST)
	public String addNewCuisine(@RequestParam("cuisine_id") String cuisine_id,
			@RequestParam("cuisine_name") String cuisine_name, Model model) {
		try {
			Cuisine cuisine = cuisinerepo.findById(cuisine_id);
			if (cuisine == null) {
				cuisinerepo.insertCuisine(cuisine_id, cuisine_name);
				model.addAttribute("successMsg", "Cuisine successfully added.");
			} else {
				model.addAttribute("errorMsg", "Cuisine with same ID already exists. Please try again.");
			}
		} catch (Exception e) {
			logger.info("Error occured at addNewCuisine() :" + e.getMessage());
			model.addAttribute("errorMsg", "Something went wrong while adding the cuisine. Please try later.");
		}
		return "foodboxresults";
	}

	@RequestMapping(value = "/removecuisine", method = RequestMethod.POST)
	public String removeCuisine(@RequestParam("rm_cuisine_id") String rm_cuisine_id,
			@RequestParam("rm_cuisine_name") String rm_cuisine_name, Model model) {
		try {
			Cuisine cuisine = cuisinerepo.findById(rm_cuisine_id);
			if (cuisine != null) {
				List<Products> cuisineProducts = productsrepo.fetchCuisineFoods(rm_cuisine_id);
				if (cuisineProducts.size() > 0) {
					model.addAttribute("errorMsg",
							"There are food products against this cuisine ID. Please delete the products before deleting the corresponding cuisine.");
					return "foodboxresults";
				}
			}
			if (cuisine != null && cuisine.getCuisineid().equals(rm_cuisine_id)
					&& cuisine.getCuisinename().equals(rm_cuisine_name)) {
				int rowsDeleted = cuisinerepo.removeCuisine(rm_cuisine_id);
				if (rowsDeleted == 1) {
					model.addAttribute("successMsg", "Cuisine successfully removed.");
				} else {
					model.addAttribute("errorMsg", "Cuisine could not be removed. Please try later.");
				}
			} else {
				model.addAttribute("errorMsg", "Cuisine with specified ID and Name not found. Please try again.");
			}
		} catch (Exception e) {
			logger.info("Error occured at removeCuisine() :" + e.getMessage());
			model.addAttribute("errorMsg", "Something went wrong while removing the cuisine. Please try later.");
		}
		return "foodboxresults";
	}

}
