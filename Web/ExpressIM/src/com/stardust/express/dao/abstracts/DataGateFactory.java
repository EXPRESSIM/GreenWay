package com.stardust.express.dao.abstracts;

import com.stardust.express.dao.implementations.UserGate;

public  class DataGateFactory {
	public static IUserGate getUserGate(String datasource) {
		return new UserGate(datasource);
	}
}
