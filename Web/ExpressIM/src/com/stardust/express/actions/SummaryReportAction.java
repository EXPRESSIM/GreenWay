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
	
	private List<SummaryRecord> records = new ArrayList<SummaryRecord>();
	
	public List<SummaryRecord> getRecords(){
		return this.records;
	}
		
	public String fetch(){
		String summaryBy = context.getString("summaryBy","day");
		Date startDate = context.getDate("startDate", null);
		Date endDate = context.getDate("endDate", null);
		List<Selection> selections = new ArrayList<Selection>();
		if (startDate != null) {
			selections.add(new Selection("RECORD_DATE", Operator.GEATER_EQUAL, startDate, "startdate"));
		}
		
		if (endDate != null) {
			if (selections.size() > 0) {
				selections.add(new Selection("RECORD_DATE", Operator.LESS_EQUAL, endDate, Operand.AND));
			} else {
				selections.add(new Selection("RECORD_DATE", Operator.LESS_EQUAL, endDate));
			}	
		}
		records = new SummaryReport().run(summaryBy, selections);
		return SUCCESS;
	}

}
