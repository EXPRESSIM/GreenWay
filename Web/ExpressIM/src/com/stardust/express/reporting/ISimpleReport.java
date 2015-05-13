package com.stardust.express.reporting;

import java.util.List;

import com.stardust.express.models.HistoryRecord;

public interface ISimpleReport {
	public String getHTML(List<HistoryRecord> models);
}
