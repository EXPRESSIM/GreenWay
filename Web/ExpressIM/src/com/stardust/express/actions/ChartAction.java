package com.stardust.express.actions;

import com.stardust.express.bo.HistoryRecordBO;
import com.stardust.express.dao.abstracts.IHistoryRecordGate.PeriodSummaryType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class ChartAction extends HistoryAction {

    private Map<String, Integer> summaryResponse;

    public Map<String, Integer> getResult() {
        return summaryResponse;
    }

    public String chartSummryProidBy() {
        if (!isValidSession()) return ERROR;
        HistoryRecordBO bo = new HistoryRecordBO(context);
        Date startDate = context.getDate("startDate", null);
        Date endDate = context.getDate("endDate", null);
        String summary = context.getString("summaryType", null);
        PeriodSummaryType summaryType = Enum.valueOf(PeriodSummaryType.class, summary.toUpperCase());
        summaryResponse = bo.getPeriodSummaryMap(startDate, endDate, summaryType);

        return SUCCESS;
    }

}
