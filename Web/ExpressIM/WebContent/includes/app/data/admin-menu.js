﻿{
    items: [
		{
		    text: "信息管理",
		    items: [
                { text: "用户管理", view: "User" }
			],
		    isRoot: true
		},
		{
		    text: "记录查询",
		    items: [
				{ text: "稽查记录查询", view: "History" },
				{ text: "稽查记录录入", view: "HistoryMaintenance" }
			],
		    isRoot: true
		},
		{
		    text: "统计",
		    items: [
				{ text: "汇总统计", view: "SummaryReport" },
				{ text: "车辆检验登记表", view: "ListReport" },
				{ text: "存档连续打印", view: "PrintReportSet"}
			],
		    isRoot: true
		},
        {
            text: "分析图表",
            items: [
                { text: "按时间分布统计", view: "CheckChart" }
            ],
            isRoot: true
        },
		{
		    text: "系统",
		    items: [  
				{ text: "退出", view: "Exit" }
			],
		    isRoot: true
		}
	]
}