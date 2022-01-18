package com.foodbox.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.foodbox.entity.Users;
import com.foodbox.repository.UsersRepository;

@Controller
public class UsersController {

	@Autowired
	UsersRepository usersRepo;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public RedirectView userLogin(@RequestParam("username") String userIdInput,
			@RequestParam("password") String userPwdInput, HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			Users userObj = usersRepo.findById(userIdInput);
			if (userObj == null) {
				return new RedirectView("invaliduser.html");
			} else {
				String userPwd = userObj.getUserpwd();
				session.setAttribute("userId", userIdInput);
				if (userPwd.equals(userPwdInput)) {
					return new RedirectView("userlanding.html");
				} else {
					return new RedirectView("loginerror.html");
				}
			}
		} catch (Exception e) {
			logger.info("Error occured at UsersController : " + e.getMessage());
			return new RedirectView("error.html");
		}
	}

	@RequestMapping(value = "/userregisteration", method = RequestMethod.POST)
	public String registerUser(@RequestParam("userid") String userid, @RequestParam("fname") String fname,
			@RequestParam("lname") String lname, @RequestParam("pwd") String pwd, Model model) throws IOException {
		try {
			Users userObj = usersRepo.findById(userid);
			if (userObj == null) {
				Users user = new Users(userid, fname, lname, pwd);
				usersRepo.addUser(user);
				model.addAttribute("successMsg", "User added successfully. Please login to continue.");
			} else {
				model.addAttribute("errorMsg",
						"User ID already exists. Please try a different ID or login to continue.");
			}
		} catch (Exception e) {
			logger.info("Error occured at UsersController : " + e.getMessage());
			model.addAttribute("errorMsg", "Something went wrong. Please try later.");
		}
		return "foodboxresults";
	}
}
