package com.stardust.express.actions;

import java.util.ArrayList;

import com.stardust.express.bo.AdminBOFactory;
import com.stardust.express.bo.IAdminBO;
import com.stardust.express.models.DataModel;


public class MaintenanceAction extends  ActionExecutor {
	private static final long serialVersionUID = -2191774577797836729L;
	private DataModel model;
	
	public DataModel getModel() {
		return model;
	}
	
	public String read() {
		IAdminBO bo = AdminBOFactory.create(context);
		String key = context.getString("key");
		model = bo.get(key); 
		return SUCCESS;
	}
	
	public String delete() {
		IAdminBO bo = AdminBOFactory.create(context);
		bo.remove();
		return SUCCESS;
	}
	
	public String update() {
		IAdminBO bo = AdminBOFactory.create(context);
		model = bo.update();
		return SUCCESS;
	}
}
