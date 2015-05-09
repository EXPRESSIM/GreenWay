package com.stardust.express.dao.abstracts;

import java.util.HashMap;
import java.util.List;

import com.stardust.express.models.DataModel;

public interface IDataGate {
	DataModel find(long id);
	void remove(DataModel model);
	void add(DataModel model);
	void update(DataModel model);
	void setDatasource(String datasource);
	String getDatasource();
	List<DataModel> fetchAll();
	List<DataModel> fetchAll(int start, int size);
	List<DataModel> fetchAll(int start, int size, HashMap<String, Object> selections);
	int count();
	int count(HashMap<String, Object> selections);
}
