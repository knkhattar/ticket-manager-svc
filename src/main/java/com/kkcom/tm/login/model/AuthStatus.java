package com.kkcom.tm.login.model;

import org.springframework.stereotype.Component;

@Component
public class AuthStatus {
	@Override
	public String toString() {
		return "AuthStatus [authenticated=" + authenticated + ", errMsg="
				+ errMsg + ", opStatus=" + opStatus + "]";
	}

	boolean authenticated;

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	String errMsg;
	String opStatus;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}
}
