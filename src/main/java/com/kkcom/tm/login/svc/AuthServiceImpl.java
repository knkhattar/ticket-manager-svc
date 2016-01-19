package com.kkcom.tm.login.svc;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kkcom.tm.login.model.AuthStatus;
import com.kkcom.tm.login.model.Users;

@Service
public class AuthServiceImpl implements AuthService {

	static Logger logger = Logger.getLogger(AuthServiceImpl.class);
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		AuthServiceImpl.entityManagerFactory = entityManagerFactory;
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
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = null;
		List<Users> users = null;
		try {
			transaction = manager.getTransaction();
			transaction.begin();


			String hql = "from Users where name = :userName and password = :password";
			Query query = manager.createQuery(hql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			users = query.getResultList();
			if (users.size() >= 1) {
				isAuthenticated = true;
			}

			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAuthenticated;
	}

}
