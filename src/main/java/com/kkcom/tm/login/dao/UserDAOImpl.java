package com.kkcom.tm.login.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.kkcom.tm.login.model.Users;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Users> getUsers(String userName, String password) {

		boolean isAuthenticated = false;
		Session session = null;
		List<Users> userList1 = null;
		try {
			session = getSessionFactory().openSession();
			if (session == null) {
				System.out.println("dataSource is null ::");
			}

			String hql = "from Users where name = :userName and password = :password";
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			query.setString("password", password);
			@SuppressWarnings("unchecked")
			List<Users> userList = query.list();
			userList1 = userList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList1;
	};
}
