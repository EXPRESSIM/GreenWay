package com.stardust.express.dao.abstracts;

import java.util.List;

import com.stardust.express.models.DataModel;

public interface IDataGate {
	
	List<DataModel> fetchAll();
	DataModel find(long id);
	void remove(DataModel model);
	void add(DataModel model);
	void update(DataModel model);
	void setDatasource(String datasource);
	String getDatasource();
}
