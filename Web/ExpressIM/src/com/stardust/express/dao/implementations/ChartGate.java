package com.stardust.express.dao.implementations;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartGate extends HistoryRecordGate {

	public void chartDay(String starDate, String endDate) {

		Connection conn = null;
		String sql = "select DATEPART(HOUR,REOCRD_DATE) as everytime,COUNT(*) as count from EXPRESSWAY_GATEWAY_HISTORY where '"
				+ starDate + "'<=REOCRD_DATE and  '" + endDate + "'>=REOCRD_DATE Group by DATEPART(HOUR,REOCRD_DATE)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();

			ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String c = data.getColumnName(i);
					String v = rs.getString(c);
					System.out.println(c + ":" + v + "\t");
					map.put(c, v);
				}
				System.out.println("======================");
				al.add(map);
			}
			System.out.println(al);
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chartMonth(String starDate, String endDate) {

		Connection conn = null;
		String sql = "select sum(amount) as totalPerMonth,YEAR,MONTH,DAY from dbo.EXPRESSWAY_GATEWAY_HISTORY where '"
				+ starDate + "'<=REOCRD_DATE and '" + endDate + "'>=REOCRD_DATE group by YEAR,MONTH,DAY";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();

			ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String c = data.getColumnName(i);
					String v = rs.getString(c);
					System.out.println(c + ":" + v + "\t");
					map.put(c, v);
				}
				System.out.println("======================");
				al.add(map);
			}
			System.out.println(al);
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chartYear(String starDate, String endDate) {

		Connection conn = null;
		String sql = "select sum(amount) as totalPerMonth,YEAR,MONTH from dbo.EXPRESSWAY_GATEWAY_HISTORY where '"
				+ starDate + "'<=REOCRD_DATE and '" + endDate + "'>=REOCRD_DATE group by YEAR,MONTH";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();

			ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= data.getColumnCount(); i++) {
					String c = data.getColumnName(i);
					String v = rs.getString(c);
					System.out.println(c + ":" + v + "\t");
					map.put(c, v);
				}
				System.out.println("======================");
				al.add(map);
			}
			System.out.println(al);
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}