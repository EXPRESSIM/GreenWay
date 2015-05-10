package com.stardust.express.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.stardust.express.tools.IViewContext;
import com.stardust.express.tools.ViewContext;

public class ActionExecutor extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2035562312657833982L;

	protected IViewContext context = new ViewContext(ActionContext.getContext());
	protected ActionContext actionContext = ActionContext.getContext();
}
