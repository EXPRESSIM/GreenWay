package com.stardust.express.dao.implementations;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartGate extends HistoryRecordGate {

	public void chartDay(String starDate, String endDate) {

		Connection conn = null;

		try {
			String sql = "select DATEPART(HOUR,REOCRD_DATE) as everytime,COUNT(*) as count from EXPRESSWAY_GATEWAY_HISTORY where '"
					+ starDate + "'<=REOCRD_DATE and  '" + endDate
					+ "'>=REOCRD_DATE Group by DATEPART(HOUR,REOCRD_DATE)";

			PreparedStatement pst = conn.prepareStatement(sql);

			for (int everytime = 0; everytime <= 23; everytime++) {

				int count = 0;

				Map<Integer, Integer> map = new HashMap<Integer, Integer>();

				map.put(everytime, count);

			}
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}