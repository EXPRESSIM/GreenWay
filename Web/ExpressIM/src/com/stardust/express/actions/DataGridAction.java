package com.stardust.express.actions;

import java.util.ArrayList;
import java.util.List;

import com.stardust.express.models.DataModel;

public class DataGridAction extends ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5054795442024297138L;
	
	private List<DataModel> rows = new ArrayList<DataModel>();
	
	public String show() {
		return SUCCESS;
	}
}
