package com.stardust.express.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import com.stardust.express.bo.HistoryRecordBO;
import com.stardust.express.dao.implementations.Selection;
import com.stardust.express.dao.implementations.Selection.Operand;
import com.stardust.express.dao.implementations.Selection.Operator;
import com.stardust.express.models.DataModel;

public class HistoryAction extends ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5054795442024297138L;
	
	private List<DataModel> rows;
	
	private int total = 0;
	
	public List<DataModel> getRows() {
		return this.rows;
	}
	
	public int getTotal(){
		return total;
	}
	
	public String fetch() {
		HistoryRecordBO bo = new HistoryRecordBO(context);
		int pageSize = context.getInt("rows");
		int start = (context.getInt("page")-1) * pageSize;
		String sortBy = context.getString("sortBy");
		Date startDate = context.getDate("startDate", null);
		Date endDate = context.getDate("endDate", null);
		String vehicleNumber = context.getString("vehicleNumber");
		String vehicleType = context.getString("vehicleType");
		String channel = context.getString("channel");
		String channelType = context.getString("channelType");
		
		List<Selection> selections = new ArrayList<Selection>();
		if (startDate != null) {
			selections.add(new Selection("date", Operator.GEATER_EQUAL, startDate, "startdate"));
		}
		
		if (endDate != null) {
			if (selections.size() > 0) {
				selections.add(new Selection("date", Operator.LESS_EQUAL, endDate, Operand.AND));
			} else {
				selections.add(new Selection("date", Operator.LESS_EQUAL, endDate));
			}	
		}
		
		if (vehicleNumber != null && !vehicleNumber.isEmpty()) {
			if (selections.size() > 0) {
				selections.add(new Selection("vehicleNumber", Operator.EQUAL, vehicleNumber, Operand.AND));
			} else {
				selections.add(new Selection("vehicleNumber", Operator.EQUAL, vehicleNumber));
			}	
		}
		
		if (vehicleType != null && !vehicleType.isEmpty()) {
			if (selections.size() > 0) {
				selections.add(new Selection("vehicleType", Operator.EQUAL, vehicleType, Operand.AND));
			} else {
				selections.add(new Selection("vehicleType", Operator.EQUAL, vehicleType));
			}	
		}
		
		if (channel != null && !channel.isEmpty()) {
			if (selections.size() > 0) {
				selections.add(new Selection("channel", Operator.EQUAL, channel, Operand.AND));
			} else {
				selections.add(new Selection("channel", Operator.EQUAL, channel));
			}	
		}
		
		if (channelType != null && !channelType.isEmpty()) {
			if (selections.size() > 0) {
				selections.add(new Selection("channelType", Operator.EQUAL, channelType, Operand.AND));
			} else {
				selections.add(new Selection("channelType", Operator.EQUAL, channelType));
			}	
		}
		
		
		rows = bo.filter(selections, sortBy, pageSize, start);
		total = bo.count(selections);
		if (rows == null) {
			rows = new ArrayList<DataModel>();
		}
		if (total < 0) {
			total = 0;
		}
		return SUCCESS;
	}
}
