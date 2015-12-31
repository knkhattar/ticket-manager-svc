package com.kkcom.tm.login.svc;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.kkcom.tm.login.model.AuthStatus;
import com.kkcom.tm.login.model.Users;

@Service
public class AuthServiceImpl implements AuthService {

	static Logger logger = Logger.getLogger(AuthServiceImpl.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AuthStatus authenticate(String username, String password) {
		logger.info("Inside authenticate method");

		AuthStatus authStatus = new AuthStatus();
		authStatus.setAuthenticated(checkDbForAuth(username, password));

		logger.info("Exit authenticate method, auth status::" + authStatus);
		return authStatus;
	}

	@SuppressWarnings("unchecked")
	private boolean checkDbForAuth(String userName, String password) {
		boolean isAuthenticated = false;
		Session session = null;
		try {
			session = getSessionFactory().openSession();
			if (session == null) {
				System.out.println("dataSource is null ::");
			}

			String hql = "from Users where name = :userName and password = :password";
			Query query = session.createQuery(hql);
			query.setString("userName", userName);
			query.setString("password", password);
			List<Users> result = query.list();
			if (result.size() >= 1) {
				isAuthenticated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAuthenticated;
	}

}
