package com.stardust.express.actions;

import com.stardust.express.bo.SecurityBO;
import com.stardust.express.models.User;


public class LogonAction extends  ActionExecutor {
	private static final long serialVersionUID = -2191774577797836729L;
	private String INCORRECT_USER = "incorrect_user";
	private String INVALID_USER_STATUS = "invalid_user_status";
	
	public String logon() {
		SecurityBO bo = new SecurityBO(context);
		String username = context.getString("username");
		String password = context.getString("password");
		User user = bo.verifyUser(username, password);
		if (user != null && user.getId() > 0) {
			if (!user.getStatus().equals(User.STATUS_ACTIVE)) {
				return INVALID_USER_STATUS;
			} else {
				context.getSession().put("logon_user", user);
				return SUCCESS;
			}
		} else {
			return INCORRECT_USER;
		}
	}
	
	public String logoff(){
		context.getSession().remove("logon_user");
		return SUCCESS;
	}
	
}
