package com.stardust.express.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avro.data.Json;

import com.opensymphony.xwork2.ActionContext;
import com.stardust.express.bo.AdminBOFactory;
import com.stardust.express.bo.ChartBO;
import com.stardust.express.dao.implementations.ChartGate;

/**
 * Created by Administrator on 2017/4/14.
 */
public class ChartAction extends ActionExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 从前端获取year,month,day,starDate和endDate属性 再由action分配到ChartGate 进行数据处理
	 */

	private String button;
	private String starDate;
	private String endDate;
	private int everytime;
	private int count;
	private ChartGate cg;


	public int getEverytime() {
		return everytime;
	}

	public void setEverytime(int everytime) {
		this.everytime = everytime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getStarDate() {
		return starDate;
	}

	public void setStarDate(String starDate) {
		this.starDate = starDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String chartImport() throws Exception {

		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		System.out.println(request.getParameter("method"));

		

		if ("run_hour".equals(this.button)) {
			cg.chartDay(starDate, endDate);
			
			
		}
		if ("run_month".equals(this.button)) {
			
			
		} else {

			

		}
		return null;

		
		
	}

}
