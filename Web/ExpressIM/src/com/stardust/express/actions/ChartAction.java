package com.stardust.express.actions;

import com.stardust.express.bo.AdminBOFactory;
import com.stardust.express.bo.ChartBO;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartAction extends ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String run_hour(){
		if (!isValidSession()) return ERROR;
		return null;
		
	}
	
	public String run_month(){
		if (!isValidSession()) return ERROR;
		ChartBO bo = (ChartBO) AdminBOFactory.create(context);
		return null;
		
	}
	
	public String run_year(){
		if (!isValidSession()) return ERROR;
		return null;
		
	}



}
