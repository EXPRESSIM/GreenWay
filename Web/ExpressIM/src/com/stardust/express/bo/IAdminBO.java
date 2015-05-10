package com.stardust.express.bo;

import java.util.List;

import com.stardust.express.models.DataModel;
import com.stardust.express.tools.IViewContext;

public interface IAdminBO {
    List<DataModel> Filter(IViewContext ctx);
    List<DataModel> Filter(List<String> colums, List<Object> values, String sortBy, int pageSize, int page, Integer total);
    List<DataModel> Filter(String searchBy, String searchValue, String sortBy, int pageSize, int Integer, int total);
    DataModel Get(int id);
    DataModel Get(String key);
    int Remove(DataModel model);
    int Remove(IViewContext ctx);
    int Update(DataModel model);
    int Update(IViewContext ctx);
}
