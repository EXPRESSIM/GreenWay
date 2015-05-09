package com.stardust.express.dao.implementations;

import com.stardust.express.dao.abstracts.IUserGate;
import com.stardust.express.models.User;

public class UserGate extends DataGate implements IUserGate{

	public UserGate(){
		super();
	}
	
	public UserGate(String datasource) {
		super(datasource);
	}
	
	@Override
	protected Class<?> getModelClass() {
		return User.class;
	}

	@Override
	protected String getModelName() {
		return "User";
	}

}
