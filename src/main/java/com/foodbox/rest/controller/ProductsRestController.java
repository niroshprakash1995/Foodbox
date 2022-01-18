package com.foodbox.rest.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entity.Products;
import com.foodbox.repository.CuisineRepository;
import com.foodbox.repository.ProductsRepository;

@RestController
public class ProductsRestController {

	@Autowired
	ProductsRepository productsRepo;

	@Autowired
	CuisineRepository cuisineRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetchdata", method = RequestMethod.GET)
	public JSONArray fetchJsonData() throws IOException {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<Products> productsList = productsRepo.fetchAll();
			for (Products product : productsList) {
				jsonObj = new JSONObject();
				jsonObj.put("_id", product.getProductid());
				jsonObj.put("product_stock", product.getStock());
				jsonObj.put("product_price", product.getProductprice());
				jsonObj.put("product_name", product.getProductname());
				String productImageURI = "./images/" + product.getImagename();
				jsonObj.put("product_image_md", productImageURI);
				jsonObj.put("product_image_lg", productImageURI);
				jsonObj.put("product_image_sm", productImageURI);
				jsonObj.put("product_description", product.getDescription());
				jsonArr.add(jsonObj);
			}
			return jsonArr;
		} catch (Exception e) {
			logger.info("Exception occured at ProductsController - fetchJsonData() : " + e.getMessage());
			return jsonArr;
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetchcuisinefoods", method = RequestMethod.POST)
	public JSONArray fetchJsonFoodData(@RequestParam("cuisineName") String cuisineName) throws IOException {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		String cuisineId = cuisineRepo.fetchCuisineId(cuisineName);
		if (cuisineId != "") {
			try {
				List<Products> productsList = productsRepo.fetchCuisineFoods(cuisineId);
				for (Products product : productsList) {
					jsonObj = new JSONObject();
					jsonObj.put("_id", product.getProductid());
					jsonObj.put("product_stock", product.getStock());
					jsonObj.put("product_price", product.getProductprice());
					jsonObj.put("product_name", product.getProductname());
					String productImageURI = "./images/" + product.getImagename();
					jsonObj.put("product_image_md", productImageURI);
					jsonObj.put("product_image_lg", productImageURI);
					jsonObj.put("product_image_sm", productImageURI);
					jsonObj.put("product_description", product.getDescription());
					jsonArr.add(jsonObj);
				}
				return jsonArr;
			} catch (Exception e) {
				logger.info("Exception occured at ProductsController - fetchJsonFoodData() : " + e.getMessage());
				return jsonArr;
			}
		} else {
			return jsonArr;
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchfoods", method = RequestMethod.POST)
	public JSONArray fetchFoods(@RequestParam("searchItem") String searchItem) {
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		try {
			List<Products> productsList = productsRepo.fetchFoodItems(searchItem);
			for (Products product : productsList) {
				jsonObj = new JSONObject();
				jsonObj.put("_id", product.getProductid());
				jsonObj.put("product_stock", product.getStock());
				jsonObj.put("product_price", product.getProductprice());
				jsonObj.put("product_name", product.getProductname());
				String productImageURI = "./images/" + product.getImagename();
				jsonObj.put("product_image_md", productImageURI);
				jsonObj.put("product_image_lg", productImageURI);
				jsonObj.put("product_image_sm", productImageURI);
				jsonObj.put("product_description", product.getDescription());
				jsonArr.add(jsonObj);
			}
			return jsonArr;
		} catch (Exception e) {
			logger.info("Exception occured at ProductsController - fetchJsonData() : " + e.getMessage());
			return jsonArr;
		}

	}

}
