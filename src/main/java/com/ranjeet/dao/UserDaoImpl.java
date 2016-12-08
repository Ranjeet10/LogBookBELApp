/**
 * 
 */
package com.ranjeet.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.ranjeet.model.AdminUser;

@Repository
public class UserDaoImpl {
	
	@Autowired
	private HibernateTemplate template;

	@SuppressWarnings({ "unchecked" })
	@Transactional(propagation = Propagation.SUPPORTS)
	public AdminUser findUserByEmailAndPas(String email, String password) {
		List<AdminUser> users = (List<AdminUser>) template.findByCriteria(DetachedCriteria.forClass(AdminUser.class)
				.add(Restrictions.eq("username", email))
				.add(Restrictions.eq("password", password)));
		if(!CollectionUtils.isEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

}
