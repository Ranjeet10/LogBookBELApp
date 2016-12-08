/**
 * 
 */
package com.ranjeet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.ranjeet.model.AdminUser;
import com.ranjeet.service.DeliveryManagementService;
import com.ranjeet.service.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author M1028103
 *
 */
@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private DeliveryManagementService deliveryManagementService;
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") AdminUser user,Model model, HttpServletRequest request) {
		AdminUser validUser = userService.checkValidUser(user);
		if(validUser == null) {
			model.addAttribute("loginMsg", "Login UnSuccessful");
			return "redirect:../login";
		} else {
			model.addAttribute("loggedInUser", validUser);
			//model.addAttribute("loginMsg", "Login Successful :)");
			HttpSession session = request.getSession();
			session.setAttribute("User", user.getUsername());
			return "redirect:../showAdminPage";
		}
		
	}
	
//	@RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
//	public String updatePassword(@ModelAttribute("user") AdminUser user,Model model) {
//		AdminUser validUser = userService.checkValidUser(user);
//		if(validUser == null) {
//			//model.addAttribute("loginMsg", "Login UnSuccessful :(");
//			return "redirect:../login";
//		} else {
//			model.addAttribute("loggedInUser", validUser);
//			//model.addAttribute("loginMsg", "Login Successful :)");
//			return "redirect:../updatePassword";
//		}
//		
//	}

}
