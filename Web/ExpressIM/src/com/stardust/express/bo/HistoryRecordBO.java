package com.stardust.express.bo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.stardust.express.dao.abstracts.DataGateFactory;
import com.stardust.express.dao.abstracts.IHistoryRecordGate;
import com.stardust.express.models.DataModel;
import com.stardust.express.models.HistoryRecord;
import com.stardust.express.tools.IViewContext;
import com.stardust.express.dao.abstracts.IHistoryRecordGate.PeriodSummaryType;

public class HistoryRecordBO extends AdminBO {

    public HistoryRecordBO(IViewContext context) {
        super(context);
        gate = DataGateFactory.getHistoryRecordGate("");
    }

    @Override
    protected DataModel _createModel(IViewContext ctx) {
        return new HistoryRecord(ctx);
    }

    public void cleanUp(String endDate, String folder) {
        ((IHistoryRecordGate) gate).cleanUp(endDate);
        deleteFiles(endDate, folder + "\\snapshoot\\");
        deleteFiles(endDate, folder + "\\video\\");
    }

    private void deleteFiles(String endDate, String path) {
        File root = new File(path);
        File[] fs = root.listFiles();
        if (fs == null) return;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date edate = formatter.parse(endDate);
            for (File f : fs) {
                long time = f.lastModified();
                Date date = new Date(time);
                if (date.before(edate)) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getEmptyMapForDay() {
        Map<String, Integer> getEmptyMapForDay = new HashMap<String, Integer>();
        for (int i = 0; i < 24; i++) {
            getEmptyMapForDay.put(String.valueOf(i), 0);
        }
        return getEmptyMapForDay;
    }

    public Map<String, Integer> getPeriodSummaryMap(Date startDate, Date endDate, PeriodSummaryType summaryType) {

        List<Object[]> summaryList = ((IHistoryRecordGate) gate).getPeriodSummaryData(startDate, endDate, summaryType);
        Map<String, Integer> summarymap = new HashMap<>();
        if (summaryType.equals(PeriodSummaryType.HOUR)) {
            summarymap = getEmptyMapForDay();
        }else{

        }

        for (Object[] obj : summaryList) {
            summarymap.put((String)obj[1], (Integer)obj[0]);
        }
        return summarymap;
    }
}

