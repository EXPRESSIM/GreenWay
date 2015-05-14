package com.stardust.express.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stardust.express.dao.implementations.Selection;
import com.stardust.express.dao.implementations.Selection.Operand;
import com.stardust.express.dao.implementations.Selection.Operator;
import com.stardust.express.models.SummaryRecord;
import com.stardust.express.reporting.SummaryReport;

public class SummaryReportAction extends ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4014745096879618188L;
	
	private List<SummaryRecord> rows = new ArrayList<SummaryRecord>();
	
	private int total = 0;
	
	public List<SummaryRecord> getRows(){
		return this.rows;
	}
		
	public int getTotal(){
		return total;
	}
	
	public String fetch(){
		String summaryBy = context.getString("summaryBy","day");
		Date startDate = context.getDate("startDate", null);
		Date endDate = context.getDate("endDate", null);
		List<Selection> selections = new ArrayList<Selection>();
		if (startDate != null) {
			selections.add(new Selection("REOCRD_DATE", Operator.GEATER_EQUAL, startDate, "startdate"));
		}
		
		if (endDate != null) {
			if (selections.size() > 0) {
				selections.add(new Selection("REOCRD_DATE", Operator.LESS_EQUAL, endDate, Operand.AND));
			} else {
				selections.add(new Selection("REOCRD_DATE", Operator.LESS_EQUAL, endDate));
			}	
		}
		rows = new SummaryReport().run(summaryBy, selections);
		total = rows.size();
		return SUCCESS;
	}

}
