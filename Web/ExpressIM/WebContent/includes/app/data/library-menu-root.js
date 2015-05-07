{
	items: [
		{
			text: "用户管理", 
			items:[
					{text:"检测员管理", view:"LibraryUser"},
					{text:"审核人管理", view:"LibraryReviewer"}
			],
			isRoot: true
		},
		{
			text: "检测管理",
			items: [
				{text:"检测结果录入", view:"FormEditor"},
				{text:"检测结果审核", view:"FormReview"},
				{text:"检测报告查询", view:"FormBrowser"},
				{text:"检测试剂管理", items:[
					{text:"试剂管理", view:"Agentia"},
					{text:"批号管理", view:"BatchNumber"}
				]}
			],
			isRoot: true
		},
		{
			text: "系统",
			items: [
				{ text: "修改密码", view: "ChangePassword" },
				{text:"退出",view:"Exit"},
				{ text: "关于", view: "About" }
			],
			isRoot: true
		}
	] 
}