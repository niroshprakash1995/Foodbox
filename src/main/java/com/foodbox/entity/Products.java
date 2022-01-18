package com.foodbox.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Products {

	@Id
	private int productid;
	private String productname;
	private String imagename;
	private int productprice;
	private int stock;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuisineid")
	private Cuisine cuisineid;
	private String description;

	public Products() {
		super();
	}

	public Products(int productid, String productname, String imagename, int productprice, int stock, Cuisine cuisineid,
			String description) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.imagename = imagename;
		this.productprice = productprice;
		this.stock = stock;
		this.cuisineid = cuisineid;
		this.description = description;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public int getProductprice() {
		return productprice;
	}

	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Cuisine getCuisineid() {
		return cuisineid;
	}

	public void setCuisineid(Cuisine cuisineid) {
		this.cuisineid = cuisineid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
