package com.foodbox.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	private String userid;
	private String fname;
	private String lname;
	private String userpwd;

	public Users() {
		super();
	}

	public Users(String userid, String fname, String lname, String userpwd) {
		super();
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.userpwd = userpwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", fname=" + fname + ", lname=" + lname + ", userpwd=" + userpwd + "]";
	}

}
