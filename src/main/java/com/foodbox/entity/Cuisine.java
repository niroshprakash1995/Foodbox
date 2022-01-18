package com.foodbox.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cuisine {

	@Id
	private String cuisineid;
	private String cuisinename;

	public Cuisine() {
		super();
	}

	public Cuisine(String cuisineid, String cuisinename) {
		super();
		this.cuisineid = cuisineid;
		this.cuisinename = cuisinename;
	}

	public String getCuisineid() {
		return cuisineid;
	}

	public void setCuisineid(String cuisineid) {
		this.cuisineid = cuisineid;
	}

	public String getCuisinename() {
		return cuisinename;
	}

	public void setCuisinename(String cuisinename) {
		this.cuisinename = cuisinename;
	}

	@Override
	public String toString() {
		return "Cuisine [cuisineid=" + cuisineid + ", cuisinename=" + cuisinename + "]";
	}

}
