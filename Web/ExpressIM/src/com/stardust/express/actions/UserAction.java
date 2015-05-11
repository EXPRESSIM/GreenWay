package com.stardust.express.actions;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.json.annotations.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.stardust.express.dao.abstracts.DataGateFactory;
import com.stardust.express.dao.abstracts.IHistoryRecordGate;
import com.stardust.express.dao.implementations.Selection;
import com.stardust.express.dao.implementations.UserGate;
import com.stardust.express.models.HistoryRecord;
import com.stardust.express.models.User;
import com.stardust.express.tools.IViewContext;
import com.stardust.express.tools.ViewContext;

public class UserAction extends ActionExecutor{
	
	ActionContext actionContext = ActionContext.getContext();
	
	private static final long serialVersionUID = 1359090410097337654L;
	private List<User> users = new ArrayList<User>();
	
	private long userid = 0;
	private String datasource = "";
	
	private InputStream stringResponse;
	
	public InputStream getStringResponse() { 
		return stringResponse; 
	} 
	
	public void setDatasource(String source) {
		datasource = source;
	}
	
	@JSON(serialize=false)
	public String getDatasource(){
		return datasource;
	}
	
	public void setUserId(long id) {
		userid = id;
	}
	
	@JSON(serialize=false)
	public long getUserId(){
		return userid;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public String loadHistory(){
		IHistoryRecordGate gate = DataGateFactory.getHistoryRecordGate(context.getString("datasource"));
		
		for (int i=0; i<0;i++) {
			HistoryRecord hr = new HistoryRecord();
			hr.setAmount(10);
			hr.setChannel("通道-1");
			hr.setComment("测试");
			hr.setDate(new Date());
			hr.setEntranceGateway("临潼高速收费站");
			hr.setExitGateway("宝鸡高速收费站");
			hr.setMerchandiseType("蔬菜-水果");
			hr.setVehicleType("I类");
			hr.setVehicleNumber("陕A09623");
			User operator = new User();
			operator.setId(77);
			User leader = new User();
			leader.setId(78);
			hr.setLeader(leader);
			hr.setOperator(operator);
			gate.update(hr);
		}
		
		HistoryRecord hr = (HistoryRecord)gate.find(3);
		return SUCCESS;
	}
	
	public String loadUser() {
		IViewContext context = new ViewContext(actionContext);
		String ds = StringEscapeUtils.escapeHtml4(context.getString("datasource"));
		int userid = context.getInt("userid");
		
        User user = new User();
        user.setUsername("rivneg");
        user.setPassword("8forxiao");
        //user.setId(getUserId());
        user.setRoleId(1);
        user.setName("Alex Li");
        users.add(user);
        
        UserGate gate = new UserGate(ds);
        gate.add(user);
        User u = (User)gate.find(4);
        users.add(u);
        List<Selection> selections = new ArrayList<Selection>();
        selections.add(new Selection("id", Selection.Operator.GREATER, 20));
        int a = gate.count(selections);
        //stringResponse = new StringBufferInputStream("Permission Denied");
        //return "permission_denied";
        return SUCCESS;
    }
}
