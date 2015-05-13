package com.stardust.express.actions;

import java.util.ArrayList;
import java.util.List;

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
		String summaryBy = context.getString("summaryBy");
		records = new SummaryReport().run(summaryBy, null);
		return SUCCESS;
	}

}
