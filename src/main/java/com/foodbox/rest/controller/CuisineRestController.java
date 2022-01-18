package com.foodbox.rest.controller;

import java.util.List;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.Cuisine;
import com.foodbox.repository.CuisineRepository;

@RestController
public class CuisineRestController {

	@Autowired
	CuisineRepository cuisinerepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetchcuisines", method = RequestMethod.GET)
	public JSONArray fetchcuisines() {
		JSONArray jsonArr = new JSONArray();
		JSONArray jsonArrObj = new JSONArray();
		try {
			List<Cuisine> cuisineList = cuisinerepo.fetchAll();
			if (cuisineList.size() > 0) {
				for (Cuisine cuisine : cuisineList) {
					jsonArrObj = new JSONArray();
					jsonArrObj.add(cuisine.getCuisinename());
					jsonArrObj.add(cuisine.getCuisineid());
					jsonArr.add(jsonArrObj);
				}
				return jsonArr;
			} else {
				jsonArr.add(jsonArrObj);
				return jsonArr;
			}

		} catch (Exception e) {
			logger.info("Error occured at CuisineController - fetchcuisines() : " + e.getMessage());
			return jsonArr;
		}

	}
}
