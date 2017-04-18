package com.stardust.express.actions;

import com.stardust.express.bo.ChartBO;
import com.stardust.express.dao.implementations.Selection;
import com.stardust.express.models.DataModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartAction extends ActionExecutor {


    private List<DataModel> rows;

    private int total = 0;

    public List<DataModel> getRows() {
        return this.rows;
    }

    public int getTotal() {
        return total;
    }


    public String chartImport() {
        if (!isValidSession()) return ERROR;
        ChartBO bo = new ChartBO(context);
        Date startDate = context.getDate("startDate", null);
        Date endDate = context.getDate("endDate", null);

        List<Selection> selections = new ArrayList<Selection>();

        if (startDate != null) {
            selections.add(new Selection("REOCRD_DATE", Selection.Operator.GEATER_EQUAL, startDate, "startdate"));
        }

        if (endDate != null) {
            if (selections.size() > 0) {
                selections.add(new Selection("REOCRD_DATE", Selection.Operator.LESS_EQUAL, endDate, Selection.Operand.AND));
            } else {
                selections.add(new Selection("REOCRD_DATE", Selection.Operator.LESS_EQUAL, endDate));
            }
        }

        return SUCCESS;

    }

}
