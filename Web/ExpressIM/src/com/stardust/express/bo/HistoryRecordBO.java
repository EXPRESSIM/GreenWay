package com.stardust.express.bo;

import com.stardust.express.dao.abstracts.DataGateFactory;
import com.stardust.express.models.DataModel;
import com.stardust.express.models.HistoryRecord;
import com.stardust.express.tools.IViewContext;

public class HistoryRecordBO extends AdminBO {

	public HistoryRecordBO(IViewContext context) {
		super(context);
		gate = DataGateFactory.getHistoryRecordGate("");
	}

	@Override
	protected DataModel _createModel(IViewContext ctx) {
		return new HistoryRecord(ctx);
	}
}
