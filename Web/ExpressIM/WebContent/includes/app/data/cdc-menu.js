{
    items: [
		{
		    text: "检测管理",
		    items: [
				{ text: "检测报告查询", view: "FormBrowser" }
			],
		    isRoot: true
		},
        {
            text: "统计",
            items: [
                { text: "检测", items: [
			                { text: "检测结果统计", view: "ReportResultSummary" }
		                ]
                }
	        ],
            isRoot: true
        },
		{
		    text: "系统",
		    items: [
				{ text: "修改密码", view: "ChangePassword" },
				{ text: "退出", view: "Exit" },
				{ text: "关于", view: "About" }
			],
		    isRoot: true
		}
	]
}