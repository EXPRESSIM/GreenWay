package com.stardust.express.dao.implementations;

import com.opensymphony.xwork2.util.KeyProperty;
import com.stardust.express.dao.abstracts.IChartExpress;
import com.stardust.express.models.ChartExpress;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartGate  implements IChartExpress {


    public ChartGate() {
        super();
        keyProperty = "";
    }

    public ChartGate(String datesource) {
        super(datesource);
        keyProperty = "";
    }

    @Override
    public void dateHorizon(String starDate,String endDate) {

        Session session = null;
        Transaction tst = null;
        PreparedStatement pst = null;

        try{
            session = getSession();
            tst =session.beginTransaction();
            String hql = "SELECT SUM(amount) as everMonth,YEAR ,MONTH  FROM dbo.EXPRESSWAY_GATEWAY_HISTORY WHERE REOCRD_DATE>='"+starDate+"' and REOCRD_DATE<='"+endDate+"' group by YEAR ,MONTH ";
            SQLQuery query = session.createSQLQuery(hql);

            query.executeUpdate();
            query.setResultSetMapping(hql);
            tst.commit();

            System.out.print(query);
        }catch (Exception e){
            e.printStackTrace();
            if (tst!=null){
                tst.rollback();
            }
        }finally {
            if (session!=null){
                session.close();
            }
        }

    }

    @Override
    protected Class<?> getModelClass() {
        return ChartExpress.class;
    }

    @Override
    protected String getModelName() {
        return "ChartExpress";
    }
}