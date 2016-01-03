package com.kkcom.tm.login.dao;

import java.util.List;

import com.kkcom.tm.login.model.Users;

public interface UserDAO {
	List<Users> getUsers(String userName, String password);
}
