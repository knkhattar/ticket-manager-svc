package com.kkcom.tm.login.svc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkcom.tm.login.model.AuthStatus;

@Service
public class AuthServiceImpl implements AuthService {

	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public AuthStatus authenticate(String username, String password) {

		AuthStatus authStatus = new AuthStatus();
		authStatus.setAuthenticated(checkDbForAuth(username, password));

		return authStatus;
	}

	private boolean checkDbForAuth(String userName, String password) {
		boolean isAuthenticated = false;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;

		try {

			if (dataSource == null) {
				System.out.println("dataSource is null ::");
			}

			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from users where name='"
					+ userName + "' and password='" + password + "'");

			if (rs.next()) {
				isAuthenticated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isAuthenticated;
	}

}
