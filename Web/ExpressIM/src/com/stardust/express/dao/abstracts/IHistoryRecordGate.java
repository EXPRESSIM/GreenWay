package com.stardust.express.dao.abstracts;

import java.util.List;
import java.util.Date;

public interface IHistoryRecordGate extends IDataGate {
	public void cleanUp(String endDate);
	
	public enum PeriodSummaryType {
		BY_MONTH, BY_YEAR, BY_DAY;
	}

	public List getPeriodSummaryData(Date startDate, Date endDate, PeriodSummaryType summaryType);
}
