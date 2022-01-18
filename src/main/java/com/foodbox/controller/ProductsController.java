package com.foodbox.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.foodbox.entity.Cuisine;
import com.foodbox.entity.Products;
import com.foodbox.repository.CuisineRepository;
import com.foodbox.repository.ProductsRepository;

@Controller
public class ProductsController {

	@Autowired
	ProductsRepository productsRepo;

	@Autowired
	CuisineRepository cuisineRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/editfooditem", method = RequestMethod.POST)
	public String editFoodItem(@RequestParam("food_id") int productid,
			@RequestParam(value = "food_name", required = false, defaultValue = "") String productname,
			@RequestParam(value = "food_price", required = false, defaultValue = "0") int productprice,
			@RequestParam(value = "food_cuisineid", required = false, defaultValue = "") String cuisineid,
			@RequestParam(value = "food_description", required = false, defaultValue = "") String description,
			Model model) {
		try {
			if (productsRepo.foodIdExists(productid)) {
				if (cuisineid.equals("")) {
					productsRepo.updateFoodItemDetails(productid, productname, productprice, cuisineid, description);
					model.addAttribute("successMsg", "Food item details updated successfully.");
				} else {
					if (cuisineRepo.cuisineIdExists(cuisineid)) {
						productsRepo.updateFoodItemDetails(productid, productname, productprice, cuisineid,
								description);
						model.addAttribute("successMsg", "Food item details updated successfully.");
					} else {
						model.addAttribute("errorMsg",
								"Entered cuisine ID does not exist in the database. Please try again.");
					}
				}
			} else {
				model.addAttribute("errorMsg", "Entered food ID does not exist. Please try again.");
			}

		} catch (Exception e) {
			logger.info("Exception occured at ProductsController - editFoodItem() :" + e.getMessage());
			model.addAttribute("errorMsg", "Something went wrong. Please try again.");
		}
		return "foodboxresults";
	}

	@RequestMapping(value = "/addfooditem", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public String addFoodItem(@RequestParam("food_id") int productid, @RequestParam("food_name") String productname,
			@RequestParam("food_image") MultipartFile multipartFile, @RequestParam("food_price") int productprice,
			@RequestParam("food_stock") int stock, @RequestParam("food_cuisineid") String cuisineid,
			@RequestParam("food_description") String description, Model model) throws IOException {
		try {
			if (productsRepo.foodIdExists(productid)) {
				model.addAttribute("errorMsg", "Food product with same ID already exists. Please try again.");
				return "foodboxresults";
			}
			if (!cuisineRepo.cuisineIdExists(cuisineid)) {
				model.addAttribute("errorMsg", "Cuisine ID does not exist in the database. Please try again.");
				return "foodboxresults";
			}
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			// Check if product with same image name already exists
			File f = new File("src/main/resources/static/images/" + fileName);
			if (f.exists() && !f.isDirectory()) {
				model.addAttribute("errorMsg", "Food product with same image name already exists. Please try again.");
				return "foodboxresults";
			}
			// Create image file
			File imageFile = new File("src/main/resources/static/images/" + fileName);
			imageFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(imageFile);
			fout.write(multipartFile.getBytes());
			fout.close();

			Cuisine cuisine = cuisineRepo.findById(cuisineid);
			Products product = new Products(productid, productname, fileName, productprice, stock, cuisine,
					description);
			productsRepo.addProduct(product);
			model.addAttribute("successMsg", "Food product added successfully.");
			return "foodboxresults";

		} catch (Exception e) {
			model.addAttribute("errorMsg", "Something went wrong. Please try later.");
			return "foodboxresults";
		}
	}

	@RequestMapping(value = "/removefooditem", method = RequestMethod.POST)
	public String removeFoodItem(@RequestParam("food_id") int productid, Model model) {
		try {
			if (productsRepo.foodIdExists(productid)) {

				// Delete image
				Products product = productsRepo.findById(productid);
				String imageName = product.getImagename();
				File imageFile = new File("src/main/resources/static/images/" + imageName);
				imageFile.delete();

				int rowDeleted = productsRepo.deleteProduct(productid);
				if (rowDeleted == 1) {
					model.addAttribute("successMsg", "Food product successfully deleted.");
					return "foodboxresults";
				} else {
					model.addAttribute("errorMsg", "Could not delete food product. Please try later.");
					return "foodboxresults";
				}
			} else {
				model.addAttribute("errorMsg", "Food product does not exist with this ID. Please try again.");
				return "foodboxresults";
			}
		} catch (Exception e) {
			logger.info("Exception occured at ProductsNonRestController - removeFoodItem() :" + e.getMessage());
			model.addAttribute("errorMsg", "Something went wrong. Please try later.");
			return "foodboxresults";
		}
	}

}
