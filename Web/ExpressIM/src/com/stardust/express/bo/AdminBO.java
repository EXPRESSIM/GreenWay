package com.stardust.express.bo;

import com.stardust.express.tools.IViewContext;

abstract public class AdminBO extends BusinessObject implements IAdminBO{

	public AdminBO(IViewContext context) {
		super(context);
	}
	
}
