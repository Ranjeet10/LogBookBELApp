/**
 * 
 */
package com.ranjeet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranjeet.dao.UserDaoImpl;
import com.ranjeet.model.AdminUser;

/**
 * @author M1028103
 *
 */
@Service
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDao;

	public AdminUser checkValidUser(AdminUser user) {
		AdminUser validUser = userDao.findUserByEmailAndPas(user.getUsername(), user.getPassword());
		if(validUser != null){
			return validUser;
		}
		return null;
	}

}
