package com.stardust.express.bo;

import com.stardust.express.dao.abstracts.DataGateFactory;
import com.stardust.express.models.ChartExpress;
import com.stardust.express.models.DataModel;
import com.stardust.express.tools.IViewContext;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ChartBO extends AdminBO {

    public ChartBO(IViewContext context) {
        super(context);
        gate=DataGateFactory.getChartGate("");
    }

    @Override
    protected DataModel _createModel(IViewContext ctx) {
        return new ChartExpress(ctx);
    }
}
