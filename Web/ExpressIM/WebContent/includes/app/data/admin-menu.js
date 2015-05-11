{
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
				{ text: "稽查记录查询", view: "Supplier" }
			],
		    isRoot: true
		},
		{
		    text: "统计",
		    items: [
				{ text: "稽查金额统计", view: "Fee" }
			],
		    isRoot: true
		},
		{
		    text: "系统",
		    items: [
				{ text: "系统备份", view: "Examination" },
				{ text: "系统还原", view: "FormBrowser" },
				{ text: "修改密码", view: "ChangePassword" },
				{ text: "退出", view: "Exit" }
			],
		    isRoot: true
		}
	]
}