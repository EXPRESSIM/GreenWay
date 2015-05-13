package com.stardust.express.dao.implementations;


import java.util.Calendar;
import java.util.Date;

import com.stardust.express.dao.abstracts.IHistoryRecordGate;
import com.stardust.express.models.DataModel;
import com.stardust.express.models.HistoryRecord;

public class HistoryRecordGate extends DataGate implements IHistoryRecordGate {

	public HistoryRecordGate(){
		super();
		keyProperty = "";
	}
	
	public HistoryRecordGate(String datasource) {
		super(datasource);
		keyProperty = "";
	}
	
	@Override
	protected Class<?> getModelClass() {
		return HistoryRecord.class;
	}

	@Override
	protected String getModelName() {
		return "HistoryRecord";
	}

	@Override
	public long update(DataModel model) {
		HistoryRecord hr = (HistoryRecord) model;
		Date date = hr.getDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		hr.setYear(year+"");
		hr.setMonth(month+"");
		hr.setDay(day+"");
		return super.update(model);
	}
}
