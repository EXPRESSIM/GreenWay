package com.stardust.express.dao.implementations;

import com.stardust.express.dao.abstracts.IHistoryRecordGate;
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

}
